package ics.recents;

import android.content.res.XResources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;

/**
 * Step 1 for SystemUI: ICS-style navigation bar icons (back / home / recents).
 * Icons are drawn at runtime, so no extra resource files are needed.
 */
public class SysUi implements IXposedHookInitPackageResources {
    static final String TAG = "[IcsSysUi] ";
    static final String PKG = "com.android.systemui";

    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam p) {
        if (!PKG.equals(p.packageName)) return;
        XposedBridge.log(TAG + "init resources in " + p.packageName);
        replace(p, "ic_sysbar_back", 0);
        replace(p, "ic_sysbar_home", 1);
        replace(p, "ic_sysbar_home_quick_step", 1);
        replace(p, "ic_sysbar_recent", 2);
    }

    static void replace(XC_InitPackageResources.InitPackageResourcesParam p, String name, final int kind) {
        try {
            p.res.setReplacement(PKG, "drawable", name, new XResources.DrawableLoader() {
                @Override public Drawable newDrawable(XResources res, int id) throws Throwable {
                    return icon(res, kind);
                }
            });
            XposedBridge.log(TAG + "replaced " + name);
        } catch (Throwable t) {
            XposedBridge.log(TAG + "failed " + name + ": " + t);
        }
    }

    /** White Holo-ish line icons on a 96x96 canvas (system tints them as needed). */
    static Drawable icon(XResources res, int kind) {
        Bitmap bmp = Bitmap.createBitmap(96, 96, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(bmp);
        Paint pt = new Paint(Paint.ANTI_ALIAS_FLAG);
        pt.setColor(0xFFFFFFFF);
        pt.setStyle(Paint.Style.STROKE);
        pt.setStrokeWidth(8f);
        pt.setStrokeCap(Paint.Cap.ROUND);
        pt.setStrokeJoin(Paint.Join.ROUND);
        Path path = new Path();
        if (kind == 0) {            // back: curved return arrow
            path.moveTo(40, 24); path.lineTo(24, 40); path.lineTo(40, 56);
            path.moveTo(24, 40); path.lineTo(56, 40);
            path.quadTo(72, 40, 72, 56); path.lineTo(72, 72);
        } else if (kind == 1) {     // home: house outline
            path.moveTo(16, 48); path.lineTo(48, 20); path.lineTo(80, 48);
            path.moveTo(26, 42); path.lineTo(26, 76); path.lineTo(70, 76); path.lineTo(70, 42);
        } else {                    // recents: two overlapping rectangles
            path.moveTo(32, 36); path.lineTo(32, 22); path.lineTo(76, 22);
            path.lineTo(76, 58); path.lineTo(64, 58);
            path.addRoundRect(new RectF(20, 36, 64, 74), 3f, 3f, Path.Direction.CW);
        }
        cv.drawPath(path, pt);
        return new BitmapDrawable(res, bmp);
    }
}
