package ics.recents;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.TextView;

import java.util.HashSet;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * TechnoUI holo look for SystemUI-drawn toasts and dialogs (incl. the power menu, which is a Dialog in
 * SystemUI). v4 also logs what it sees (dialog classes + view trees, keyguard view chain) so the power
 * menu and the unlock ring can be matched precisely in the next build.
 */
public class Holo {
    static final String TAG = "[IcsSysUi] ";
    static final int KEY_SKINNED = 0x1c5ec10d;
    static final boolean SKIN_TOAST = true;
    static final boolean SKIN_DIALOG = true;
    static final HashSet<String> dumped = new HashSet<String>();

    static void hook(ClassLoader cl) {
        if (SKIN_TOAST) hookToast(cl);
        hookDialogs();
        try { LockText.hook(cl); } catch (Throwable t) { XposedBridge.log(TAG + "LockText.hook failed: " + t); }
        try { PowerMenu.hook(cl); } catch (Throwable t) { XposedBridge.log(TAG + "PowerMenu.hook failed: " + t); }
    }

    // ------------------------------------------------------------ toast
    static void hookToast(ClassLoader cl) {
        String[] names = {
            "com.android.systemui.toast.SystemUIToast",
            "com.android.systemui.toast.ToastUI"
        };
        int total = 0;
        for (String n : names) {
            Class<?> c = XposedHelpers.findClassIfExists(n, cl);
            if (c == null) { XposedBridge.log(TAG + "toast: no class " + n); continue; }
            StringBuilder ms = new StringBuilder();
            for (java.lang.reflect.Method m : c.getDeclaredMethods()) ms.append(m.getName()).append(' ');
            XposedBridge.log(TAG + "toast: methods in " + n + ": " + ms);
            if (n.endsWith("SystemUIToast")) {
                try {
                    total += XposedBridge.hookAllConstructors(c, new XC_MethodHook() {
                        @Override protected void afterHookedMethod(MethodHookParam p) {
                            try {
                                Object v = null;
                                try { v = XposedHelpers.getObjectField(p.thisObject, "mToastView"); } catch (Throwable ignored) {}
                                if (v == null) v = XposedHelpers.callMethod(p.thisObject, "getView");
                                XposedBridge.log(TAG + "toast ctor view=" + (v == null ? "null" : v.getClass().getSimpleName()));
                                if (v instanceof View) skinToast((View) v);
                            } catch (Throwable t) { XposedBridge.log(TAG + "toast ctor: " + t); }
                        }
                    }).size();
                    XposedBridge.log(TAG + "toast ctor hooked");
                } catch (Throwable t) { XposedBridge.log(TAG + "toast ctor hook failed: " + t); }
            }
            try {
                total += XposedBridge.hookAllMethods(c, "getView", new XC_MethodHook() {
                    @Override protected void afterHookedMethod(MethodHookParam p) {
                        if (p.getResult() instanceof View) skinToast((View) p.getResult());
                    }
                }).size();
            } catch (Throwable t) { XposedBridge.log(TAG + "toast hook failed: " + t); }
        }
        XposedBridge.log(TAG + "toast hooks: " + total);
    }

    static void skinToast(View v) {
        try {
            if (v.getTag(KEY_SKINNED) != null) return;
            v.setTag(KEY_SKINNED, Boolean.TRUE);
            Drawable d = HoloArt.nine(v.getResources(), "toast");
            v.setClipToOutline(false);
            v.setBackground(d);
            whiteText(v);
            hideIcons(v);
            XposedBridge.log(TAG + "toast skinned " + v.getClass().getSimpleName());
            logTree("toast", v);
        } catch (Throwable t) { XposedBridge.log(TAG + "skinToast: " + t); }
    }

