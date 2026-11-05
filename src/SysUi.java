package ics.recents;

import android.content.res.XResources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/** ICS-style nav bar icons, incl. the keyboard-open (IME) back icon. */
public class SysUi implements IXposedHookInitPackageResources, IXposedHookLoadPackage {
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
        if (!PKG.equals(lp.packageName) || !lp.processName.equals(PKG)) return;
        dumpNames(lp.classLoader);
        hookBattery(lp.classLoader);
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
                    "updateColors", "setColorsFromContext", "onBatteryUnknownStateChanged",
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
