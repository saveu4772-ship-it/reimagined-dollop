package ics.recents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import java.util.ArrayList;

import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Replica of TechnoUI's ICS volume panel (com.techno.honeycombui.ics.GbVolume): a dark rounded card
 * 80dp below the top edge, min(480dp, screen) wide; holo seek bar per stream, divider and a
 * "more volume controls" button that expands the other streams. Auto-hides after 3s (400ms fade).
 * Artwork is TechnoUI's own (VolArt).
 */
public class VolumePanel {
    static final String TAG = "[IcsVol] ";
    static final Handler H = new Handler(Looper.getMainLooper());
    static final int[] ORDER = {0, 3, 2, 4};       // voice call, media, ring, alarm (same as TechnoUI)

    static Context ctx;
    static AudioManager am;
    static LinearLayout root, sliders;
    static ImageView more;
    static View divider;
    static final ArrayList<Row> rows = new ArrayList<Row>();
    static boolean shown, fading, expanded;
    static int active = -1;
    static BroadcastReceiver rcv;
    static final Runnable HIDE = new Runnable() { @Override public void run() { hide(); } };

    static class Row {
        int stream;
        LinearLayout group;
        ImageView icon;
        SeekBar bar;
    }

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
        total += hookShowRequested(cl);
        XposedBridge.log(TAG + (total == 0 ? "no volume dialog method hooked" : "volume hooks: " + total));
    }

    /**
     * Second, more robust path: the controller dispatches onShowRequested(reason, keyguard, lockTask)
     * to every callback (VolumeDialogControllerImpl$C), and VolumeDialogImpl's own callback calls showH
     * from there. Replace it wherever it exists so the stock panel is never asked to show.
     */
    static int hookShowRequested(ClassLoader cl) {
        java.util.ArrayList<String> cands = new java.util.ArrayList<String>();
        cands.add("com.android.systemui.volume.VolumeDialogControllerImpl$C");
        for (int i = 1; i <= 12; i++) cands.add("com.android.systemui.volume.VolumeDialogControllerImpl$" + i);
        for (int i = 1; i <= 40; i++) cands.add("com.android.systemui.volume.VolumeDialogImpl$" + i);
        int hooked = 0;
        for (String n : cands) {
            Class<?> c = XposedHelpers.findClassIfExists(n, cl);
            if (c == null) continue;
            StringBuilder ms = new StringBuilder();
            for (java.lang.reflect.Method m : c.getDeclaredMethods()) {
                ms.append(m.getName()).append(' ');
                if (!m.getName().equals("onShowRequested")) continue;
                try {
                    XposedBridge.hookMethod(m, new XC_MethodReplacement() {
                        @Override protected Object replaceHookedMethod(MethodHookParam p) {
                            try { show(currentApp()); } catch (Throwable t) { XposedBridge.log(TAG + "show(req): " + t); }
                            return null;
                        }
                    });
                    hooked++;
                    XposedBridge.log(TAG + "hooked " + n + ".onShowRequested");
                } catch (Throwable t) { XposedBridge.log(TAG + "hook failed " + n + ": " + t); }
            }
            if (n.endsWith("$C")) XposedBridge.log(TAG + "methods in " + n + ": " + ms);
        }
        if (hooked == 0) XposedBridge.log(TAG + "onShowRequested not found in controller/dialog callbacks");
        return hooked;
    }

    static Context currentApp() {
        Class<?> at = XposedHelpers.findClass("android.app.ActivityThread", null);
        return (Context) XposedHelpers.callStaticMethod(at, "currentApplication");
    }

    // ---------------------------------------------------------------- show / hide
    static void show(final Context c) {
        XposedBridge.log(TAG + "show requested");
        H.post(new Runnable() {
            @Override public void run() {
                try { showNow(c); } catch (Throwable t) { XposedBridge.log(TAG + "show failed: " + t); }
            }
        });
    }

    static void showNow(Context c) {
        ctx = c;
        am = (AudioManager) c.getSystemService(Context.AUDIO_SERVICE);
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        if (root == null) build(c);
        if (fading) {                                   // came back while fading out
            fading = false;
            root.animate().cancel();
            root.setAlpha(1f);
        }
        int want = pickStream();
        if (!shown) {
            active = want;
            expanded = false;
            reorder();
            try {
                wm.addView(root, params(c));
                shown = true;
            } catch (RuntimeException e) {
                XposedBridge.log(TAG + "addView: " + e);
                return;
            }
            register(c);
        } else if (want != active && !expanded) {
            active = want;
            reorder();
        }
        refresh();
        resetTimer();
    }

    static void hide() {
        H.removeCallbacks(HIDE);
        if (!shown || fading || root == null) return;
        fading = true;
        root.animate().alpha(0f).setDuration(400).setInterpolator(new AccelerateInterpolator(1f))
                .withEndAction(new Runnable() { @Override public void run() { finish(); } }).start();
    }

    static void finish() {
        if (!fading) return;
        fading = false;
        shown = false;
        try { ((WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE)).removeView(root); }
        catch (RuntimeException ignored) {}
        root.setAlpha(1f);
        try { if (rcv != null) ctx.unregisterReceiver(rcv); } catch (RuntimeException ignored) {}
        rcv = null;
    }

    static void resetTimer() {
        H.removeCallbacks(HIDE);
        H.postDelayed(HIDE, 3000);
    }

    static WindowManager.LayoutParams params(Context c) {
        int w = Math.min(dp(c, 480), c.getResources().getDisplayMetrics().widthPixels);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                w, -2, 2038 /* TYPE_APPLICATION_OVERLAY (TechnoUI uses accessibility overlay 2032) */,
                0x40228 /* NOT_FOCUSABLE | NOT_TOUCH_MODAL | LAYOUT_NO_LIMITS | WATCH_OUTSIDE_TOUCH */,
                PixelFormat.TRANSLUCENT);
        lp.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;   // 0x31
        lp.y = dp(c, 80);
        lp.setTitle("TechnoUIVolume");
        return lp;
    }

    static void register(Context c) {
        rcv = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent i) {
                if (!shown) return;
                refresh();
                if (!expanded) resetTimer();
            }
        };
        IntentFilter f = new IntentFilter();
        f.addAction("android.media.VOLUME_CHANGED_ACTION");
        f.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
        try { c.registerReceiver(rcv, f); } catch (Throwable t) { XposedBridge.log(TAG + "receiver: " + t); }
    }

    /** same rules as TechnoUI: in call -> voice, else media if playing, else ring (phones) / media */
    static int pickStream() {
        int mode = am.getMode();
        if (mode == AudioManager.MODE_IN_CALL || mode == AudioManager.MODE_IN_COMMUNICATION) return 0;
        if (am.isMusicActive()) return 3;
        return ctx.getPackageManager().hasSystemFeature("android.hardware.telephony") ? 2 : 3;
    }

    // ---------------------------------------------------------------- UI
    static int dp(Context c, float v) {
        return (int) (TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v, c.getResources().getDisplayMetrics()) + 0.5f);
    }

    static void build(final Context c) {
        Resources res = c.getResources();
        root = new LinearLayout(c);
        root.setOrientation(LinearLayout.HORIZONTAL);
        try { root.setBackground(VolArt.nine(res, "dialog")); } catch (Throwable t) { XposedBridge.log(TAG + "bg: " + t); root.setBackgroundColor(0xE6262626); }
        root.setOnTouchListener(new View.OnTouchListener() {
            @Override public boolean onTouch(View v, MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_OUTSIDE) hide(); else resetTimer();
                return false;
            }
        });

        sliders = new LinearLayout(c);
        sliders.setOrientation(LinearLayout.VERTICAL);
        root.addView(sliders, new LinearLayout.LayoutParams(0, -2, 1f));

        divider = new View(c);
        divider.setBackgroundColor(0x13FFFFFF);          // the 1px divider_vertical_dark pixel
        LinearLayout.LayoutParams dlp = new LinearLayout.LayoutParams(
                Math.max(1, Math.round(res.getDisplayMetrics().density / 1.5f)), dp(c, 32));
        dlp.topMargin = dp(c, 16);
        dlp.bottomMargin = dp(c, 16);
        root.addView(divider, dlp);

        more = new ImageView(c);
        more.setImageDrawable(VolArt.get(res, "more"));
        more.setPadding(dp(c, 16), dp(c, 16), dp(c, 16), dp(c, 16));
        more.setScaleType(ImageView.ScaleType.CENTER);
        more.setContentDescription("More volume controls");
        more.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { expand(); resetTimer(); }
        });
        LinearLayout.LayoutParams mlp = new LinearLayout.LayoutParams(-2, -2);
        mlp.gravity = Gravity.TOP;                        // 0x30
        root.addView(more, mlp);

        rows.clear();
        for (int s : ORDER) rows.add(makeRow(c, s));
    }

    static Row makeRow(final Context c, final int stream) {
        final Row r = new Row();
        r.stream = stream;
        r.group = new LinearLayout(c);
        r.group.setOrientation(LinearLayout.HORIZONTAL);
        r.group.setGravity(0x13);                         // CENTER_VERTICAL | LEFT

        r.icon = new ImageView(c);
        r.icon.setPadding(dp(c, 16), dp(c, 16), dp(c, 16), dp(c, 16));
        r.group.addView(r.icon, new LinearLayout.LayoutParams(-2, -2));

        r.bar = new HoloSeekBar(c);
        r.bar.setPadding(dp(c, 16), dp(c, 16), dp(c, 16), dp(c, 16));
        styleBar(c, r.bar);
        r.bar.setMax(am.getStreamMaxVolume(stream));
        r.bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override public void onProgressChanged(SeekBar b, int p, boolean fromUser) {
                if (!fromUser) return;
                try { am.setStreamVolume(stream, p, 0); } catch (Throwable t) { XposedBridge.log(TAG + "set: " + t); }
                updateIcon(r);
                resetTimer();
            }
            @Override public void onStartTrackingTouch(SeekBar b) { resetTimer(); }
            @Override public void onStopTrackingTouch(SeekBar b) {
                try { am.setStreamVolume(stream, b.getProgress(), AudioManager.FLAG_PLAY_SOUND); } catch (Throwable ignored) {}
                resetTimer();
            }
        });
        LinearLayout.LayoutParams blp = new LinearLayout.LayoutParams(0, -2, 1f);
        blp.rightMargin = dp(c, 16);
        r.group.addView(r.bar, blp);
        r.group.setLayoutParams(new LinearLayout.LayoutParams(-1, dp(c, 68)));
        return r;
    }

    /** gbvol_scrubber_progress (track + scaled primary) and gbvol_scrubber_control (pressed / normal) */
    static void styleBar(Context c, SeekBar bar) {
        Resources res = c.getResources();
        try {
            Drawable track = VolArt.nine(res, "track");
            Drawable prim = new ScaleDrawable(VolArt.nine(res, "primary"), Gravity.LEFT, 1f, -1f);
            LayerDrawable ld = new LayerDrawable(new Drawable[]{track, prim});
            ld.setId(0, android.R.id.background);
            ld.setId(1, android.R.id.progress);
            bar.setProgressDrawable(ld);
        } catch (Throwable t) { XposedBridge.log(TAG + "progress drawable: " + t); }
        StateListDrawable thumb = new StateListDrawable();
        thumb.addState(new int[]{android.R.attr.state_pressed}, VolArt.get(res, "thumb_pressed"));
        thumb.addState(new int[]{}, VolArt.get(res, "thumb"));
        bar.setThumb(thumb);
        // the theme's Material tints would recolour TechnoUI's art
        try {
            bar.setThumbTintList(null);
            bar.setProgressTintList(null);
            bar.setProgressBackgroundTintList(null);
            bar.setSecondaryProgressTintList(null);
            bar.setSplitTrack(false);
        } catch (Throwable ignored) {}
        bar.setThumbOffset(dp(c, 16));
        bar.setMinimumHeight(dp(c, 13));
        bar.setFocusable(true);
    }

    /** TechnoUI's HoloSeekBar: paints a 3dp cyan strip from the left edge to the thumb centre */
    static class HoloSeekBar extends SeekBar {
        final Paint fill = new Paint();
        HoloSeekBar(Context c) { super(c); fill.setColor(0xFF33B5E5); }

        @Override protected synchronized void onDraw(Canvas cv) {
            Drawable th = getThumb();
            if (th != null && isEnabled()) {
                float d = getResources().getDisplayMetrics().density;
                int cx = getPaddingLeft() - getThumbOffset() + th.getBounds().centerX();
                int cy = getPaddingTop() + th.getBounds().centerY();
                float h = Math.max(1f, d * 1.5f);
                if (cx > getPaddingLeft()) cv.drawRect(getPaddingLeft(), cy - h, cx, cy + h, fill);
            }
            super.onDraw(cv);
        }
    }

    // ---------------------------------------------------------------- state
    static Row find(int stream) {
        for (Row r : rows) if (r.stream == stream) return r;
        return null;
    }

    /** active stream first, then the others (voice call only when it is the active one) */
    static void reorder() {
        sliders.removeAllViews();
        Row a = find(active);
        if (a != null) sliders.addView(a.group);
        for (Row r : rows) {
            if (r == a || r.stream == 0) continue;
            sliders.addView(r.group);
        }
        for (Row r : rows) r.bar.setMax(am.getStreamMaxVolume(r.stream));
        collapse();
    }

    static void collapse() {
        expanded = false;
        more.setVisibility(View.VISIBLE);
        divider.setVisibility(View.VISIBLE);
        View first = sliders.getChildAt(0);
        if (first != null) { first.setVisibility(View.VISIBLE); first.setAlpha(1f); }
        for (int i = 1; i < sliders.getChildCount(); i++) sliders.getChildAt(i).setVisibility(View.GONE);
    }

    static void expand() {
        expanded = true;
        for (int i = 0; i < sliders.getChildCount(); i++) sliders.getChildAt(i).setVisibility(View.VISIBLE);
        more.setVisibility(View.INVISIBLE);
        divider.setVisibility(View.INVISIBLE);
        refresh();
    }

    static void refresh() {
        if (am == null) return;
        for (Row r : rows) {
            try { r.bar.setProgress(am.getStreamVolume(r.stream)); } catch (Throwable ignored) {}
            updateIcon(r);
        }
    }

    static void updateIcon(Row r) {
        String k;
        int vol = 0;
        try { vol = am.getStreamVolume(r.stream); } catch (Throwable ignored) {}
        switch (r.stream) {
            case 0:  k = "phone"; break;
            case 4:  k = vol == 0 ? "alarm_mute" : "alarm"; break;
            case 2:
                int rm = am.getRingerMode();
                k = rm == AudioManager.RINGER_MODE_VIBRATE ? "ring_vib"
                        : (rm == AudioManager.RINGER_MODE_SILENT || vol == 0) ? "ring_mute" : "ring";
                break;
            default: k = vol == 0 ? "vol_mute" : "vol";
        }
        r.icon.setImageDrawable(VolArt.get(ctx.getResources(), k));
    }
}
