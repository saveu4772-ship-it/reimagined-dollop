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

    /** zero the corner radius of notification card backgrounds */
    static void squareCards(ClassLoader cl) {
        Class<?> c = XposedHelpers.findClassIfExists("com.android.systemui.statusbar.notification.row.NotificationBackgroundView", cl);
        if (c == null) { XposedBridge.log(TAG + "cards: NotificationBackgroundView not found"); return; }
        int n = 0;
        StringBuilder names = new StringBuilder();
        for (Method m : c.getDeclaredMethods()) {
            String nm = m.getName().toLowerCase();
            if (!(nm.contains("radius") || nm.contains("roundness"))) continue;
            Class<?>[] pt = m.getParameterTypes();
            if (pt.length == 0) continue;
            boolean allFloat = true;
            for (Class<?> x : pt) if (x != float.class) allFloat = false;
            names.append(m.getName()).append('/').append(pt.length).append(allFloat ? "f " : " ");
            if (!allFloat) continue;
            try {
                XposedBridge.hookMethod(m, new XC_MethodHook() {
                    @Override protected void beforeHookedMethod(MethodHookParam p) {
                        for (int i = 0; i < p.args.length; i++) p.args[i] = 0f;
                    }
                });
                n++;
            } catch (Throwable t) { XposedBridge.log(TAG + "cards hook " + m.getName() + ": " + t); }
        }
        XposedBridge.log(TAG + "cards: radius methods " + names + "-> hooked " + n);
    }
}
