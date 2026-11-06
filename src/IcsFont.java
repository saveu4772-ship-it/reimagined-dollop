package ics.recents;

import android.content.Context;
import android.graphics.Typeface;

import java.io.File;

/** KitKat status bar clock font: Roboto Regular. */
public class IcsFont {
    static Typeface cached;

    public static synchronized Typeface get(Context c) {
        if (cached != null) return cached;
        // realme may map "sans-serif" to its own font, so prefer the real Roboto file
        String[] files = {
            "/system/fonts/Roboto-Regular.ttf",
            "/system/fonts/RobotoStatic-Regular.ttf",
            "/system/fonts/Roboto[wdth,wght].ttf",
            "/system/fonts/Roboto-Regular.otf"
        };
        for (String p : files) {
            try {
                if (new File(p).exists()) {
                    cached = Typeface.createFromFile(p);
                    de.robv.android.xposed.XposedBridge.log("[IcsSysUi] clock font " + p);
                    return cached;
                }
            } catch (Throwable ignored) {}
        }
        de.robv.android.xposed.XposedBridge.log("[IcsSysUi] clock font: Roboto file not found, using sans-serif");
        cached = Typeface.create("sans-serif", Typeface.NORMAL);
        return cached;
    }
}
