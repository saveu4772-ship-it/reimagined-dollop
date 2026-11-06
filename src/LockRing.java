package ics.recents;

import android.animation.ValueAnimator;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import java.util.ArrayList;

import de.robv.android.xposed.XposedBridge;

/**
 * Port of the AOSP KitKat GlowPadView as used by Holo_Lock: dot cloud (PointCloud) + wave + glow, hairline
 * outer ring, three targets (unlock right, search up, camera left) and the ringed lock handle, all with
 * Holo_Lock's real bitmaps and AOSP's constants. Unlock replays a real swipe-up so SystemUI runs its own
 * unlock/bouncer flow.
 */
public class LockRing extends View {
    static final String TAG = "[IcsSysUi] ";
    static final String TAGKEY = "ics_lock_ring";
    // slot order = AOSP: angle = -2*PI*i/4  ->  0 right, 1 up, 2 left, (3 down: empty)
    static final int T_UNLOCK = 0, T_SEARCH = 1, T_CAMERA = 2;
    static final String[] NORMAL = {"unlock", "assist", "camera"};
    static final String[] ACTIVE = {"unlock_a", "assist_a", "camera_a"};

    final Handler h = new Handler(Looper.getMainLooper());
    final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    final float u;                       // 1dp
    final float k;                       // art scale: bitmaps are xhdpi (density 2.0)
    final View root;                     // keyguard window root

    // dimensions (Holo_Lock dimens.xml)
    final float outerR, innerR, glowRadius, snapR;

    // interaction state
    float dx, dy;                        // handle offset from centre
    boolean dragging;
    int hit = -1;
    float handleA;

    // point cloud: bands of dots + wave + glow
    float[] ptx, pty, ptr;
    int npts;
    float waveR, waveA, waveW;
    float glowX, glowY, glowR, glowA;
    ValueAnimator waveAnim, handleAnim;
    long suppressUntil;

    LockRing(Context c, View root) {
        super(c);
        this.root = root;
        u = c.getResources().getDisplayMetrics().density;
        k = u / 2f;
        outerR = 135 * u;
        innerR = 15 * u;
        glowRadius = 75 * u;
        snapR = 52 * u;
        waveW = 100 * u;                 // AOSP: 200px
        glowR = glowRadius;
        setVisibility(View.GONE);
        buildCloud();
    }