    // ------------------------------------------------------------ dialogs
    static void hookDialogs() {
        try {
            int n = XposedBridge.hookAllMethods(Dialog.class, "show", new XC_MethodHook() {
                @Override protected void beforeHookedMethod(MethodHookParam p) {
                    try {
                        String cn = p.thisObject.getClass().getName();
                        if (PowerMenu.looksLikePowerMenu(cn)) {
                            XposedBridge.log(TAG + "power dialog intercepted: " + cn);
                            PowerMenu.show();
                            p.setResult(null);
                        }
                    } catch (Throwable t) { XposedBridge.log(TAG + "power intercept failed, stock used: " + t); }
                }
                @Override protected void afterHookedMethod(MethodHookParam p) {
                    try {
                        Dialog d = (Dialog) p.thisObject;
                        String cn = d.getClass().getName();
                        Window w = d.getWindow();
                        if (w == null) return;
                        View decor = w.getDecorView();
                        boolean first;
                        synchronized (dumped) { first = dumped.add("dlg:" + cn); }
                        if (first) {
                            XposedBridge.log(TAG + "dialog show " + cn + " ctx=" + d.getContext().getClass().getSimpleName()
                                    + " type=" + w.getAttributes().type);
                            logTree("dialog " + cn.substring(cn.lastIndexOf('.') + 1), decor);
                        }
                        if (SKIN_DIALOG && skippable(cn)) return;
                        if (SKIN_DIALOG) skinDialog(d, w, decor);
                    } catch (Throwable t) { XposedBridge.log(TAG + "dialog show: " + t); }
                }
            }).size();
            XposedBridge.log(TAG + "Dialog.show hooked x" + n);
        } catch (Throwable t) { XposedBridge.log(TAG + "dialog hook failed: " + t); }
    }

    /** dialogs that draw their own full-screen / special UI and must not get a card background */
    static boolean skippable(String cn) {
        return cn.startsWith("ics.recents") || cn.contains("Keyguard") || cn.contains("Bouncer") || cn.contains("Volume")
                || cn.contains("Screenshot") || cn.contains("Biometric");
    }

