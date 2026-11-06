package ics.recents;

import android.content.res.XResources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.IXposedHookZygoteInit;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/** ICS-style nav bar icons, incl. the keyboard-open (IME) back icon. */
public class SysUi implements IXposedHookZygoteInit, IXposedHookInitPackageResources, IXposedHookLoadPackage {
    static final String TAG = "[IcsSysUi] ";
    static final String PKG = "com.android.systemui";
    static final int HINT_BACK_ALT = 1; // StatusBarManager.NAVIGATION_HINT_BACK_ALT

    // ---- resources ----
    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam p) {
        if (!PKG.equals(p.packageName)) return;
        XposedBridge.log(TAG + "init resources in " + p.packageName);
        replace(p, "ic_sysbar_back", 0);
        replace(p, "ic_sysbar_back_quick_step", 0);
        replace(p, "ic_sysbar_home", 1);
        replace(p, "ic_sysbar_home_quick_step", 1);
        replace(p, "ic_sysbar_recent", 2);
        replace(p, "ic_sysbar_back_ime", 3);
        for (String[] m : KK) replaceKk(p, m[0], m[1]);
        for (String n : new String[]{"stat_sys_ringer_silent", "stat_sys_ringer_silent_mute"}) {
            try {
                p.res.setReplacement(PKG, "drawable", n, new XResources.DrawableLoader() {
                    @Override public Drawable newDrawable(XResources res, int id) { return KkExtra.silent(res); }
                });
                XposedBridge.log(TAG + "kk replaced " + n);
            } catch (Throwable t) { XposedBridge.log(TAG + "kk skip " + n + ": " + t); }
        }
    }


    // ---- KitKat status bar icons: realme resource name -> KkIcons key ----
    static final String[][] KK = {
        {"ic_mobile_strength_0","sig0"},{"ic_mobile_strength_1","sig1"},{"ic_mobile_strength_2","sig2"},
        {"ic_mobile_strength_3","sig3"},{"ic_mobile_strength_4","sig4"},
        {"ic_mobile_call_strength_0","sig0"},{"ic_mobile_call_strength_1","sig1"},{"ic_mobile_call_strength_2","sig2"},
        {"ic_mobile_call_strength_3","sig3"},{"ic_mobile_call_strength_4","sig4"},
        {"stat_signal_wifi_signal_0","wifi0"},{"stat_signal_wifi_signal_1","wifi1"},{"stat_signal_wifi_signal_2","wifi2"},
        {"stat_signal_wifi_signal_3","wifi3"},{"stat_signal_wifi_signal_4","wifi4"},
        {"stat_signal_activity_wifi_in","wifi_in"},{"stat_signal_activity_wifi_out","wifi_out"},
        {"stat_signal_activity_wifi_inout","wifi_inout"},
        {"ic_3g_mobiledata","d3g"},{"ic_4g_mobiledata","d4g"},{"ic_4g_lte_mobiledata","d4g"},
        {"ic_lte_mobiledata","d4g"},{"ic_e_mobiledata","de"},{"ic_g_mobiledata","dg"},
        {"ic_h_mobiledata","dh"},{"ic_h_plus_mobiledata","dh"},{"ic_1x_mobiledata","d1x"},
        {"stat_sys_data_bluetooth","bt"},{"stat_sys_data_bluetooth_connected","btc"},
        {"stat_sys_airplane_mode","air"},
    };

    static void replaceKk(XC_InitPackageResources.InitPackageResourcesParam p, String name, final String key) {
        try {
            p.res.setReplacement(PKG, "drawable", name, new XResources.DrawableLoader() {
                @Override public Drawable newDrawable(XResources res, int id) throws Throwable {
                    Drawable d = KkIcons.get(res, key);
                    if (d == null) throw new IllegalStateException("no icon " + key);
                    return d;
                }
            });
            XposedBridge.log(TAG + "kk replaced " + name);
        } catch (Throwable t) {
            XposedBridge.log(TAG + "kk skip " + name + ": " + t);
        }
    }

    static void replace(XC_InitPackageResources.InitPackageResourcesParam p, String name, final int kind) {
        try {
            p.res.setReplacement(PKG, "drawable", name, new XResources.DrawableLoader() {
                @Override public Drawable newDrawable(XResources res, int id) throws Throwable {
                    return IcsIcons.get(res, kind);
                }
            });
            XposedBridge.log(TAG + "replaced " + name);
        } catch (Throwable t) {
            XposedBridge.log(TAG + "skip " + name + ": " + t);
        }
    }

    // ---- code hooks: keyboard-open back icon ----
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lp) {
        XposedBridge.log(TAG + "BUILD v7 (shade-style lock notifications, Holo_Lock glowpad ring, swipe unlock + gray holo + power menu) loaded in " + lp.processName);
        hookEdge();                       // overscroll glow in whichever scoped app this is
        if (!PKG.equals(lp.packageName) || !lp.processName.equals(PKG)) return;
        // dumpNames(lp.classLoader);   // discovery only; re-enable if you need new names
        hookBattery(lp.classLoader);
        VolumePanel.hook(lp.classLoader);
        hookLockClock(lp.classLoader);
        hookClock(lp.classLoader);
        QsPanel.hookStatusBar(lp.classLoader);
        hookNavGlow(lp.classLoader);
        try { Holo.hook(lp.classLoader); } catch (Throwable t) { XposedBridge.log(TAG + "Holo.hook failed: " + t); }
        Class<?> nav = null;
        String[] names = {
            "com.android.systemui.navigationbar.views.NavigationBarView",
            "com.android.systemui.navigationbar.NavigationBarView",
            "com.android.systemui.statusbar.phone.NavigationBarView"
        };
        for (String n : names) {
            nav = XposedHelpers.findClassIfExists(n, lp.classLoader);
            if (nav != null) { XposedBridge.log(TAG + "nav class " + n); break; }
        }
        if (nav == null) { XposedBridge.log(TAG + "NavigationBarView not found"); return; }

        // 1) stop AOSP rotating the back icon when the keyboard is shown
        try {
            XposedBridge.hookAllMethods(nav, "orientBackButton", XC_MethodReplacement.DO_NOTHING);
            XposedBridge.log(TAG + "hooked orientBackButton");
        } catch (Throwable t) { XposedBridge.log(TAG + "orient hook failed: " + t); }

        // 1b) stop AOSP rotating the home icon 90 deg when the bar is vertical (landscape)
        try {
            XposedBridge.hookAllMethods(nav, "orientHomeButton", XC_MethodReplacement.DO_NOTHING);
            XposedBridge.log(TAG + "hooked orientHomeButton");
        } catch (Throwable t) { XposedBridge.log(TAG + "home orient hook failed: " + t); }

        // 2) swap in the ICS "down chevron" while the keyboard is up
        try {
            XposedBridge.hookAllMethods(nav, "setNavigationIconHints", new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        int hints = (Integer) param.args[0];
                        boolean ime = (hints & HINT_BACK_ALT) != 0;
                        Object disp = XposedHelpers.callMethod(param.thisObject, "getBackButton");
                        View v = (View) XposedHelpers.callMethod(disp, "getCurrentView");
                        if (v instanceof ImageView) {
                            ((ImageView) v).setImageDrawable(
                                IcsIcons.get(v.getResources(), ime ? 3 : 0));
                        }
                    } catch (Throwable t) { XposedBridge.log(TAG + "ime swap failed: " + t); }
                }
            });
            XposedBridge.log(TAG + "hooked setNavigationIconHints");
        } catch (Throwable t) { XposedBridge.log(TAG + "hints hook failed: " + t); }
    }


    // ---- KitKat overscroll: blue edge glow instead of the Android 12+ stretch (all apps) ----
    static boolean edgeDone;

    @Override
    public void initZygote(StartupParam startupParam) { hookEdge(); }

    /** Runs in zygote (needs reboot) or in any app that is ticked in the module scope (force-stop the app). */
    static synchronized void hookEdge() {
        if (edgeDone) return;
        edgeDone = true;
        try {
            XC_MethodHook h = new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        XposedHelpers.setIntField(param.thisObject, "mEdgeEffectType", 0); // TYPE_GLOW
                        ((android.widget.EdgeEffect) param.thisObject).setColor(0xFF33B5E5);
                    } catch (Throwable ignored) {}
                }
            };
            int n = XposedBridge.hookAllConstructors(android.widget.EdgeEffect.class, h).size();
            XposedBridge.log(TAG + "EdgeEffect ctors hooked x" + n);
        } catch (Throwable t) { XposedBridge.log(TAG + "edge effect hook failed: " + t); }
    }

    // ---- lock screen clock: thin KitKat look. A15 uses the shared clock plugin
    //      (com.android.systemui.shared.clocks.AnimatableClockView inside clock_default_large/small),
    //      so match by class name AND by view id, and use a real Roboto thin file (realme remaps sans-serif). ----
    static final java.util.HashMap<Integer, Boolean> LOCK_IDS = new java.util.HashMap<Integer, Boolean>();
    static final String[] LOCK_ID_NAMES = {
        "lockscreen_clock_view", "lockscreen_clock_view_large", "weather_clock_time", "lock_screen_clock"
    };
    static final java.util.HashSet<String> lockLogged = new java.util.HashSet<String>();

    static void thin(TextView tv) {
        try {
            android.graphics.Typeface t = IcsFont.thin(tv.getContext());
            if (t != null) tv.setTypeface(t);
            try { tv.setFontVariationSettings(null); } catch (Throwable ignored) {}
        } catch (Throwable e) { XposedBridge.log(TAG + "lock clock thin: " + e); }
    }

    static boolean isLockClockId(TextView tv) {
        int id = tv.getId();
        if (id == View.NO_ID) return false;
        Boolean b = LOCK_IDS.get(id);
        if (b == null) {
            b = false;
            try {
                String n = tv.getResources().getResourceEntryName(id);
                for (String x : LOCK_ID_NAMES) if (x.equals(n)) b = true;
            } catch (Throwable ignored) {}
            LOCK_IDS.put(id, b);
        }
        return b;
    }

    static void hookLockClock(ClassLoader cl) {
        // 1) by view id, whatever the class is
        try {
            XposedBridge.hookAllMethods(TextView.class, "onAttachedToWindow", new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        TextView tv = (TextView) param.thisObject;
                        if (!isLockClockId(tv)) return;
                        String key = tv.getClass().getName();
                        synchronized (lockLogged) {
                            if (lockLogged.add(key)) XposedBridge.log(TAG + "lock clock view " + key + " id=" + tv.getId());
                        }
                        thin(tv);
                        Holo.lockProbe(tv);
                    } catch (Throwable ignored) {}
                }
            });
            XposedBridge.log(TAG + "lock clock: TextView.onAttachedToWindow hooked");
        } catch (Throwable t) { XposedBridge.log(TAG + "lock clock attach hook failed: " + t); }

        // 2) by class (re-apply after the plugin restyles itself)
        String[] names = {
            "com.android.systemui.shared.clocks.AnimatableClockView",
            "com.android.keyguard.AnimatableClockView",
            "com.android.keyguard.KeyguardClockSwitch"
        };
        for (String n : names) {
            Class<?> c = XposedHelpers.findClassIfExists(n, cl);
            if (c == null) continue;
            try {
                int k = XposedBridge.hookAllConstructors(c, new XC_MethodHook() {
                    @Override protected void afterHookedMethod(MethodHookParam param) {
                        if (param.thisObject instanceof TextView) thin((TextView) param.thisObject);
                    }
                }).size();
                int m = 0;
                for (String mn : new String[]{"setTextStyle", "refreshTime", "refreshText"}) {
                    try {
                        m += XposedBridge.hookAllMethods(c, mn, new XC_MethodHook() {
                            @Override protected void afterHookedMethod(MethodHookParam param) {
                                if (param.thisObject instanceof TextView) thin((TextView) param.thisObject);
                            }
                        }).size();
                    } catch (Throwable ignored) {}
                }
                XposedBridge.log(TAG + "lock clock hooked " + n + " ctors x" + k + " restyle x" + m);
            } catch (Throwable t) { XposedBridge.log(TAG + "lock clock hook failed: " + t); }
        }
    }

    // ---- KitKat nav button pressed glow ----
    static void hookNavGlow(ClassLoader cl) {
        String[] names = {
            "com.android.systemui.navigationbar.buttons.KeyButtonView",
            "com.android.systemui.navigationbar.views.buttons.KeyButtonView",
            "com.android.systemui.statusbar.policy.KeyButtonView"
        };
        Class<?> k = null;
        for (String n : names) {
            k = XposedHelpers.findClassIfExists(n, cl);
            if (k != null) { XposedBridge.log(TAG + "KeyButtonView " + n); break; }
        }
        if (k == null) { XposedBridge.log(TAG + "KeyButtonView not found"); return; }
        try {
            int n = XposedBridge.hookAllConstructors(k, new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        View v = (View) param.thisObject;
                        boolean land = v.getResources().getConfiguration().orientation
                                == android.content.res.Configuration.ORIENTATION_LANDSCAPE;
                        v.setBackground(KkExtra.glow(v.getResources(), land));
                    } catch (Throwable t) { XposedBridge.log(TAG + "glow: " + t); }
                }
            }).size();
            XposedBridge.log(TAG + "hooked KeyButtonView ctors x" + n);
        } catch (Throwable t) { XposedBridge.log(TAG + "glow hook failed: " + t); }
    }

    // ---- KitKat battery: swap the icon image after SystemUI updates it ----
    static void hookBattery(ClassLoader cl) {
        try {
            Class<?> c = XposedHelpers.findClassIfExists("com.android.systemui.battery.BatteryMeterView", cl);
            if (c == null) { XposedBridge.log(TAG + "BatteryMeterView not found"); return; }
            XC_MethodHook h = new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    applyBattery(param.thisObject);
                }
            };
            String[] ms = {"onBatteryLevelChanged", "updateDrawable", "onPowerSaveChanged",
                    "updateColors", "setColorsFromContext", "updatePercentText", "updateShowPercent", "onBatteryUnknownStateChanged",
                    "scaleBatteryMeterViews"};
            for (String m : ms) {
                int n = XposedBridge.hookAllMethods(c, m, h).size();
                XposedBridge.log(TAG + "battery hook " + m + " x" + n);
            }
        } catch (Throwable t) { XposedBridge.log(TAG + "battery hook failed: " + t); }
    }

    static void applyBattery(Object bmv) {
        try {
            ImageView iv = (ImageView) XposedHelpers.getObjectField(bmv, "mBatteryIconView");
            int level = XposedHelpers.getIntField(bmv, "mLevel");
            boolean charging = false;
            try { charging = XposedHelpers.getBooleanField(bmv, "mPluggedIn"); } catch (Throwable ignored) {}
            try {
                Object pv = XposedHelpers.getObjectField(bmv, "mBatteryPercentView");
                if (pv instanceof TextView) ((TextView) pv).setTypeface(IcsFont.get(((TextView) pv).getContext()));
            } catch (Throwable ignored) {}
            Drawable d = KkIcons.battery(iv.getResources(), level, charging);
            if (d != null) {
                iv.setImageTintList(null);
                iv.setScaleType(ImageView.ScaleType.CENTER);   // draw at KitKat's real size, no shrinking
                android.view.ViewGroup.LayoutParams lp = iv.getLayoutParams();
                if (lp != null) {
                    lp.width = d.getIntrinsicWidth();
                    lp.height = d.getIntrinsicHeight();
                    iv.setLayoutParams(lp);
                }
                iv.setImageDrawable(d);
            }
        } catch (Throwable t) { XposedBridge.log(TAG + "applyBattery: " + t); }
    }

    // ---- status bar clock: TechnoUI font + move to the right of the battery ----
    static final int KEY_MOVED = 0x1c5ec10c;

    static void hookClock(ClassLoader cl) {
        Class<?> clock = null;
        String[] names = {
            "com.android.systemui.statusbar.policy.Clock",
            "com.android.systemui.statusbar.policy.ui.Clock"
        };
        for (String n : names) {
            clock = XposedHelpers.findClassIfExists(n, cl);
            if (clock != null) { XposedBridge.log(TAG + "clock class " + n); break; }
        }
        if (clock == null) { XposedBridge.log(TAG + "Clock class not found"); return; }
        try {
            XposedBridge.hookAllMethods(clock, "onAttachedToWindow", new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        final TextView c = (TextView) param.thisObject;
                        final View root = statusBarRoot(c);
                        if (root == null) return;          // shade / keyguard clocks: leave alone
                        android.graphics.Typeface tf = IcsFont.get(c.getContext());
                        if (tf != null) c.setTypeface(tf);
                        c.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 16);   // KitKat clock size
                        if (c.getTag(KEY_MOVED) != null) return;
                        c.setTag(KEY_MOVED, Boolean.TRUE);
                        c.post(new Runnable() { @Override public void run() { moveRight(c, root); } });
                    } catch (Throwable t) { XposedBridge.log(TAG + "clock hook failed: " + t); }
                }
            });
            XposedBridge.hookAllMethods(clock, "getSmallTime", new XC_MethodHook() {
                @Override protected void afterHookedMethod(MethodHookParam param) {
                    try {
                        TextView c = (TextView) param.thisObject;
                        if (c.getTag(KEY_MOVED) == null) return;           // main status bar clock only
                        if (android.text.format.DateFormat.is24HourFormat(c.getContext())) return;
                        java.util.Calendar cal = java.util.Calendar.getInstance();
                        int h = cal.get(java.util.Calendar.HOUR);
                        if (h == 0) h = 12;
                        String time = h + ":" + (cal.get(java.util.Calendar.MINUTE) < 10 ? "0" : "")
                                + cal.get(java.util.Calendar.MINUTE);
                        String ap = cal.get(java.util.Calendar.AM_PM) == java.util.Calendar.AM ? "AM" : "PM";
                        android.text.SpannableStringBuilder sb = new android.text.SpannableStringBuilder(time + " " + ap);
                        sb.setSpan(new android.text.style.RelativeSizeSpan(0.7f), time.length(), sb.length(),
                                android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        param.setResult(sb);
                    } catch (Throwable t) { XposedBridge.log(TAG + "getSmallTime: " + t); }
                }
            });
            XposedBridge.log(TAG + "hooked Clock.getSmallTime");
            XposedBridge.log(TAG + "hooked Clock.onAttachedToWindow");
        } catch (Throwable t) { XposedBridge.log(TAG + "clock hook failed: " + t); }
    }

    /** The PhoneStatusBarView ancestor, or null if this clock is in the shade/keyguard. */
    static View statusBarRoot(View v) {
        StringBuilder chain = new StringBuilder();
        View found = null;
        ViewParent p = v.getParent();
        while (p != null) {
            String n = p.getClass().getName();
            chain.append(n.substring(n.lastIndexOf('.') + 1)).append('<');
            if (n.contains("QuickStatusBar") || n.contains("ShadeHeader") || n.contains("QSContainer")
                    || n.contains("Keyguard")) { found = null; break; }
            if (found == null && n.endsWith("PhoneStatusBarView")) found = (View) p;
            p = p.getParent();
        }
        XposedBridge.log(TAG + "clock chain: " + chain + (found != null ? " -> MAIN" : " -> skip"));
        return found;
    }

    static void moveRight(TextView c, View root) {
        try {
            int id = c.getResources().getIdentifier("system_icons", "id", PKG);
            View sys = id == 0 ? null : root.findViewById(id);
            if (!(sys instanceof LinearLayout)) {
                XposedBridge.log(TAG + "system_icons not found/LinearLayout: " + sys);
                return;
            }
            ViewGroup old = (ViewGroup) c.getParent();
            if (old != null) old.removeView(c);
            float d = c.getResources().getDisplayMetrics().density;
            c.setPadding((int) (6 * d), 0, (int) (2 * d), 0);
            c.setGravity(android.view.Gravity.CENTER_VERTICAL);
            ((LinearLayout) sys).addView(c, new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            try { XposedHelpers.callMethod(c, "updateClock"); } catch (Throwable ignored) {}
            XposedBridge.log(TAG + "clock moved to system_icons (right)");
        } catch (Throwable t) { XposedBridge.log(TAG + "moveRight failed: " + t); }
    }

    /** Discovery: log SystemUI drawable names that look like status bar icons. */
    static void dumpNames(ClassLoader cl) {
        try {
            Class<?> r = XposedHelpers.findClassIfExists("com.android.systemui.R$drawable", cl);
            if (r == null) { XposedBridge.log(TAG + "R$drawable not found"); return; }
            java.util.regex.Pattern pat = java.util.regex.Pattern.compile(
                "stat_sys|signal|wifi|battery|bluetooth|airplane|flight|alarm|vibrate|mute|" +
                "silent|zen|dnd|location|hotspot|nfc|cast|mobile|data_|roam|volte|vowifi|5g|4g|3g|lte");
            StringBuilder sb = new StringBuilder();
            int n = 0, total = 0;
            for (java.lang.reflect.Field f : r.getFields()) {
                total++;
                if (!pat.matcher(f.getName()).find()) continue;
                sb.append(f.getName()).append(' ');
                if (++n % 15 == 0) { XposedBridge.log(TAG + "names: " + sb); sb.setLength(0); }
            }
            if (sb.length() > 0) XposedBridge.log(TAG + "names: " + sb);
            XposedBridge.log(TAG + "names done, matched=" + n + " of " + total);
        } catch (Throwable t) { XposedBridge.log(TAG + "dumpNames failed: " + t); }
    }
}
