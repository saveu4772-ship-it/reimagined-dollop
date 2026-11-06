package ics.recents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/** KitKat-style volume panel: slides in from the top, Holo-blue sliders, expand arrow. */
public class VolumePanel {
    static final String TAG = "[IcsVol] ";
    static final int BLUE = 0xFF33B5E5;
    static final int[] STREAMS = {AudioManager.STREAM_MUSIC, AudioManager.STREAM_RING, AudioManager.STREAM_ALARM};
    static final Handler H = new Handler(Looper.getMainLooper());

    static View root;
    static Context ctx;
    static AudioManager am;
    static boolean expanded;
    static BroadcastReceiver rcv;
    static final LinearLayout[] rows = new LinearLayout[3];
    static final SeekBar[] bars = new SeekBar[3];
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
                        ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).addView(root, lp);
                        register(c);
                        root.setTranslationY(-dp(c, 120));
                        root.animate().translationY(0).setDuration(150).start();
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
        r.animate().translationY(-r.getHeight()).setDuration(130).withEndAction(new Runnable() {
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

    static View build(final Context c) {
        LinearLayout panel = new LinearLayout(c);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setBackgroundColor(0xF2101010);
        panel.setPadding(dp(c, 8), dp(c, 4), dp(c, 8), dp(c, 4));
        panel.setClickable(true);
        for (int i = 0; i < 3; i++) {
            final int idx = i;
            LinearLayout row = new LinearLayout(c);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER_VERTICAL);

            ImageView ic = new ImageView(c);
            ic.setImageDrawable(icon(c, i));
            row.addView(ic, new LinearLayout.LayoutParams(dp(c, 44), dp(c, 44)));

            SeekBar sb = new SeekBar(c);
            sb.setProgressDrawable(track(c));
            sb.setThumb(thumb(c));
            sb.setThumbOffset(0);
            sb.setPadding(dp(c, 12), 0, dp(c, 12), 0);
            sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override public void onProgressChanged(SeekBar s, int p, boolean fromUser) {
                    if (!fromUser) return;
                    try { am.setStreamVolume(STREAMS[idx], p, 0); } catch (Throwable t) { XposedBridge.log(TAG + "set: " + t); }
                    H.removeCallbacks(AUTO); H.postDelayed(AUTO, 3000);
                }
                @Override public void onStartTrackingTouch(SeekBar s) { H.removeCallbacks(AUTO); }
                @Override public void onStopTrackingTouch(SeekBar s) { H.postDelayed(AUTO, 3000); }
            });
            row.addView(sb, new LinearLayout.LayoutParams(0, dp(c, 44), 1f));
            bars[i] = sb;

            ImageView ar = new ImageView(c);
            ar.setImageDrawable(arrow(c));
            ar.setPadding(dp(c, 8), dp(c, 8), dp(c, 8), dp(c, 8));
            ar.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) { expanded = !expanded; refresh(); H.removeCallbacks(AUTO); H.postDelayed(AUTO, 3000); }
            });
            row.addView(ar, new LinearLayout.LayoutParams(dp(c, 44), dp(c, 44)));
            arrows[i] = ar;

            rows[i] = row;
            panel.addView(row, new LinearLayout.LayoutParams(-1, -2));
        }
        return panel;
    }

    static void refresh() {
        if (am == null) return;
        int primary = am.isMusicActive() ? 0 : 1;
        for (int i = 0; i < 3; i++) {
            try {
                bars[i].setMax(am.getStreamMaxVolume(STREAMS[i]));
                bars[i].setProgress(am.getStreamVolume(STREAMS[i]));
            } catch (Throwable ignored) {}
            rows[i].setVisibility(expanded || i == primary ? View.VISIBLE : View.GONE);
            arrows[i].setVisibility(i == primary ? View.VISIBLE : View.INVISIBLE);
            arrows[i].setRotation(expanded ? 180 : 0);
        }
    }

    // ---------------------------------------------------------------- drawing helpers
    static Drawable track(Context c) {
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(0x55FFFFFF);
        bg.setSize(-1, dp(c, 3));
        GradientDrawable fg = new GradientDrawable();
        fg.setColor(BLUE);
        fg.setSize(-1, dp(c, 3));
        ClipDrawable clip = new ClipDrawable(fg, Gravity.LEFT, ClipDrawable.HORIZONTAL);
        LayerDrawable ld = new LayerDrawable(new Drawable[]{bg, clip});
        ld.setId(0, android.R.id.background);
        ld.setId(1, android.R.id.progress);
        return ld;
    }

    static Drawable thumb(Context c) {
        GradientDrawable g = new GradientDrawable();
        g.setShape(GradientDrawable.OVAL);
        g.setColor(BLUE);
        g.setSize(dp(c, 18), dp(c, 18));
        return g;
    }

    static Drawable arrow(Context c) {
        int s = 96;
        Bitmap b = Bitmap.createBitmap(s, s, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(b);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xFFFFFFFF); p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(8);
        Path pa = new Path();
        pa.moveTo(24, 36); pa.lineTo(48, 62); pa.lineTo(72, 36);
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
            sp.moveTo(14, 38); sp.lineTo(32, 38); sp.lineTo(54, 18); sp.lineTo(54, 78);
            sp.lineTo(32, 58); sp.lineTo(14, 58); sp.close();
            cv.drawPath(sp, p);
            p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(7);
            cv.drawArc(new RectF(40, 30, 76, 66), -50, 100, false, p);
            cv.drawArc(new RectF(34, 18, 90, 78), -50, 100, false, p);
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
            p.setStyle(Paint.Style.STROKE); p.setStrokeWidth(7);
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
