package ics.recents;

import android.content.res.XResources;
import android.graphics.drawable.Drawable;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/** ICS-style navigation bar icons (real 4.0 PNGs embedded in IcsIcons). */
public class SysUi implements IXposedHookInitPackageResources {
    static final String TAG = "[IcsSysUi] ";
    static final String PKG = "com.android.systemui";

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam p) {
        if (!PKG.equals(p.packageName)) return;
        XposedBridge.log(TAG + "init resources in " + p.packageName);
        replace(p, "ic_sysbar_back", 0);
        replace(p, "ic_sysbar_back_quick_step", 0);
        replace(p, "ic_sysbar_home", 1);
        replace(p, "ic_sysbar_home_quick_step", 1);
        replace(p, "ic_sysbar_recent", 2);
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
            XposedBridge.log(TAG + "failed " + name + ": " + t);
        }
    }
}
