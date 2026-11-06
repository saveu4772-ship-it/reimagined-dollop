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

    static Typeface thinCached;
    static boolean thinTried;

    /** KitKat lock screen clock: Roboto Thin (falls back to Light, then a variable font at wght 100). */
    public static synchronized Typeface thin(Context c) {
        if (thinCached != null || thinTried) return thinCached;
        thinTried = true;
        try {
            String[] have = new File("/system/fonts").list();
            StringBuilder sb = new StringBuilder();
            if (have != null) for (String h : have) if (h.toLowerCase().contains("roboto")) sb.append(h).append(' ');
            de.robv.android.xposed.XposedBridge.log("[IcsSysUi] roboto fonts: " + sb);
        } catch (Throwable ignored) {}
        String[] statics = {
            "/system/fonts/Roboto-Thin.ttf", "/system/fonts/RobotoStatic-Thin.ttf",
            "/system/fonts/Roboto-Light.ttf", "/system/fonts/RobotoStatic-Light.ttf"
        };
        for (String p : statics) {
            try {
                if (new File(p).exists()) {
                    thinCached = Typeface.createFromFile(p);
                    de.robv.android.xposed.XposedBridge.log("[IcsSysUi] lock clock font " + p);
                    return thinCached;
                }
            } catch (Throwable ignored) {}
        }
        String[] vars = {
            "/system/fonts/Roboto[wdth,wght].ttf", "/system/fonts/RobotoFlex-Regular.ttf",
            "/system/fonts/Roboto-Regular.ttf", "/system/fonts/RobotoStatic-Regular.ttf"
        };
        for (String p : vars) {
            try {
                if (!new File(p).exists()) continue;
                Typeface t = new Typeface.Builder(p).setFontVariationSettings("'wght' 100").build();
                if (t != null) {
                    thinCached = t;
                    de.robv.android.xposed.XposedBridge.log("[IcsSysUi] lock clock font (wght 100) " + p);
                    return thinCached;
                }
            } catch (Throwable ignored) {}
        }
        de.robv.android.xposed.XposedBridge.log("[IcsSysUi] lock clock font: using sans-serif-thin");
        thinCached = Typeface.create("sans-serif-thin", Typeface.NORMAL);
        return thinCached;
    }
}
