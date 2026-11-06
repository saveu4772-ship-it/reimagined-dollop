package ics.recents;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Enumeration;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Jelly Bean style power menu (Power off / Reboot / Airplane mode / sound mode row) in the TechnoUI holo
 * dialog frame. It replaces realme's slider power menu. Matched by class (GlobalActions*) and, as a fallback,
 * by any SystemUI Dialog whose class name looks like a power menu (see Holo.hookDialogs).
 */
public class PowerMenu {
    static final String TAG = "[IcsSysUi] ";
    static final int BLUE = 0xFF33B5E5;
    static final Handler H = new Handler(Looper.getMainLooper());
    static Dialog current;

    static boolean looksLikePowerMenu(String cn) {
        if (cn.startsWith("ics.recents")) return false;
        String l = cn.toLowerCase();
        return l.contains("globalactions") || l.contains("powermenu") || l.contains("poweroffdialog")
                || l.contains("shutdowndialog") || l.contains("powerdialog");
    }

    // ------------------------------------------------------------ hooks
    static void hook(ClassLoader cl) {
        String[][] targets = {
            {"com.android.systemui.globalactions.GlobalActionsImpl", "showGlobalActions"},
            {"com.android.systemui.globalactions.GlobalActionsComponent", "handleShowGlobalActionsMenu"},
            {"com.android.systemui.globalactions.GlobalActionsDialogLite", "showOrHideDialog"},
        };
        int total = 0;
        for (String[] t : targets) {
            Class<?> c = XposedHelpers.findClassIfExists(t[0], cl);
            if (c == null) { XposedBridge.log(TAG + "power: no class " + t[0]); continue; }
            try {
                total += XposedBridge.hookAllMethods(c, t[1], new XC_MethodHook() {
                    @Override protected void beforeHookedMethod(MethodHookParam p) {
                        try {
                            show();
                            p.setResult(null);              // stock menu never shows
                        } catch (Throwable e) {
                            XposedBridge.log(TAG + "power show failed, stock menu used: " + e);
                        }
                    }
                }).size();
                XposedBridge.log(TAG + "power: hooked " + t[0] + "." + t[1]);
            } catch (Throwable e) { XposedBridge.log(TAG + "power hook failed " + t[1] + ": " + e); }
        }
        XposedBridge.log(TAG + "power hooks: " + total);
        scan(cl);
    }

    /** discovery: list SystemUI classes that look like a power menu, so the next build can match exactly */
    static void scan(final ClassLoader cl) {
        new Thread(new Runnable() {
            @Override public void run() {
                try {
                    Object list = XposedHelpers.getObjectField(cl, "pathList");
                    Object[] els = (Object[]) XposedHelpers.getObjectField(list, "dexElements");
                    java.util.regex.Pattern pat = java.util.regex.Pattern.compile(
                            "(?i)globalaction|powermenu|poweroff|shutdown|powerdialog|emergencydialog");
                    StringBuilder sb = new StringBuilder();
                    int n = 0;
                    for (Object e : els) {
                        Object df = XposedHelpers.getObjectField(e, "dexFile");
                        if (!(df instanceof dalvik.system.DexFile)) continue;
                        Enumeration<String> en = ((dalvik.system.DexFile) df).entries();
                        while (en.hasMoreElements() && n < 80) {
                            String name = en.nextElement();
                            if (name.indexOf('$') >= 0 || !pat.matcher(name).find()) continue;
                            sb.append(name).append(' ');
                            if (++n % 4 == 0) { XposedBridge.log(TAG + "power classes: " + sb); sb.setLength(0); }
                        }
                    }
                    if (sb.length() > 0) XposedBridge.log(TAG + "power classes: " + sb);
                    XposedBridge.log(TAG + "power scan done, " + n + " classes");
                } catch (Throwable t) { XposedBridge.log(TAG + "power scan failed: " + t); }
            }
        }, "ics-pm-scan").start();
    }

    // ------------------------------------------------------------ ui
    static Context app() {
        Class<?> at = XposedHelpers.findClass("android.app.ActivityThread", null);
        return (Context) XposedHelpers.callStaticMethod(at, "currentApplication");
    }

    static float dp;
    static int dp(float v) { return (int) (v * dp + 0.5f); }

    static ImageView[] soundIcons = new ImageView[3];
    static View[] soundBars = new View[3];
    static ImageView airIcon;
    static TextView airSub;