    static void skinDialog(Dialog d, Window w, View decor) {
        if (decor.getTag(KEY_SKINNED) != null) return;
        decor.setTag(KEY_SKINNED, Boolean.TRUE);
        w.setBackgroundDrawable(HoloArt.nine(decor.getResources(), "dialog_full"));
        clearInnerBackgrounds(decor, 0);
        whiteText(decor);
        flatten(decor);
        // lists (Wi-Fi networks, etc.) fill in after show(): square their cards as they appear
        final View dv = decor;
        dv.getViewTreeObserver().addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {
            long last;
            @Override public void onGlobalLayout() {
                long now = android.os.SystemClock.uptimeMillis();
                if (now - last < 250) return;
                last = now;
                try { flatten(dv); } catch (Throwable ignored) {}
            }
        });
        XposedBridge.log(TAG + "dialog skinned " + d.getClass().getSimpleName());
    }

    static final int KEY_WIDGET = 0x1c5ec10f;
    static final int HOLO_BLUE = 0xFF33B5E5;

    static boolean isSwitch(View v) {
        if (!(v instanceof android.widget.CompoundButton)) return false;
        for (Class<?> c = v.getClass(); c != null; c = c.getSuperclass()) {
            String n = c.getSimpleName();
            if (n.equals("Switch") || n.equals("SwitchCompat")) return true;
        }
        return false;
    }

    static boolean modernBlue(int c) {
        int r = (c >> 16) & 255, b = c & 255, a = (c >>> 24);
        return a > 200 && b > 180 && r < 110 && b - r > 90;
    }

    static void call(View v, String name, Object... args) {
        try { XposedHelpers.callMethod(v, name, args); } catch (Throwable ignored) {}
    }

    static void nullTint(View v, String name, Class<?> type) {
        try { XposedHelpers.callMethod(v, name, new Class<?>[]{type}, new Object[]{null}); } catch (Throwable ignored) {}
    }

    /** ICS holo switch (slanted thumb, ON/OFF on the track), holo blue accents, flat holo buttons */
    static void holoWidgets(View v) {
        if (isSwitch(v)) {
            if (v.getTag(KEY_WIDGET) != null) return;
            v.setTag(KEY_WIDGET, Boolean.TRUE);
            android.content.res.Resources r = v.getResources();
            float d = r.getDisplayMetrics().density;
            nullTint(v, "setThumbTintList", android.content.res.ColorStateList.class);
            nullTint(v, "setTrackTintList", android.content.res.ColorStateList.class);
            nullTint(v, "setTrackDecorationDrawable", Drawable.class);
            nullTint(v, "setThumbIconDrawable", Drawable.class);
            call(v, "setTrackDrawable", HoloArt.switchTrack(r));
            call(v, "setThumbDrawable", HoloArt.switchThumb(r));
            call(v, "setSwitchMinWidth", (int) (72 * d));
            call(v, "setThumbTextPadding", (int) (6 * d));
            call(v, "setShowText", true);
            call(v, "setTextOn", (CharSequence) "ON");
            call(v, "setTextOff", (CharSequence) "OFF");
            try { XposedHelpers.setObjectField(v, "mTextColors", android.content.res.ColorStateList.valueOf(0xFFFFFFFF)); } catch (Throwable ignored) {}
            v.requestLayout();
            XposedBridge.log(TAG + "dialog switch styled " + v.getClass().getSimpleName());
            return;
        }
        if (v instanceof TextView) {
            TextView t = (TextView) v;
            if (modernBlue(t.getCurrentTextColor())) t.setTextColor(HOLO_BLUE);
            if (v instanceof android.widget.Button && v.getTag(KEY_WIDGET) == null) {
                v.setTag(KEY_WIDGET, Boolean.TRUE);
                android.graphics.drawable.StateListDrawable s = new android.graphics.drawable.StateListDrawable();
                s.addState(new int[]{android.R.attr.state_pressed}, new android.graphics.drawable.ColorDrawable(0x8033B5E5));
                s.addState(new int[]{android.R.attr.state_focused}, new android.graphics.drawable.ColorDrawable(0x4033B5E5));
                s.addState(new int[]{}, new android.graphics.drawable.ColorDrawable(0x00000000));
                v.setBackground(s);
                try { v.setStateListAnimator(null); } catch (Throwable ignored) {}
                if (!modernBlue(t.getCurrentTextColor()) && t.getCurrentTextColor() != HOLO_BLUE) t.setTextColor(0xFFFFFFFF);
                XposedBridge.log(TAG + "dialog button styled " + v.getClass().getSimpleName());
            }
        }
    }

    /** square every rounded card background in the tree (any depth), no clipping to rounded outlines */
    static void flatten(View v) {
        try { holoWidgets(v); } catch (Throwable t) { XposedBridge.log(TAG + "holoWidgets: " + t); }
        Drawable bg = v.getBackground();
        if (bg != null) LockText.square(bg, 0);
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            if (g.getClipToOutline()) g.setClipToOutline(false);
            for (int i = 0; i < g.getChildCount(); i++) flatten(g.getChildAt(i));
        }
    }

    /** flatten the modern rounded/tinted panels inside the dialog so the holo frame shows through */
    static void clearInnerBackgrounds(View v, int depth) {
        if (!(v instanceof ViewGroup) || v instanceof AbsListView) return;
        ViewGroup g = (ViewGroup) v;
        if (depth > 0 && depth < 5) {
            Drawable bg = g.getBackground();
            if (bg != null && (bg instanceof android.graphics.drawable.GradientDrawable
                    || bg instanceof android.graphics.drawable.ColorDrawable
                    || bg instanceof android.graphics.drawable.InsetDrawable
                    || bg instanceof android.graphics.drawable.LayerDrawable)) {
                g.setBackground(null);
            }
            g.setClipToOutline(false);
        }
        for (int i = 0; i < g.getChildCount(); i++) clearInnerBackgrounds(g.getChildAt(i), depth + 1);
    }

    /** ICS toasts had no app icon */
    static void hideIcons(View v) {
        if (v instanceof android.widget.ImageView) { v.setVisibility(View.GONE); return; }
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            for (int i = 0; i < g.getChildCount(); i++) hideIcons(g.getChildAt(i));
        }
    }

    static void whiteText(View v) {
        if (v instanceof TextView) {
            TextView t = (TextView) v;
            t.setTextColor(0xFFFFFFFF);
            try { t.setTypeface(IcsFont.get(t.getContext())); } catch (Throwable ignored) {}
        }
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            for (int i = 0; i < g.getChildCount(); i++) whiteText(g.getChildAt(i));
        }
    }

    // ------------------------------------------------------------ discovery
    static String idName(View v) {
        try {
            if (v.getId() == View.NO_ID) return "-";
            return v.getResources().getResourceEntryName(v.getId());
        } catch (Throwable t) { return "?"; }
    }

    static void logTree(String label, View root) {
        StringBuilder sb = new StringBuilder();
        int[] count = {0};
        walk(root, 0, sb, count);
        // chunk so one line never gets cut off by the logger
        String s = sb.toString();
        for (int i = 0; i < s.length(); i += 700)
            XposedBridge.log(TAG + "tree[" + label + "] " + s.substring(i, Math.min(s.length(), i + 700)));
    }

    static void walk(View v, int depth, StringBuilder sb, int[] count) {
        if (count[0]++ > 70 || depth > 7) return;
        sb.append(depth).append(':').append(v.getClass().getSimpleName()).append('#').append(idName(v));
        Drawable bg = v.getBackground();
        if (bg != null) sb.append("(bg=").append(bg.getClass().getSimpleName()).append(')');
        if (v instanceof TextView) {
            CharSequence t = ((TextView) v).getText();
            if (t != null && t.length() > 0) sb.append("[\"").append(t.length() > 18 ? t.subSequence(0, 18) : t).append("\"]");
        }
        sb.append(" | ");
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            for (int i = 0; i < g.getChildCount(); i++) walk(g.getChildAt(i), depth + 1, sb, count);
        }
    }

    static boolean textDumped;

    /** one-time dump of what the lock screen really contains: every visible text and the first notification cards */
    static void lockTextDump(View top) {
        try {
            if (textDumped) return;
            textDumped = true;
            StringBuilder sb = new StringBuilder();
            int[] n = {0};
            collectText(top, sb, n);
            for (int i = 0; i < sb.length(); i += 700)
                XposedBridge.log(TAG + "locktext " + sb.substring(i, Math.min(sb.length(), i + 700)));
            java.util.ArrayList<View> rows = new java.util.ArrayList<View>();
            findByClass(top, "ExpandableNotificationRow", rows, 2);
            XposedBridge.log(TAG + "locktext rows found: " + rows.size());
            for (int i = 0; i < rows.size(); i++) logTree("lockrow" + i, rows.get(i));
        } catch (Throwable t) { XposedBridge.log(TAG + "lockTextDump: " + t); }
    }

    static void collectText(View v, StringBuilder sb, int[] n) {
        if (n[0] > 60) return;
        if (v instanceof TextView && v.isShown()) {
            CharSequence t = ((TextView) v).getText();
            if (t != null && t.length() > 0) {
                n[0]++;
                sb.append(idName(v)).append('#').append(v.getClass().getSimpleName()).append(" \"")
                  .append(t.length() > 22 ? t.subSequence(0, 22) : t).append("\" ")
                  .append((int) (((TextView) v).getTextSize() / v.getResources().getDisplayMetrics().scaledDensity)).append("sp")
                  .append(" < ").append(v.getParent() instanceof View ? idName((View) v.getParent()) : "-").append(" | ");
            }
        }
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            for (int i = 0; i < g.getChildCount(); i++) collectText(g.getChildAt(i), sb, n);
        }
    }

    static void findByClass(View v, String simple, java.util.ArrayList<View> out, int max) {
        if (out.size() >= max) return;
        if (v.getClass().getSimpleName().equals(simple)) { out.add(v); return; }
        if (v instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) v;
            for (int i = 0; i < g.getChildCount(); i++) findByClass(g.getChildAt(i), simple, out, max);
        }
    }

    static final HashSet<String> lockProbed = new HashSet<String>();

    /** called when the lock screen clock attaches: log the view chain and the siblings along it (where a ring could live) */
    static void lockProbe(View clock) {
        try {
            synchronized (lockProbed) { if (!lockProbed.add(clock.getClass().getName() + clock.getId())) return; }
            StringBuilder chain = new StringBuilder();
            View top = clock;
            android.view.ViewParent p = clock.getParent();
            while (p instanceof View) {
                top = (View) p;
                chain.append(top.getClass().getSimpleName()).append('#').append(idName(top)).append(" < ");
                p = p.getParent();
            }
            XposedBridge.log(TAG + "lockprobe chain: " + chain);
            LockNotifs.attach(top);
            LockRing.attach(top);
            final View tv = top;
            top.postDelayed(new Runnable() { @Override public void run() { lockTextDump(tv); } }, 2500);
            if (top instanceof ViewGroup) {
                ViewGroup g = (ViewGroup) top;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < g.getChildCount(); i++) {
                    View c = g.getChildAt(i);
                    sb.append(c.getClass().getSimpleName()).append('#').append(idName(c))
                      .append(' ').append(c.getWidth()).append('x').append(c.getHeight()).append(" | ");
                }
                XposedBridge.log(TAG + "lockprobe root " + top.getClass().getName() + " children: " + sb);
            }
        } catch (Throwable t) { XposedBridge.log(TAG + "lockProbe: " + t); }
    }
}
