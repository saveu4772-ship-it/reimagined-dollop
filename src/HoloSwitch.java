package ics.recents;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;
import android.widget.CompoundButton;

import java.util.HashSet;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * ICS holo switch drawn in code: dark track, slanted slab that slides (blue + "ON" on the right, gray + "OFF" on
 * the left). Replaces the drawing and size of realme's COUISwitch for the views Holo.holoWidgets tags.
 */
public class HoloSwitch {
    static final String TAG = "[IcsSysUi] ";
    static final int KEY_ON = 0x1c5ec110, KEY_P = 0x1c5ec111;
    static final int TRACK = 0xFF262626, THUMB_OFF = 0xFF666666, THUMB_ON = 0xFF1A94C9;
    static final HashSet<Class<?>> hooked = new HashSet<Class<?>>();
    static final Paint pt = new Paint(Paint.ANTI_ALIAS_FLAG);
    static final Path path = new Path();

    static Class<?> declaring(Class<?> c, String name, Class<?>... types) {
        for (; c != null; c = c.getSuperclass()) {
            String n = c.getName();
            if (n.startsWith("android.view.") || n.startsWith("android.widget.")) return null;
            try { c.getDeclaredMethod(name, types); return c; } catch (NoSuchMethodException ignored) {}
        }
        return null;
    }

    static void install(View v) {
        v.setTag(KEY_ON, Boolean.TRUE);
        synchronized (hooked) {
            Class<?> dc = declaring(v.getClass(), "onDraw", Canvas.class);
            Class<?> mc = declaring(v.getClass(), "onMeasure", int.class, int.class);
            XposedBridge.log(TAG + "holoswitch: onDraw in " + (dc == null ? "none" : dc.getName())
                    + ", onMeasure in " + (mc == null ? "none" : mc.getName()));
            if (dc != null && hooked.add(dc)) {
                XposedBridge.hookAllMethods(dc, "onDraw", new XC_MethodHook() {
                    @Override protected void beforeHookedMethod(MethodHookParam p) {
                        View sw = (View) p.thisObject;
                        if (sw.getTag(KEY_ON) == null) return;
                        try { draw(sw, (Canvas) p.args[0]); p.setResult(null); }
                        catch (Throwable t) { XposedBridge.log(TAG + "holoswitch draw: " + t); }
                    }
                });
            }
            if (mc != null && hooked.add(mc)) {
                XposedBridge.hookAllMethods(mc, "onMeasure", new XC_MethodHook() {
                    @Override protected void afterHookedMethod(MethodHookParam p) {
                        View sw = (View) p.thisObject;
                        if (sw.getTag(KEY_ON) == null) return;
                        float d = sw.getResources().getDisplayMetrics().density;
                        try { XposedHelpers.callMethod(sw, "setMeasuredDimension", (int) (88 * d), (int) (32 * d)); }
                        catch (Throwable t) { XposedBridge.log(TAG + "holoswitch measure: " + t); }
                    }
                });
            }
        }
        v.requestLayout();
        v.invalidate();
    }

    static int lerp(int a, int b, float t) {
        return Color.argb(255,
                (int) (Color.red(a) + (Color.red(b) - Color.red(a)) * t),
                (int) (Color.green(a) + (Color.green(b) - Color.green(a)) * t),
                (int) (Color.blue(a) + (Color.blue(b) - Color.blue(a)) * t));
    }

    static void draw(View v, Canvas c) {
        float W = v.getWidth(), H = v.getHeight(), d = v.getResources().getDisplayMetrics().density;
        boolean on = ((CompoundButton) v).isChecked();
        float t = on ? 1f : 0f;
        Object cur = v.getTag(KEY_P);
        float p = cur instanceof Float ? (Float) cur : t;
        if (Math.abs(t - p) > 0.01f) { p += (t - p) * 0.35f; v.postInvalidateOnAnimation(); } else p = t;
        v.setTag(KEY_P, p);
        int alpha = v.isEnabled() ? 255 : 110;

        c.save();
        c.clipRect(0, 0, W, H);
        pt.setStyle(Paint.Style.FILL);
        pt.setShader(null);
        pt.setColor(TRACK);
        pt.setAlpha(alpha);
        c.drawRect(0, 0, W, H, pt);

        float tw = W * 0.62f, s = H * 0.5f, x0 = p * (W - tw);
        path.reset();
        path.moveTo(x0 + s, 0);
        path.lineTo(x0 + tw + s, 0);
        path.lineTo(x0 + tw, H);
        path.lineTo(x0, H);
        path.close();
        pt.setColor(lerp(THUMB_OFF, THUMB_ON, p));
        pt.setAlpha(alpha);
        c.drawPath(path, pt);

        // label on the slab
        pt.setColor(on ? 0xFFFFFFFF : 0xFFD0D0D0);
        pt.setAlpha(alpha);
        pt.setTextSize(H * 0.44f);
        pt.setTextAlign(Paint.Align.CENTER);
        try { pt.setTypeface(IcsFont.get(v.getContext())); } catch (Throwable ignored) {}
        float cx = Math.min(x0 + tw / 2f + s / 2f, W - 4 * d - pt.measureText("ON") / 2f);
        c.drawText(on ? "ON" : "OFF", cx, H / 2f + H * 0.16f, pt);
        c.restore();
    }
}