    static void show() {
        final Context c = app();
        dp = c.getResources().getDisplayMetrics().density;
        if (current != null) { try { current.dismiss(); } catch (Throwable ignored) {} current = null; return; }
        final Dialog d = new Dialog(c, android.R.style.Theme_DeviceDefault_Dialog_NoActionBar) { };
        Window w = d.getWindow();
        w.setType(2009);                                    // TYPE_KEYGUARD_DIALOG: works over the lock screen
        w.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        w.setDimAmount(0.6f);
        w.setBackgroundDrawable(HoloArt.nine(c.getResources(), "dialog_full"));
        d.setContentView(build(c, d));
        android.util.DisplayMetrics dm = c.getResources().getDisplayMetrics();
        int width = Math.min((int) (dm.widthPixels * 0.9f), dp(340));
        w.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        w.setGravity(Gravity.CENTER);
        d.setCanceledOnTouchOutside(true);
        d.setOnDismissListener(new android.content.DialogInterface.OnDismissListener() {
            @Override public void onDismiss(android.content.DialogInterface x) { current = null; }
        });
        current = d;
        d.show();
        XposedBridge.log(TAG + "power menu shown");
    }

    static View build(final Context c, final Dialog d) {
        LinearLayout col = new LinearLayout(c);
        col.setOrientation(LinearLayout.VERTICAL);

        col.addView(row(c, new PowerIcon(dp), "Power off", null, new View.OnClickListener() {
            @Override public void onClick(View v) { d.dismiss(); later(0); }
        }));
        col.addView(divider(c));
        col.addView(row(c, HoloArt.plain(c.getResources(), "pm_warn"), "Reboot", null, new View.OnClickListener() {
            @Override public void onClick(View v) { d.dismiss(); later(1); }
        }));
        col.addView(divider(c));
        View air = row(c, HoloArt.plain(c.getResources(), "pm_air"), "Airplane mode", "", new View.OnClickListener() {
            @Override public void onClick(View v) { setAirplane(c, !isAirplane(c)); H.postDelayed(new Runnable() {
                @Override public void run() { refresh(c); } }, 250); refresh(c); }
        });
        airIcon = (ImageView) ((ViewGroup) air).getChildAt(0);
        airSub = (TextView) ((ViewGroup) ((ViewGroup) air).getChildAt(1)).getChildAt(1);
        col.addView(air);
        col.addView(divider(c));

        LinearLayout snd = new LinearLayout(c);
        snd.setOrientation(LinearLayout.HORIZONTAL);
        String[] keys = {"pm_silent", "pm_vibrate", "vol"};
        final int[] modes = {AudioManager.RINGER_MODE_SILENT, AudioManager.RINGER_MODE_VIBRATE, AudioManager.RINGER_MODE_NORMAL};
        for (int i = 0; i < 3; i++) {
            final int mode = modes[i];
            LinearLayout cell = new LinearLayout(c);
            cell.setOrientation(LinearLayout.VERTICAL);
            cell.setGravity(Gravity.CENTER);
            cell.setBackground(pressed());
            ImageView iv = new ImageView(c);
            iv.setImageDrawable(keys[i].equals("vol") ? VolArt.get(c.getResources(), "vol") : HoloArt.plain(c.getResources(), keys[i]));
            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
            cell.addView(iv, new LinearLayout.LayoutParams(dp(32), dp(32)));
            View bar = new View(c);
            bar.setBackgroundColor(BLUE);
            LinearLayout.LayoutParams bl = new LinearLayout.LayoutParams(dp(44), dp(3));
            bl.topMargin = dp(6);
            cell.addView(bar, bl);
            cell.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    try { ((AudioManager) c.getSystemService(Context.AUDIO_SERVICE)).setRingerMode(mode); }
                    catch (Throwable t) { XposedBridge.log(TAG + "ringer: " + t); }
                    refresh(c);
                }
            });
            soundIcons[i] = iv;
            soundBars[i] = bar;
            snd.addView(cell, new LinearLayout.LayoutParams(0, dp(64), 1f));
        }
        col.addView(snd);
        refresh(c);
        return col;
    }

    static void refresh(Context c) {
        try {
            int rm = ((AudioManager) c.getSystemService(Context.AUDIO_SERVICE)).getRingerMode();
            int active = rm == AudioManager.RINGER_MODE_SILENT ? 0 : rm == AudioManager.RINGER_MODE_VIBRATE ? 1 : 2;
            for (int i = 0; i < 3; i++) {
                boolean on = i == active;
                soundIcons[i].setColorFilter(on ? 0xFFFFFFFF : 0x99FFFFFF, PorterDuff.Mode.SRC_IN);
                soundBars[i].setVisibility(on ? View.VISIBLE : View.INVISIBLE);
            }
            boolean air = isAirplane(c);
            airIcon.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
            airSub.setText("Airplane mode is " + (air ? "ON" : "OFF"));
        } catch (Throwable t) { XposedBridge.log(TAG + "power refresh: " + t); }
    }

    static Drawable pressed() {
        StateListDrawable s = new StateListDrawable();
        s.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(0x6633B5E5));
        s.addState(new int[]{}, new ColorDrawable(0x00000000));
        return s;
    }

    static View divider(Context c) {
        View v = new View(c);
        v.setBackgroundColor(0x33FFFFFF);
        v.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Math.max(1, dp(1))));
        return v;
    }

    static View row(Context c, Drawable icon, String title, String sub, View.OnClickListener l) {
        LinearLayout r = new LinearLayout(c);
        r.setOrientation(LinearLayout.HORIZONTAL);
        r.setGravity(Gravity.CENTER_VERTICAL);
        r.setMinimumHeight(dp(68));
        r.setPadding(dp(16), 0, dp(16), 0);
        r.setBackground(pressed());
        r.setOnClickListener(l);
        ImageView iv = new ImageView(c);
        iv.setImageDrawable(icon);
        iv.setColorFilter(0xFFFFFFFF, PorterDuff.Mode.SRC_IN);
        iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
        r.addView(iv, new LinearLayout.LayoutParams(dp(32), dp(32)));
        LinearLayout t = new LinearLayout(c);
        t.setOrientation(LinearLayout.VERTICAL);
        TextView a = new TextView(c);
        a.setText(title);
        a.setTextColor(0xFFFFFFFF);
        a.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        try { a.setTypeface(IcsFont.get(c)); } catch (Throwable ignored) {}
        t.addView(a);
        TextView b = new TextView(c);
        b.setTextColor(0xFFAAAAAA);
        b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        try { b.setTypeface(IcsFont.get(c)); } catch (Throwable ignored) {}
        if (sub == null) b.setVisibility(View.GONE); else b.setText(sub);
        t.addView(b);
        LinearLayout.LayoutParams tl = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        tl.leftMargin = dp(16);
        r.addView(t, tl);
        return r;
    }

    /** the Android power symbol: open ring + stem, white strokes */
    static class PowerIcon extends Drawable {
        final Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        final float d;
        PowerIcon(float density) {
            d = density;
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setColor(0xFFFFFFFF);
            p.setStrokeWidth(3f * density);
        }
        @Override public void draw(Canvas c) {
            float w = getBounds().width(), h = getBounds().height();
            float cx = getBounds().left + w / 2f, cy = getBounds().top + h / 2f + h * 0.04f;
            float r = Math.min(w, h) * 0.36f;
            c.drawArc(new RectF(cx - r, cy - r, cx + r, cy + r), -60, 300, false, p);
            c.drawLine(cx, getBounds().top + h * 0.10f, cx, cy - r * 0.15f, p);
        }
        @Override public void setAlpha(int a) { p.setAlpha(a); }
        @Override public void setColorFilter(ColorFilter f) { p.setColorFilter(f); }
        @Override public int getOpacity() { return PixelFormat.TRANSLUCENT; }
        @Override public int getIntrinsicWidth() { return (int) (32 * d); }
        @Override public int getIntrinsicHeight() { return (int) (32 * d); }
    }

    // ------------------------------------------------------------ actions
    static boolean isAirplane(Context c) {
        try { return Settings.Global.getInt(c.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) != 0; }
        catch (Throwable t) { return false; }
    }

    static void setAirplane(Context c, boolean on) {
        try {
            Object cm = c.getSystemService(Context.CONNECTIVITY_SERVICE);
            XposedHelpers.callMethod(cm, "setAirplaneMode", on);
            XposedBridge.log(TAG + "airplane -> " + on);
        } catch (Throwable t) {
            try {
                Settings.Global.putInt(c.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, on ? 1 : 0);
                Intent i = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
                i.putExtra("state", on);
                c.sendBroadcast(i);
            } catch (Throwable t2) { XposedBridge.log(TAG + "airplane failed: " + t + " / " + t2); }
        }
    }

    /** 0 = power off, 1 = reboot. Uses the system confirmation (confirm=true), same as stock. */
    static void later(final int what) {
        H.postDelayed(new Runnable() {
            @Override public void run() {
                try {
                    Class<?> sm = XposedHelpers.findClass("android.os.ServiceManager", null);
                    IBinder b = (IBinder) XposedHelpers.callStaticMethod(sm, "getService", "power");
                    Class<?> stub = XposedHelpers.findClass("android.os.IPowerManager$Stub", null);
                    Object ipm = XposedHelpers.callStaticMethod(stub, "asInterface", b);
                    if (what == 0) {
                        XposedHelpers.callMethod(ipm, "shutdown", new Class<?>[]{boolean.class, String.class, boolean.class},
                                true, null, false);
                    } else {
                        XposedHelpers.callMethod(ipm, "reboot", new Class<?>[]{boolean.class, String.class, boolean.class},
                                true, null, false);
                    }
                    XposedBridge.log(TAG + "power action " + what + " sent");
                } catch (Throwable t) {
                    XposedBridge.log(TAG + "power action failed: " + t);
                    try {
                        android.os.PowerManager pm = (android.os.PowerManager) app().getSystemService(Context.POWER_SERVICE);
                        if (what == 1) pm.reboot(null);
                    } catch (Throwable t2) { XposedBridge.log(TAG + "reboot fallback failed: " + t2); }
                }
            }
        }, 200);
    }
}
