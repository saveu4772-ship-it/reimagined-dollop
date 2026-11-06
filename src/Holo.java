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
            XposedBridge.log(TAG + "toast skinned " + v.getClass().getSimpleName());
            logTree("toast", v);
        } catch (Throwable t) { XposedBridge.log(TAG + "skinToast: " + t); }
    }

    // ------------------------------------------------------------ dialogs
    static void hookDialogs() {
        try {
            int n = XposedBridge.hookAllMethods(Dialog.class, "show", new XC_MethodHook() {
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
        return cn.contains("Keyguard") || cn.contains("Bouncer") || cn.contains("Volume")
                || cn.contains("Screenshot") || cn.contains("Biometric");
    }

    static void skinDialog(Dialog d, Window w, View decor) {
        if (decor.getTag(KEY_SKINNED) != null) return;
        decor.setTag(KEY_SKINNED, Boolean.TRUE);
        w.setBackgroundDrawable(HoloArt.nine(decor.getResources(), "dialog_full"));
        clearInnerBackgrounds(decor, 0);
        whiteText(decor);
        XposedBridge.log(TAG + "dialog skinned " + d.getClass().getSimpleName());
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
