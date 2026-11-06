package ics.recents;

import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;

import java.lang.reflect.Method;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * JB/ICS look for the lock screen's text and notification cards (ids/classes taken from the v6.1 probe):
 * date line = small uppercase Roboto, carrier = uppercase Roboto, media + battery text = Roboto,
 * notification cards = square corners with Roboto text.
 */
public class LockText {
    static final String TAG = "[IcsSysUi] ";
    static final int KEY = 0x1c5ec10e;
    static final boolean DATE_CAPS = true;
    static final int DATE_SP = 14;

    static void hook(ClassLoader cl) {
        try {
            XposedBridge.hookAllMethods(TextView.class, "onAttachedToWindow", new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam p) {
                    try { style((TextView) p.thisObject); } catch (Throwable ignored) {}
                }
            });
            XposedBridge.log(TAG + "locktext: TextView hook installed");
        } catch (Throwable t) { XposedBridge.log(TAG + "locktext hook failed: " + t); }
        squareCards(cl);
    }

    static boolean inRow(View v) {
        ViewParent p = v.getParent();
        for (int i = 0; i < 16 && p != null; i++, p = p.getParent())
            if (p.getClass().getSimpleName().equals("ExpandableNotificationRow")) return true;
        return false;
    }

    static void style(TextView tv) {
        if (tv.getTag(KEY) != null) return;
        String cn = tv.getClass().getName();
        String id = Holo.idName(tv);
        Typeface f = null;
        if (cn.contains("KeyguardSliceTextView")) {
            if (DATE_CAPS) tv.setAllCaps(true);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, DATE_SP);
            f = IcsFont.get(tv.getContext());
            log("date", cn);
        } else if (id.equals("keyguard_carrier_text")) {
            tv.setAllCaps(true);
            f = IcsFont.get(tv.getContext());
            log("carrier", cn);
        } else if (id.equals("media_seamless_text") || id.equals("header_title") || id.equals("header_artist")
                || id.equals("battery_percentage_view")) {
            f = IcsFont.get(tv.getContext());
            log(id, cn);
        } else if (inRow(tv)) {
            f = IcsFont.get(tv.getContext());
            log("notification text", cn);
        } else return;
        tv.setTag(KEY, Boolean.TRUE);
        if (f != null) tv.setTypeface(f);
    }

    static final java.util.HashSet<String> logged = new java.util.HashSet<String>();

    static void log(String what, String cn) {
        synchronized (logged) { if (logged.add(what)) XposedBridge.log(TAG + "locktext styled " + what + " (" + cn + ")"); }
    }

    /** zero the corner radius of notification card backgrounds: v6.3 squares the background shape right before it draws */
    static void squareCards(ClassLoader cl) {
        Class<?> c = XposedHelpers.findClassIfExists("com.android.systemui.statusbar.notification.row.NotificationBackgroundView", cl);
        if (c == null) { XposedBridge.log(TAG + "cards: NotificationBackgroundView not found"); return; }
        StringBuilder names = new StringBuilder();
        for (Method m : c.getDeclaredMethods()) names.append(m.getName()).append(' ');
        XposedBridge.log(TAG + "cards: methods " + names);
        StringBuilder fields = new StringBuilder();
        for (java.lang.reflect.Field f : c.getDeclaredFields()) fields.append(f.getName()).append(':').append(f.getType().getSimpleName()).append(' ');
        XposedBridge.log(TAG + "cards: fields " + fields);
        int n = 0;
        String[] draws = {"draw", "onDraw", "dispatchDraw"};
        for (String d : draws) {
            try {
                n += XposedBridge.hookAllMethods(c, d, new XC_MethodHook() {
                    @Override protected void beforeHookedMethod(MethodHookParam p) {
                        try {
                            Object bg = XposedHelpers.getObjectField(p.thisObject, "mBackground");
                            if (bg instanceof android.graphics.drawable.Drawable) square((android.graphics.drawable.Drawable) bg, 0);
                        } catch (Throwable t) { warn("cards draw: " + t); }
                    }
                }).size();
            } catch (Throwable t) { XposedBridge.log(TAG + "cards hook " + d + ": " + t); }
        }
        XposedBridge.log(TAG + "cards: draw hooks " + n);
    }

    static boolean warned;
    static void warn(String s) { if (!warned) { warned = true; XposedBridge.log(TAG + s); } }

    /** remove rounded corners from a (possibly layered/wrapped) background drawable, only if it still has any */
    static void square(android.graphics.drawable.Drawable d, int depth) {
        if (d == null || depth > 4) return;
        if (d instanceof android.graphics.drawable.GradientDrawable) {
            android.graphics.drawable.GradientDrawable g = (android.graphics.drawable.GradientDrawable) d;
            boolean round = g.getCornerRadius() != 0f;
            float[] r = g.getCornerRadii();
            if (r != null) for (float v : r) if (v != 0f) round = true;
            if (round) { g.setCornerRadii(new float[8]); g.setCornerRadius(0f); }
        } else if (d instanceof android.graphics.drawable.LayerDrawable) {
            android.graphics.drawable.LayerDrawable l = (android.graphics.drawable.LayerDrawable) d;
            for (int i = 0; i < l.getNumberOfLayers(); i++) square(l.getDrawable(i), depth + 1);
        } else if (d instanceof android.graphics.drawable.DrawableWrapper) {
            square(((android.graphics.drawable.DrawableWrapper) d).getDrawable(), depth + 1);
        }
    }
}
