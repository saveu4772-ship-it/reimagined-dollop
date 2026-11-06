package ics.recents;

import android.animation.ValueAnimator;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
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
import de.robv.android.xposed.XposedHelpers;

/**
 * Jelly Bean GlowPad lock ring, modelled on AOSP's GlowPadView/PointCloud as seen on a JB lock screen:
 * a hairline ring with three flat white targets (camera left, search top, unlock right) and a dot cloud
 * (concentric bands of dots lit by a travelling wave and a glow that follows the finger). Touch the cloud to
 * get the lock handle; drag onto a target and the handle turns into a white disc with that target's icon.
 * Unlock goes through dismissKeyguard, so PIN/pattern still uses the normal bouncer.
 */
public class LockRing extends View {
    static final String TAG = "[IcsSysUi] ";
    static final String TAGKEY = "ics_lock_ring";
    static final int T_CAMERA = 0, T_SEARCH = 1, T_UNLOCK = 2;

    final Handler h = new Handler(Looper.getMainLooper());
    final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    final float u;                       // 1dp
    final View root;                     // keyguard window root

    // interaction
    float dx, dy;                        // handle offset from centre
    boolean dragging;
    int hit = -1;

    // point cloud (AOSP PointCloud): bands of dots + wave + glow
    float[] ptx, pty, ptr;
    int npts;
    float outerR;
    float waveR, waveA, waveW;
    float glowX, glowY, glowR, glowA;
    float cloudScale = 1f;
    ValueAnimator pulser, touchAnim;

    LockRing(Context c, View root) {
        super(c);
        this.root = root;
        u = c.getResources().getDisplayMetrics().density;
        setVisibility(View.GONE);
        buildCloud();
    }