    // ------------------------------------------------------------ install
    static void attach(final View top) {
        if (!(top instanceof FrameLayout)) { XposedBridge.log(TAG + "lockring: root is not a FrameLayout: " + top.getClass().getName()); return; }
        top.post(new Runnable() {
            @Override public void run() {
                try {
                    FrameLayout f = (FrameLayout) top;
                    for (int i = 0; i < f.getChildCount(); i++)
                        if (TAGKEY.equals(f.getChildAt(i).getTag())) return;       // already there
                    LockRing r = new LockRing(f.getContext(), f);
                    r.setTag(TAGKEY);
                    int H = f.getContext().getResources().getDisplayMetrics().heightPixels;
                    int size = (int) (400 * r.u);
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size, Gravity.TOP);
                    // JB/KK lock screen: ring centre at roughly 79% of the screen height
                    lp.topMargin = (int) (H * 0.79f - size / 2f);
                    f.addView(r, lp);
                    XposedBridge.log(TAG + "lockring added to " + f.getClass().getSimpleName());
                } catch (Throwable t) { XposedBridge.log(TAG + "lockring attach: " + t); }
            }
        });
    }

    @Override protected void onAttachedToWindow() { super.onAttachedToWindow(); h.post(poll); }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h.removeCallbacks(poll);
        stopAnims();
    }

    // ------------------------------------------------------------ visibility
    final Runnable poll = new Runnable() {
        @Override public void run() {
            try { update(); } catch (Throwable t) { XposedBridge.log(TAG + "lockring poll: " + t); }
            h.postDelayed(this, 350);
        }
    };

    View byName(String name) {
        int id = getResources().getIdentifier(name, "id", "com.android.systemui");
        return id == 0 ? null : root.findViewById(id);
    }

    String lastWhy = "";

    void why(String r) {
        if (!r.equals(lastWhy)) { lastWhy = r; XposedBridge.log(TAG + "lockring state: " + r); }
    }

    boolean lockScreenShowing() {
        Context c = getContext();
        KeyguardManager km = (KeyguardManager) c.getSystemService(Context.KEYGUARD_SERVICE);
        PowerManager pm = (PowerManager) c.getSystemService(Context.POWER_SERVICE);
        if (km == null || !km.isKeyguardLocked()) { why("hidden: keyguard not locked"); return false; }
        if (pm == null || !pm.isInteractive()) { why("hidden: screen not interactive"); return false; }
        View st = byName("keyguard_status_view");
        if (st == null) { why("hidden: keyguard_status_view not found"); return false; }
        if (!st.isShown()) { why("hidden: status view not shown"); return false; }
        if (st.getAlpha() < 0.9f) { why("hidden: status view alpha " + st.getAlpha()); return false; }
        View bouncer = byName("keyguard_bouncer_container");
        if (bouncer instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) bouncer;
            for (int i = 0; i < g.getChildCount(); i++) {
                View k = g.getChildAt(i);
                if (k.isShown() && k.getAlpha() > 0.5f) { why("hidden: bouncer visible (" + k.getClass().getSimpleName() + ")"); return false; }
            }
        }
        why("showing");
        return true;
    }

    void update() {
        boolean show = (dragging || lockScreenShowing()) && android.os.SystemClock.uptimeMillis() > suppressUntil;
        if (show && getVisibility() != View.VISIBLE) { setVisibility(View.VISIBLE); startPulse(); }
        else if (!show && getVisibility() == View.VISIBLE) { setVisibility(View.GONE); stopAnims(); reset(false); }
    }

    // ------------------------------------------------------------ point cloud (AOSP PointCloud)
    void buildCloud() {
        float area = outerR - innerR;
        float ds = (float) (2.0 * Math.PI * innerR / 8.0);           // INNER_POINTS = 8
        int bands = Math.max(1, Math.round(area / ds));
        float dr = area / bands;
        ArrayList<float[]> pts = new ArrayList<float[]>();
        float r = innerR;
        for (int b = 0; b <= bands; b++, r += dr) {
            float circ = (float) (Math.PI * 2.0 * r);
            int n = Math.max(1, (int) (circ / ds));
            float eta = (float) (Math.PI / 2.0), dEta = (float) (2.0 * Math.PI / n);
            for (int i = 0; i < n; i++) {
                pts.add(new float[]{(float) (r * Math.cos(eta)), (float) (r * Math.sin(eta)), r});
                eta += dEta;
            }
        }
        npts = pts.size();
        ptx = new float[npts]; pty = new float[npts]; ptr = new float[npts];
        for (int i = 0; i < npts; i++) { float[] a = pts.get(i); ptx[i] = a[0]; pty[i] = a[1]; ptr[i] = a[2]; }
    }

    float alphaFor(int i) {
        float ga = 0f;
        float gd = (float) Math.hypot(glowX - ptx[i], glowY - pty[i]);
        if (glowA > 0f && gd < glowR) {
            float cf = (float) Math.cos(Math.PI * 0.25 * gd / glowR);
            ga = glowA * Math.max(0f, (float) Math.pow(cf, 10.0));
        }
        float d = ptr[i] - waveR;
        float wa = 0f;
        if (waveA > 0f && d < waveW * 0.5f && d < 0f) {
            float cf = (float) Math.cos(Math.PI * 0.25 * d / waveW);
            wa = waveA * Math.max(0f, (float) Math.pow(cf, 20.0));
        }
        return Math.max(ga, wa);
    }

    // ------------------------------------------------------------ animation
    /** AOSP startWaveAnimation: radius handle/2 -> 2*outerRadius, 1350ms, Quad.easeOut, alpha 1 until the end */
    void ping() {
        if (waveAnim != null) waveAnim.cancel();
        waveA = 1f;
        waveAnim = ValueAnimator.ofFloat(0f, 1f);
        waveAnim.setDuration(1350);
        waveAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator a) {
                float t = (Float) a.getAnimatedValue();
                float f = 2f * t - t * t;
                waveR = 54 * u + f * (2f * outerR - 54 * u);
                if (t >= 1f) { waveR = 0f; waveA = 0f; }
                invalidate();
            }
        });
        waveAnim.start();
    }

    final Runnable pingLoop = new Runnable() {
        @Override public void run() {
            if (getVisibility() == View.VISIBLE && !dragging) ping();
            h.postDelayed(this, 4200);
        }
    };

    void startPulse() { h.removeCallbacks(pingLoop); h.post(pingLoop); }

    void stopAnims() {
        h.removeCallbacks(pingLoop);
        if (waveAnim != null) { waveAnim.cancel(); waveAnim = null; }
        if (handleAnim != null) { handleAnim.cancel(); handleAnim = null; }
        waveA = 0f; glowA = 0f; handleA = 0f;
    }

    void fadeHandleIn() {
        if (handleAnim != null) handleAnim.cancel();
        handleAnim = ValueAnimator.ofFloat(handleA, 1f);
        handleAnim.setDuration(200);
        handleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator a) { handleA = (Float) a.getAnimatedValue(); invalidate(); }
        });
        handleAnim.start();
    }

    /** AOSP return-to-home: handle slides back to the centre and fades (200ms) */
    void returnHome() {
        if (handleAnim != null) handleAnim.cancel();
        final float sx = dx, sy = dy, sa = handleA;
        handleAnim = ValueAnimator.ofFloat(0f, 1f);
        handleAnim.setDuration(200);
        handleAnim.setInterpolator(new DecelerateInterpolator());
        handleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator a) {
                float f = (Float) a.getAnimatedValue();
                dx = sx * (1f - f); dy = sy * (1f - f); handleA = sa * (1f - f);
                invalidate();
            }
        });
        handleAnim.start();
    }

    void reset(boolean animate) {
        dx = 0; dy = 0; handleA = 0; hit = -1; dragging = false; glowA = 0;
        invalidate();
    }

    // ------------------------------------------------------------ geometry
    float cx() { return getWidth() / 2f; }
    float cy() { return getHeight() / 2f; }
    float tx(int i) { return (float) (outerR * Math.cos(-2.0 * Math.PI * i / 4.0)); }
    float ty(int i) { return (float) (outerR * Math.sin(-2.0 * Math.PI * i / 4.0)); }

    // ------------------------------------------------------------ touch
    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX() - cx(), y = e.getY() - cy();
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (Math.hypot(x, y) > glowRadius) return false;       // AOSP: touch must start inside the glow radius
                dragging = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                h.removeCallbacks(pingLoop);
                ping();
                fadeHandleIn();
                move(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                if (dragging) move(x, y);
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!dragging) return false;
                int fire = e.getActionMasked() == MotionEvent.ACTION_UP ? hit : -1;
                dragging = false;
                hit = -1;
                glowA = 0f;
                returnHome();
                startPulse();
                if (fire == T_UNLOCK) unlock();
                else if (fire == T_CAMERA) camera();
                else if (fire == T_SEARCH) search();
                return true;
        }
        return false;
    }

    void move(float x, float y) {
        float len = (float) Math.hypot(x, y);
        if (len > outerR) { x = x / len * outerR; y = y / len * outerR; }
        dx = x; dy = y;
        glowX = x; glowY = y;
        hit = -1;
        for (int t = 0; t < 3; t++)
            if (Math.hypot(dx - tx(t), dy - ty(t)) < snapR) hit = t;
        glowA = hit >= 0 ? 0f : 1f;                                  // glow hides while a target is armed
        invalidate();
    }

    // ------------------------------------------------------------ actions
    /** unlock = replay a real swipe-up on the lock screen, so SystemUI runs its own unlock/bouncer flow */
    void unlock() {
        try {
            suppressUntil = android.os.SystemClock.uptimeMillis() + 2500;
            setVisibility(View.GONE);
            swipeUp(root, h, u);
            XposedBridge.log(TAG + "lockring: swipe-up unlock");
        } catch (Throwable t) { XposedBridge.log(TAG + "lockring unlock failed: " + t); }
    }

    static void swipeUp(final View r, final Handler h, final float u) {
        final float x = r.getWidth() / 2f;
        final float y0 = r.getHeight() - 36 * u, y1 = r.getHeight() * 0.30f;
        final long t0 = android.os.SystemClock.uptimeMillis();
        final int steps = 12;
        sendTouch(r, MotionEvent.ACTION_DOWN, t0, t0, x, y0);
        for (int i = 1; i <= steps; i++) {
            final int kk = i;
            h.postDelayed(new Runnable() {
                @Override public void run() {
                    long now = android.os.SystemClock.uptimeMillis();
                    float y = y0 + (y1 - y0) * kk / steps;
                    sendTouch(r, kk == steps ? MotionEvent.ACTION_UP : MotionEvent.ACTION_MOVE, t0, now, x, y);
                }
            }, 16L * i);
        }
    }

    static void sendTouch(View r, int action, long down, long now, float x, float y) {
        MotionEvent e = MotionEvent.obtain(down, now, action, x, y, 0);
        e.setSource(android.view.InputDevice.SOURCE_TOUCHSCREEN);
        try { r.dispatchTouchEvent(e); } finally { e.recycle(); }
    }

    void camera() {
        try {
            Intent i = new Intent("android.media.action.STILL_IMAGE_CAMERA_SECURE");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(i);
            XposedBridge.log(TAG + "lockring: camera");
        } catch (Throwable t) { XposedBridge.log(TAG + "lockring camera failed: " + t); }
    }

    void search() {
        try {
            Intent i = new Intent(Intent.ACTION_ASSIST);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(i);
            XposedBridge.log(TAG + "lockring: assist");
        } catch (Throwable t) { XposedBridge.log(TAG + "lockring assist failed: " + t); }
    }

    // ------------------------------------------------------------ drawing
    void bmp(Canvas c, String key, float x, float y, float alpha) {
        if (alpha <= 0.01f) return;
        Bitmap b = GlowArt.get(key);
        float hw = b.getWidth() * k / 2f, hh = b.getHeight() * k / 2f;
        p.setStyle(Paint.Style.FILL);
        p.setShader(null);
        p.setFilterBitmap(true);
        p.setAlpha((int) (Math.min(1f, alpha) * 255));
        c.drawBitmap(b, null, new RectF(x - hw, y - hh, x + hw, y + hh), p);
    }

    @Override protected void onDraw(Canvas c) {
        float cx = cx(), cy = cy();

        // dot cloud: glowdot bitmap scaled pointSize/4 (pointSize 4 at the centre -> 2 at the rim), alpha from wave/glow
        Bitmap dot = GlowArt.get("dot");
        p.setFilterBitmap(true);
        for (int i = 0; i < npts; i++) {
            float a = alphaFor(i);
            if (a < 0.02f) continue;
            float size = 4f + (2f - 4f) * (ptr[i] / outerR);
            float s = size / 4f * k;
            float px = cx + ptx[i], py = cy + pty[i];
            p.setAlpha((int) (Math.min(1f, a) * 255));
            float hw = dot.getWidth() * s / 2f, hh = dot.getHeight() * s / 2f;
            c.drawBitmap(dot, null, new RectF(px - hw, py - hh, px + hw, py + hh), p);
        }

        // outer ring: oval, 2dp stroke, #1affffff
        p.setShader(null);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2 * u);
        p.setColor(0x1AFFFFFF);
        c.drawCircle(cx, cy, outerR, p);

        // targets (activated bitmap sits on the target itself)
        float others = dragging && hit < 0 ? 0.6f : 1f;
        for (int t = 0; t < 3; t++) {
            if (hit == t) bmp(c, ACTIVE[t], cx + tx(t), cy + ty(t), 1f);
            else bmp(c, NORMAL[t], cx + tx(t), cy + ty(t), others);
        }

        // handle: the ringed lock, follows the finger; hidden while a target is armed
        if (handleA > 0f && hit < 0) bmp(c, "handle", cx + dx, cy + dy, handleA);
    }
}
