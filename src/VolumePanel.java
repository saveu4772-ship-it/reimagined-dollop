package ics.recents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * TechnoUI / KitKat style volume panel: a dark bar under the status bar with a big speaker icon,
 * a flat thick cyan slider with a round knob, and an expand chevron.
 */
public class VolumePanel {
    static final String TAG = "[IcsVol] ";
    static final int CYAN = 0xFF33B5E5;
    static final int[] STREAMS = {AudioManager.STREAM_MUSIC, AudioManager.STREAM_RING, AudioManager.STREAM_ALARM};
    static final Handler H = new Handler(Looper.getMainLooper());

    static View root;
    static Context ctx;
    static AudioManager am;
    static boolean expanded;
    static BroadcastReceiver rcv;
    static final LinearLayout[] rows = new LinearLayout[3];
    static final FlatSlider[] bars = new FlatSlider[3];
    static final ImageView[] arrows = new ImageView[3];
    static final Runnable AUTO = new Runnable() { @Override public void run() { hide(); } };

    // ---------------------------------------------------------------- hook
    static void hook(ClassLoader cl) {
        String[] names = {
            "com.android.systemui.volume.VolumeDialogImpl",
            "com.android.systemui.volume.OplusVolumeDialogImpl",
            "com.android.systemui.volume.VolumeDialogController",
            "com.android.systemui.volume.VolumeDialogControllerImpl"
        };
        int total = 0;
        for (String n : names) {
            Class<?> c = XposedHelpers.findClassIfExists(n, cl);
            if (c == null) { XposedBridge.log(TAG + "no class " + n); continue; }
            StringBuilder seen = new StringBuilder();
            // walk the class hierarchy: show/showH may be declared in a parent
            for (Class<?> k = c; k != null && k != Object.class; k = k.getSuperclass()) {
                for (java.lang.reflect.Method m : k.getDeclaredMethods()) {
                    String mn = m.getName();
                    if (mn.toLowerCase().contains("show") || mn.toLowerCase().contains("dismiss"))
                        seen.append(k.getSimpleName()).append('.').append(mn).append(' ');
                    if (n.endsWith("Impl") && n.contains("Dialog") && !n.contains("Controller")
                            && (mn.equals("show") || mn.equals("showH") || mn.equals("-$$Nest$mshowH"))) {
                        try {
                            XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                                @Override protected Object replaceHookedMethod(MethodHookParam p) {
                                    try { show(currentApp()); } catch (Throwable t) { XposedBridge.log(TAG + "show: " + t); }
                                    return null;
                                }
                            });
                            total++;
                            XposedBridge.log(TAG + "hooked " + k.getName() + "." + mn);
                        } catch (Throwable t) { XposedBridge.log(TAG + "hook failed " + mn + ": " + t); }
                    }
                }
            }
            XposedBridge.log(TAG + "methods in " + n + ": " + seen);
        }
        XposedBridge.log(TAG + (total == 0 ? "no volume dialog method hooked" : "volume hooks: " + total));
    }

    static Context currentApp() {
        Class<?> at = XposedHelpers.findClass("android.app.ActivityThread", null);
        return (Context) XposedHelpers.callStaticMethod(at, "currentApplication");
    }

    // ---------------------------------------------------------------- show / hide
    static void show(final Context c) {
        H.post(new Runnable() {
            @Override public void run() {
                try {
                    ctx = c;
                    am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
                    if (root == null) {
                        root = build(c);
                        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                                -1, -2, 2038 /* TYPE_APPLICATION_OVERLAY */,
                                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                        | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                                        | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                                PixelFormat.TRANSLUCENT);
                        lp.gravity = Gravity.TOP;
                        lp.y = statusBarHeight(c);               // sits directly under the status bar
                        ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).addView(root, lp);
                        register(c);
                        root.setAlpha(0f);
                        root.animate().alpha(1f).setDuration(120).start();
                    }
                    refresh();
                    H.removeCallbacks(AUTO);
                    H.postDelayed(AUTO, 3000);
                } catch (Throwable t) { XposedBridge.log(TAG + "show failed: " + t); root = null; }
            }
        });
    }

    static void hide() {
        final View r = root;
        if (r == null) return;
        root = null;
        expanded = false;
        H.removeCallbacks(AUTO);
        try { if (rcv != null) ctx.unregisterReceiver(rcv); } catch (Throwable ignored) {}
        rcv = null;
        final Context c = ctx;
        r.animate().alpha(0f).setDuration(120).withEndAction(new Runnable() {
            @Override public void run() {
                try { ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).removeView(r); }
                catch (Throwable ignored) {}
            }
        }).start();
    }

    static void register(Context c) {
        rcv = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent i) { if (root != null) refresh(); }
        };
        IntentFilter f = new IntentFilter();
        f.addAction("android.media.VOLUME_CHANGED_ACTION");
        f.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
        try { c.registerReceiver(rcv, f); } catch (Throwable t) { XposedBridge.log(TAG + "receiver: " + t); }
    }

    // ---------------------------------------------------------------- UI
    static int dp(Context c, float v) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, c.getResources().getDisplayMetrics());
    }

    static int statusBarHeight(Context c) {
        Resources r = c.getResources();
        int id = r.getIdentifier("status_bar_height", "dimen", "android");
        return id > 0 ? r.getDimensionPixelSize(id) : dp(c, 24);
    }

    static void bump() { H.removeCallbacks(AUTO); H.postDelayed(AUTO, 3000); }

    static View build(final Context c) {
        LinearLayout panel = new LinearLayout(c);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setBackgroundColor(0xFF121418);
        panel.setClickable(true);
        for (int i = 0; i < 3; i++) {
            final int idx = i;
            LinearLayout row = new LinearLayout(c);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);

            ImageView ic = new ImageView(c);
            ic.setImageDrawable(icon(c, i));
            ic.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ic.setPadding(dp(c, 12), dp(c, 8), dp(c, 10), dp(c, 8));
            row.addView(ic, new LinearLayout.LayoutParams(dp(c, 59), dp(c, 45)));

            FlatSlider fs = new FlatSlider(c);
            fs.cb = new FlatSlider.OnChange() {
                @Override public void onChange(int v) {
                    try { am.setStreamVolume(STREAMS[idx], v, 0); } catch (Throwable t) { XposedBridge.log(TAG + "set: " + t); }
                    bump();
                }
            };
            row.addView(fs, new LinearLayout.LayoutParams(0, dp(c, 45), 1f));
            bars[i] = fs;

            ImageView ar = new ImageView(c);
            ar.setImageDrawable(arrow(c));
            ar.setScaleType(ImageView.ScaleType.CENTER);
            ar.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) { expanded = !expanded; refresh(); bump(); }
            });
            row.addView(ar, new LinearLayout.LayoutParams(dp(c, 60), dp(c, 45)));
            arrows[i] = ar;

            rows[i] = row;
            panel.addView(row, new LinearLayout.LayoutParams(-1, dp(c, 45)));
        }
        View line = new View(c);
        line.setBackgroundColor(0x44FFFFFF);
        panel.addView(line, new LinearLayout.LayoutParams(-1, Math.max(1, dp(c, 0.5f))));
        return panel;
    }

    static void refresh() {
        if (am == null) return;
        int primary = am.isMusicActive() ? 0 : 1;
        for (int i = 0; i < 3; i++) {
            try { bars[i].set(am.getStreamMaxVolume(STREAMS[i]), am.getStreamVolume(STREAMS[i])); }
            catch (Throwable ignored) {}
            rows[i].setVisibility(expanded || i == primary ? View.VISIBLE : View.GONE);
            arrows[i].setVisibility(i == primary ? View.VISIBLE : View.INVISIBLE);
            arrows[i].setRotation(expanded ? 180 : 0);
        }
    }

    /** Flat, thick slider like TechnoUI: gray track, cyan fill, round cyan knob. */
    static class FlatSlider extends View {
        interface OnChange { void onChange(int v); }
        OnChange cb;
        int max = 15, progress;
        final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        final float d;

        FlatSlider(Context c) { super(c); d = c.getResources().getDisplayMetrics().density; }

        void set(int m, int v) { max = Math.max(1, m); progress = v; invalidate(); }

        @Override protected void onDraw(Canvas cv) {
            float w = getWidth(), h = getHeight();
            float bh = 24 * d, top = (h - bh) / 2f;
            float x = w * progress / (float) max;
            p.setColor(0xFF666666);
            cv.drawRect(0, top, w, top + bh, p);
            p.setColor(CYAN);
            cv.drawRect(0, top, x, top + bh, p);
            float r = bh * 0.36f;
            cv.drawCircle(Math.min(Math.max(x, r), w - r), h / 2f, r, p);
        }

        @Override public boolean onTouchEvent(MotionEvent e) {
            int a = e.getActionMasked();
            if (a == MotionEvent.ACTION_DOWN) getParent().requestDisallowInterceptTouchEvent(true);
            if (a == MotionEvent.ACTION_DOWN || a == MotionEvent.ACTION_MOVE) {
                int v = Math.round(Math.max(0f, Math.min(1f, e.getX() / Math.max(1, getWidth()))) * max);
                if (v != progress) { progress = v; invalidate(); if (cb != null) cb.onChange(v); }
            }
            if (a == MotionEvent.ACTION_UP || a == MotionEvent.ACTION_CANCEL) bump();
            return true;
        }
    }

    // ---------------------------------------------------------------- icons
    static Drawable arrow(Context c) {
        int s = 96;
        Bitmap b = Bitmap.createBitmap(s, s, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFFFFF); p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(9);
        p.setStrokeCap(Paint.Cap.SQUARE);
        Path pa = new Path();
        pa.moveTo(22, 34); pa.lineTo(48, 62); pa.lineTo(74, 34);
        cv.drawPath(pa, p);
        return new BitmapDrawable(c.getResources(), b);
    }

    /** 0 = speaker (media), 1 = bell (ring), 2 = alarm clock */
    static Drawable icon(Context c, int kind) {
        int s = 96;
        Bitmap b = Bitmap.createBitmap(s, s, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFFFFF);
        if (kind == 0) {
            p.setStyle(Paint.Style.FILL);
            Path sp = new Path();
            sp.moveTo(6, 36); sp.lineTo(26, 36); sp.lineTo(52, 12); sp.lineTo(52, 84);
            sp.lineTo(26, 60); sp.lineTo(6, 60); sp.close();
            cv.drawPath(sp, p);
            p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(8); p.setStrokeCap(Paint.Cap.ROUND);
            cv.drawArc(new RectF(40, 30, 76, 66), -55, 110, false, p);
            cv.drawArc(new RectF(34, 16, 92, 80), -55, 110, false, p);
        } else if (kind == 1) {
            p.setStyle(Paint.Style.FILL);
            Path bp = new Path();
            bp.moveTo(48, 12);
            bp.cubicTo(28, 12, 24, 30, 24, 46);
            bp.lineTo(24, 58); bp.lineTo(14, 68); bp.lineTo(82, 68); bp.lineTo(72, 58);
            bp.lineTo(72, 46); bp.cubicTo(72, 30, 68, 12, 48, 12);
            cv.drawPath(bp, p);
            cv.drawCircle(48, 78, 8, p);
        } else {
            p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(8);
            cv.drawCircle(48, 52, 28, p);
            cv.drawLine(48, 52, 48, 36, p);
            cv.drawLine(48, 52, 60, 58, p);
            p.setStyle(Paint.Style.FILL);
            cv.drawCircle(22, 22, 10, p);
            cv.drawCircle(74, 22, 10, p);
        }
        return new BitmapDrawable(c.getResources(), b);
    }
}
