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
}
