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

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Ice Cream Sandwich style unlock ring (GlowPad look, drawn in code like TechnoUI does): a lock puck in the
 * middle of a thin ring with blue pulse waves. Drag it to the right target to unlock (PIN/pattern still goes
 * through the normal bouncer) or to the left target for the camera. Added once to the keyguard window root;
 * shown only while the lock screen is really visible.
 */
public class LockRing extends View {
    static final String TAG = "[IcsSysUi] ";
    static final int BLUE = 0xFF33B5E5;
    static final String TAGKEY = "ics_lock_ring";

    final Handler h = new Handler(Looper.getMainLooper());
    final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    final float u;                       // 1dp
    final View root;                     // keyguard window root
    float dx, dy;                        // puck offset from centre
    boolean dragging;
    int hit = -1;                        // 0 = camera (left), 1 = unlock (right)
    float pulse;
    ValueAnimator pulser;

    LockRing(Context c, View root) {
        super(c);
        this.root = root;
        u = c.getResources().getDisplayMetrics().density;
        setVisibility(View.GONE);
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
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            (int) (300 * r.u), Gravity.BOTTOM);
                    lp.bottomMargin = (int) (56 * r.u);
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
        stopPulse();
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
        boolean show = dragging || lockScreenShowing();
        if (show && getVisibility() != View.VISIBLE) { setVisibility(View.VISIBLE); startPulse(); }
        else if (!show && getVisibility() == View.VISIBLE) { setVisibility(View.GONE); stopPulse(); reset(false); }
    }

    void startPulse() {
        if (pulser != null) return;
        pulser = ValueAnimator.ofFloat(0f, 1f);
        pulser.setDuration(2200);
        pulser.setRepeatCount(ValueAnimator.INFINITE);
        pulser.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override public void onAnimationUpdate(ValueAnimator a) { pulse = (Float) a.getAnimatedValue(); invalidate(); }
        });
        pulser.start();
    }

    void stopPulse() { if (pulser != null) { pulser.cancel(); pulser = null; } }

    // ------------------------------------------------------------ geometry
    float cx() { return getWidth() / 2f; }
    float cy() { return getHeight() / 2f; }
    float ringR() { return 104 * u; }
    float tx(int i) { return cx() + (i == 0 ? -ringR() : ringR()); }

    // ------------------------------------------------------------ touch
    @Override public boolean onTouchEvent(MotionEvent e) {
        float x = e.getX() - cx(), y = e.getY() - cy();
        switch (e.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (Math.hypot(x, y) > 58 * u) return false;           // only the puck grabs touches
                dragging = true;
                getParent().requestDisallowInterceptTouchEvent(true);
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
                reset(true);
                if (fire == 1) unlock();
                else if (fire == 0) camera();
                return true;
        }
        return false;
    }

    void move(float x, float y) {
        float max = ringR() * 1.15f, len = (float) Math.hypot(x, y);
        if (len > max) { x = x / len * max; y = y / len * max; }
        dx = x; dy = y;
        hit = -1;
        for (int i = 0; i < 2; i++) {
            float ox = (i == 0 ? -ringR() : ringR());
            if (Math.hypot(dx - ox, dy) < 46 * u) hit = i;
        }
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
    void unlock() {
        try {
            Object wm = XposedHelpers.callStaticMethod(XposedHelpers.findClass("android.view.WindowManagerGlobal", null),
                    "getWindowManagerService");
            Class<?> cb = XposedHelpers.findClass("com.android.internal.policy.IKeyguardDismissCallback", null);
            XposedHelpers.callMethod(wm, "dismissKeyguard", new Class<?>[]{cb, CharSequence.class}, null, null);
            XposedBridge.log(TAG + "lockring: dismissKeyguard");
        } catch (Throwable t) { XposedBridge.log(TAG + "lockring unlock failed: " + t); }
    }

    void camera() {
        try {
            Intent i = new Intent("android.media.action.STILL_IMAGE_CAMERA_SECURE");
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(i);
            XposedBridge.log(TAG + "lockring: camera");
        } catch (Throwable t) { XposedBridge.log(TAG + "lockring camera failed: " + t); }
    }

    // ------------------------------------------------------------ drawing
    @Override protected void onDraw(Canvas c) {
        float cx = cx(), cy = cy(), R = ringR();

        // pulse wave (only while idle)
        if (!dragging) {
            p.setShader(null);
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(2 * u);
            p.setColor(BLUE);
            p.setAlpha((int) (120 * (1f - pulse)));
            c.drawCircle(cx, cy, R * (0.30f + 0.70f * pulse), p);
        }

        // outer ring
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2 * u);
        p.setColor(0xFFFFFFFF);
        p.setAlpha(dragging ? 110 : 60);
        c.drawCircle(cx, cy, R, p);

        // targets
        for (int i = 0; i < 2; i++) {
            float x = tx(i), y = cy;
            boolean on = hit == i;
            p.setStyle(Paint.Style.FILL);
            p.setColor(on ? BLUE : 0xFFFFFFFF);
            p.setAlpha(on ? 200 : 38);
            c.drawCircle(x, y, 28 * u, p);
            p.setStyle(Paint.Style.STROKE);
            p.setColor(0xFFFFFFFF);
            p.setAlpha(on ? 255 : 130);
            c.drawCircle(x, y, 28 * u, p);
            if (i == 1) drawLock(c, x, y, true, on ? 255 : 220); else drawCamera(c, x, y, on ? 255 : 220);
        }

        // puck
        float px = cx + dx, py = cy + dy;
        if (dragging) {
            p.setStyle(Paint.Style.FILL);
            p.setShader(new RadialGradient(px, py, 74 * u, 0xAA33B5E5, 0x0033B5E5, Shader.TileMode.CLAMP));
            c.drawCircle(px, py, 74 * u, p);
            p.setShader(null);
        }
        p.setStyle(Paint.Style.FILL);
        p.setColor(0xFFFFFFFF);
        p.setAlpha(dragging ? 90 : 55);
        c.drawCircle(px, py, 36 * u, p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(3 * u);
        p.setColor(dragging ? BLUE : 0xFFFFFFFF);
        p.setAlpha(dragging ? 255 : 200);
        c.drawCircle(px, py, 36 * u, p);
        drawLock(c, px, py, false, 255);
    }

    void drawLock(Canvas c, float x, float y, boolean open, int alpha) {
        p.setColor(0xFFFFFFFF);
        p.setAlpha(alpha);
        p.setStyle(Paint.Style.FILL);
        c.drawRoundRect(new RectF(x - 9 * u, y - 2 * u, x + 9 * u, y + 10 * u), 2 * u, 2 * u, p);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(2.5f * u);
        p.setStrokeCap(Paint.Cap.ROUND);
        float lift = open ? 4 * u : 0;
        c.drawArc(new RectF(x - 6 * u, y - 12 * u - lift, x + 6 * u, y - 0 * u - lift), 180, 180, false, p);
        c.drawLine(x + 6 * u, y - 6 * u - lift, x + 6 * u, y - 2 * u, p);
        if (!open) c.drawLine(x - 6 * u, y - 6 * u, x - 6 * u, y - 2 * u, p);
        p.setStrokeCap(Paint.Cap.BUTT);
    }

    void drawCamera(Canvas c, float x, float y, int alpha) {
        p.setColor(0xFFFFFFFF);
        p.setAlpha(alpha);
        p.setStyle(Paint.Style.FILL);
        c.drawRoundRect(new RectF(x - 11 * u, y - 7 * u, x + 11 * u, y + 8 * u), 2 * u, 2 * u, p);
        c.drawRect(new RectF(x - 5 * u, y - 10 * u, x + 5 * u, y - 7 * u), p);
        p.setColor(0xFF333333);
        c.drawCircle(x, y + 0.5f * u, 4.5f * u, p);
        p.setColor(0xFFFFFFFF);
        p.setAlpha(alpha);
        c.drawCircle(x, y + 0.5f * u, 2.2f * u, p);
    }
}
