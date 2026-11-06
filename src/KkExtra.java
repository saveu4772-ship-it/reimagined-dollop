package ics.recents;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.Base64;

/** Extra KitKat assets (hdpi originals, white): silent-ringer status icon + nav button press glow. */
public class KkExtra {
    static final String SILENT =
        "iVBORw0KGgoAAAANSUhEUgAAAB4AAAAbCAMAAABcI88jAAABj1BMVEUAAAD/////////////////////////////////////////" +
        "////////////////////////////////////////////////////////////////////////////////////////////////////" +
        "////////////////////////////////////////////////////////////////////////////////////////////////////" +
        "////////////////////////////////////////////////////////////////////////////////////////////////////" +
        "////////////////////////////////////////////////////////////////////////////////////////////////////" +
        "//////////////////////////////////////////////////////////////////////////////////////+J7vFQAAAAhHRS" +
        "TlMAAwQBAkqMdxFE+f0T1b12zAuTHOEbmkL81EAJpaENCMnvJc1Vs3X63hBMIrqFCm19fuzEBhXGeFrLJNdrlvj+Yydet1Qm6lHD" +
        "+0Yv9fZDvzo7QYStqfAuSOYzkKJ74tsplZswyB9i0heYpI3n3bV6i7k45ONnq6PODKAgXNgoeV2ZBzKRZcRPAAABiElEQVQoz32T" +
        "6VMTQRDF3+wMhLCCbgCNRgOBhEsOQQUFdAEBuZVbLsOtwYCCIocXx/vDmS20ip1FumpqPvz6Tb/umQH8ISxL4r8h/66rQyEnN5Tn" +
        "JUgRpALhfJshvUt51ck3Clh485Z3ihOWQXGEDBUVl2h8m3cgjMLhqM2wunsPsft8EL/sUQpLlpaRiXJUJJFiRBkdCFSyitU1qH1Y" +
        "x3qoy1o0ND5qamaUj5/gaQtbIaRP+Yw2qfFztLWzw0+1qxd8GXVdl51dxeyGMViJV1ppu25Pb5/92qQa99O2OTA4NDwy6vQE5/VG" +
        "ux55OzaQGMfEpC5mTGSKnJ55x9k5YH7BxAKLfN+WZHoJMSyvGOPU2atr6xv88LErU4rNTyb2rreV/fHe7JaD7c8BtZJfuNOw+5Xf" +
        "SrC2Z2AJK8PvDva1/V0c5PixxOEmj4TCjyz5M/7rt9+5wB9GIJXCMXkylz7136XEeoflKRQyzKZXzLY9cxdP2ElVJc4C+N+z1R5r" +
        "YrjmG0j/NzgHvXFH1U3LgCIAAAAASUVORK5CYII=";

    static final String GLOW =
        "iVBORw0KGgoAAAANSUhEUgAAALMAAABiCAMAAAAsqBJLAAAAzFBMVEUAAAD/////////////////////////////////////////" +
        "////////////////////////////////////////////////////////////////////////////////////////////////////" +
        "////////////////////////////////////////////////////////////////////////////////////////////////////" +
        "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";

    static Bitmap decode(Resources res, String s) {
        byte[] d = Base64.decode(s, Base64.DEFAULT);
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inDensity = 240;                                   // hdpi source
        o.inTargetDensity = res.getDisplayMetrics().densityDpi;
        return BitmapFactory.decodeByteArray(d, 0, d.length, o);
    }

    public static Drawable silent(Resources res) {
        return new BitmapDrawable(res, decode(res, SILENT));
    }

    /** Pressed-state glow for nav buttons; rotated for the vertical (landscape) bar. */
    public static Drawable glow(Resources res, boolean vertical) {
        Bitmap b = decode(res, GLOW);
        if (vertical) {
            Matrix m = new Matrix();
            m.postRotate(90);
            b = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
        }
        BitmapDrawable g = new BitmapDrawable(res, b);
        g.setGravity(android.view.Gravity.CENTER);
        StateListDrawable sl = new StateListDrawable();
        sl.addState(new int[]{android.R.attr.state_pressed}, g);
        sl.addState(new int[]{}, new android.graphics.drawable.ColorDrawable(0));
        return sl;
    }
}