    float ringR() { return 125 * u; }

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
                    int size = (int) (310 * r.u);
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, size, Gravity.BOTTOM);
                    // JB puts the ring centre at roughly 79% of the screen height
                    lp.bottomMargin = Math.max(0, (int) (H - (H * 0.79f + size / 2f)));
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

    // ------------------------------------------------------------ point cloud (port of AOSP PointCloud)
    void buildCloud() {
        float inner = 14 * u;
        outerR = ringR() * 1.12f;
        float area = outerR - inner;
        float ds = (float) (2.0 * Math.PI * inner / 8.0);            // INNER_POINTS = 8
        int bands = Math.max(1, Math.round(area / ds));
        float dr = area / bands;
        ArrayList<float[]> pts = new ArrayList<float[]>();
        float r = inner;
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
        waveW = 80 * u;
    }

    float alphaFor(int i) {
        float glowA2 = 0f;
        float gd = (float) Math.hypot(glowX - ptx[i], glowY - pty[i]);
        if (glowA > 0f && gd < glowR) {
            float cf = (float) Math.cos(Math.PI * 0.25 * gd / glowR);
            glowA2 = glowA * Math.max(0f, (float) Math.pow(cf, 10.0));
        }
        float d = ptr[i] - waveR;
        float waveA2 = 0f;
        if (waveA > 0f && d < 0f) {
            float cf = (float) Math.cos(Math.PI * 0.25 * d / waveW);
            waveA2 = waveA * Math.max(0f, (float) Math.pow(cf, 20.0));
        }
        return Math.max(glowA2, waveA2);
    }

    // ------------------------------------------------------------ animation
    float ease(float t) { float v = 1f - t; return 1f - v * v * v * v; }      // quart out

    void startPulse() {
        if (pulser != null || dragging) return;
        pulser = ValueAnimator.ofFloat(0f, 1f);
        pulser.setDuration(3200);
        pulser.setRepeatCount(ValueAnimator.INFINITE);
        pulser.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator a) {
                float t = (Float) a.getAnimatedValue();
                float w = Math.min(1f, t / 0.7f);                       // wave runs for 70%, then rests
                waveR = 10 * u + ease(w) * 110 * u;
                waveA = t < 0.7f ? 1f - w : 0f;
                glowA = 0f;
                invalidate();
            }
        });
        pulser.start();
    }

    void stopAnims() {
        if (pulser != null) { pulser.cancel(); pulser = null; }
        if (touchAnim != null) { touchAnim.cancel(); touchAnim = null; }
    }

    /** finger down: dots spread to the full ring (the wave races outwards and settles dim) */
    void touchWave() {
        stopAnims();
        glowX = 0; glowY = 0; glowR = 90 * u; glowA = 1f;
        touchAnim = ValueAnimator.ofFloat(0f, 1f);
        touchAnim.setDuration(700);
        touchAnim.setInterpolator(new DecelerateInterpolator());
        touchAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator a) {
                float t = (Float) a.getAnimatedValue();
                waveR = 20 * u + t * (outerR * 1.15f - 20 * u);
                waveA = 1f - 0.55f * t;
                invalidate();
            }
        });
        touchAnim.start();
    }

    // ------------------------------------------------------------ geometry
    float cx() { return getWidth() / 2f; }
    float cy() { return getHeight() / 2f; }
    float tdx(int t) { return t == T_CAMERA ? -ringR() : t == T_UNLOCK ? ringR() : 0f; }
    float tdy(int t) { return t == T_SEARCH ? -ringR() : 0f; }

    // ------------------------------------------------------------ touch
    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX() - cx(), y = e.getY() - cy();
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (Math.hypot(x, y) > 70 * u) return false;           // only the middle grabs touches
                dragging = true;
                getParent().requestDisallowInterceptTouchEvent(true);
                touchWave();
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
                stopAnims();
                reset(true);
                startPulse();
                if (fire == T_UNLOCK) unlock();
                else if (fire == T_CAMERA) camera();
                else if (fire == T_SEARCH) search();
                return true;
        }
        return false;
    }

    void move(float x, float y) {
        float max = ringR() * 1.25f, len = (float) Math.hypot(x, y);
        if (len > max) { x = x / len * max; y = y / len * max; }
        dx = x; dy = y;
        glowX = x; glowY = y;
        hit = -1;
        for (int t = 0; t < 3; t++)
            if (Math.hypot(dx - tdx(t), dy - tdy(t)) < 48 * u) hit = t;
        invalidate();
    }

    void reset(boolean animate) {
        if (!animate) { dx = 0; dy = 0; invalidate(); return; }
        final float sx = dx, sy = dy;
        ValueAnimator a = ValueAnimator.ofFloat(1f, 0f);
        a.setDuration(220);
        a.setInterpolator(new DecelerateInterpolator());
        a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator v) {
                float f = (Float) v.getAnimatedValue();
                dx = sx * f; dy = sy * f; invalidate();
            }
        });
        a.start();
    }

    // ------------------------------------------------------------ actions
    long suppressUntil;

    /** unlock = replay a real swipe-up on the lock screen, so SystemUI runs its own unlock/bouncer flow */
    void unlock() {
        try {
            suppressUntil = android.os.SystemClock.uptimeMillis() + 2500;
            setVisibility(View.GONE);
            final View r = root;
            final float x = r.getWidth() / 2f;
            final float y0 = r.getHeight() - 36 * u, y1 = r.getHeight() * 0.30f;
            final long t0 = android.os.SystemClock.uptimeMillis();
            final int steps = 12;
            sendTouch(r, MotionEvent.ACTION_DOWN, t0, t0, x, y0);
            for (int i = 1; i <= steps; i++) {
                final int k = i;
                h.postDelayed(new Runnable() {
                    @Override public void run() {
                        long now = android.os.SystemClock.uptimeMillis();
                        float y = y0 + (y1 - y0) * k / steps;
                        sendTouch(r, k == steps ? MotionEvent.ACTION_UP : MotionEvent.ACTION_MOVE, t0, now, x, y);
                    }
                }, 16L * i);
            }
            XposedBridge.log(TAG + "lockring: swipe-up unlock");
        } catch (Throwable t) { XposedBridge.log(TAG + "lockring unlock failed: " + t); }
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
    @Override protected void onDraw(Canvas c) {
        float cx = cx(), cy = cy(), R = ringR();
        if (pulser == null && touchAnim == null && !dragging) startPulse();

        // hairline ring
        p.setShader(null);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(1.2f * u);
        p.setColor(0xFFFFFFFF);
        p.setAlpha(dragging ? 70 : 42);
        c.drawCircle(cx, cy, R, p);

        // dot cloud
        p.setStyle(Paint.Style.FILL);
        p.setColor(0xFFFFFFFF);
        for (int i = 0; i < npts; i++) {
            float a = alphaFor(i);
            if (a < 0.03f) continue;
            float f = ptr[i] / outerR;
            float rad = (2.3f - 1.3f * f) * u;
            p.setAlpha((int) (Math.min(1f, a) * 255));
            c.drawCircle(cx + ptx[i], cy + pty[i], rad, p);
        }

        // targets: flat white icons on the ring (dim while the lock handle is held, bright when one is armed)
        for (int t = 0; t < 3; t++) {
            if (hit == t) continue;                                  // its icon is inside the handle
            int al = dragging && hit < 0 ? 150 : 255;
            drawTarget(c, t, cx + tdx(t), cy + tdy(t), 0xFFFFFFFF, al);
        }

        // handle
        float px = cx + dx, py = cy + dy;
        if (dragging) {
            if (hit >= 0) {
                p.setStyle(Paint.Style.FILL);
                p.setShader(new RadialGradient(px, py, 52 * u, 0xAAFFFFFF, 0x00FFFFFF, Shader.TileMode.CLAMP));
                c.drawCircle(px, py, 52 * u, p);
                p.setShader(null);
                p.setColor(0xFFFFFFFF);
                p.setAlpha(255);
                c.drawCircle(px, py, 29 * u, p);
                drawTarget(c, hit, px, py, 0xFF000000, 255);
            } else {
                p.setStyle(Paint.Style.FILL);
                p.setColor(0xFF000000);
                p.setAlpha(110);
                c.drawCircle(px, py, 23 * u, p);
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeWidth(2.6f * u);
                p.setColor(0xFFFFFFFF);
                p.setAlpha(255);
                c.drawCircle(px, py, 23 * u, p);
                drawLock(c, px, py, false, 0xFFFFFFFF, 255, 0.8f);
            }
        }
    }

    void drawTarget(Canvas c, int t, float x, float y, int color, int alpha) {
        if (t == T_CAMERA) drawCamera(c, x, y, color, alpha);
        else if (t == T_SEARCH) drawSearch(c, x, y, color, alpha);
        else drawLock(c, x, y, true, color, alpha, 1f);
    }

    void drawLock(Canvas c, float x, float y, boolean open, int color, int alpha, float s) {
        c.save();
        c.translate(x, y);
        c.scale(s, s);
        float v = u;
        p.setColor(color);
        p.setAlpha(alpha);
        p.setStyle(Paint.Style.FILL);
        c.drawRoundRect(new RectF(-9 * v, -2 * v, 9 * v, 13 * v), 2 * v, 2 * v, p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3f * v);
        p.setStrokeCap(Paint.Cap.BUTT);
        float lift = open ? 5 * v : 0;
        c.drawArc(new RectF(-6 * v, -15 * v - lift, 6 * v, -3 * v - lift), 180, 180, false, p);
        c.drawLine(-6 * v, -9 * v - lift, -6 * v, -2 * v, p);                 // left leg always reaches the body
        if (!open) c.drawLine(6 * v, -9 * v, 6 * v, -2 * v, p);
        // keyhole
        p.setStyle(Paint.Style.FILL);
        p.setColor(color == 0xFF000000 ? 0xFFFFFFFF : 0xFF000000);
        p.setAlpha(alpha);
        c.drawCircle(0, 5 * v, 2.2f * v, p);
        c.drawRect(-0.9f * v, 5 * v, 0.9f * v, 9 * v, p);
        c.restore();
    }

    void drawCamera(Canvas c, float x, float y, int color, int alpha) {
        float v = u;
        p.setColor(color);
        p.setAlpha(alpha);
        p.setStyle(Paint.Style.FILL);
        c.drawRoundRect(new RectF(x - 16 * v, y - 9 * v, x + 16 * v, y + 11 * v), 2 * v, 2 * v, p);
        c.drawRect(new RectF(x - 14 * v, y - 12 * v, x - 8 * v, y - 8 * v), p);
        p.setColor(color == 0xFF000000 ? 0xFFFFFFFF : 0xFF000000);
        p.setAlpha(alpha);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2.6f * v);
        c.drawCircle(x, y + 1.5f * v, 5f * v, p);
    }

    void drawSearch(Canvas c, float x, float y, int color, int alpha) {
        float v = u;
        p.setColor(color);
        p.setAlpha(alpha);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3.2f * v);
        p.setStrokeCap(Paint.Cap.ROUND);
        c.drawCircle(x - 3 * v, y - 3 * v, 8.5f * v, p);
        p.setStrokeWidth(4.4f * v);
        c.drawLine(x + 3.5f * v, y + 3.5f * v, x + 11 * v, y + 11 * v, p);
        p.setStrokeCap(Paint.Cap.BUTT);
    }
}
