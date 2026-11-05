package ics.recents;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;

/** Real Android 4.0 nav icons (white, xxhdpi 240x144) embedded as base64. */
public class IcsIcons {
    static final String BACK =
        "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";
    static final String HOME =
        "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" +
        "QgghhBDik+D/VtNSXLF+P1EAAAAASUVORK5CYII=";
    static final String RECENT =
        "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";
    static final String BACK_IME =
        "iVBORw0KGgoAAAANSUhEUgAAAPAAAACQCAYAAAA2oebIAAAK6UlEQVR4nO3dW3MU1xHA8f/oBoiLMbahgAQDxraMwanKQ97yofOe" +
        "b+CqVMU2QsYQiK+YGMxFCNDt5KFPa0bDLF4EWBf/f1Vbwrur1czU9HSfPmfWIEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmS" +
        "JEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEmSJEnSm9ds9wbsVqWUhjh+BaBp" +
        "mrK9W7Q31OPq8RyTAfwKOkGcgWwwv6QMWNpj2ADFYzgeA3iL6ok3AUzSnnzr9eEJOIbOMcxHXgTXgXWP4W8zgF+jTkbeeArMyKmX" +
        "bTee9vhsnQG8BaWUSSLzrvRPvlLKFJFNoM0oa3/0k7SXbbvDjrWmada2c9t2MwN4TKWUiaZp1uu/DwKngaPAMvAAuNc0zYOB3+se" +
        "4z9U02so4+Yx7L1vBjhCHM+DwBLwU9M0i/k5f4TjtRVT270Bu0EpJce6efIdBP4C/A14C/gR+LyU8q+maX4a+Igp2qyzDqyXUvb0" +
        "GK8G7yRtNQJtj6D7vhngI+Cz+ngXmAf+ASzWt02WUuwtDDCAx9fNpFPACSKIPwbuA38CTpRSrgN3gF+BxaZpHgMr3Q/KsXInQ+36" +
        "rDzQTYYoj1d775siLoBHgLeBM8Bl4K/AJWB//Yx/dn5tgl7gKxjAW7NGlM4FOAacBI4DF4ls/F/gKjBfSvm6aZq7vd/vd68Luzgr" +
        "v6CbvDrw9veBT4BPgQ/qf/+ZOH6HgXvE8XVcPAYDeGsKkVWfAM+IjHKi/vwQuEuUhReAK6WUm0RgPwQeNU3zjN4JWsv0zMo7OiOP" +
        "6CavDzWjSimHgENEtj1FZNlLxMXuNHEBPFDfvkYc0ycYwGMxgLemAaapnWhirPaUCOYDxIl6jMg0fwe+B74hxnZflVJu1NK6/5mT" +
        "nf9eL6XsuO71i7rJPH9ROgHMEcdhDjhPZNxjxMUO4pg9JUrk6fqzP3beUcdgJzGAtyaDbR8wSwRtZo71+vos0eA6TWTjj4jsfA5Y" +
        "KKXcIDL1I+Bx0zQrdAIgs1zNzNA5iX/voB6Y3x6VbfcTZXDu93ki235CVCPvEdl4jQjcx/XnNDBDjH9niePqDMkYDODx9ANmjZjq" +
        "WCZOvikiq6wT477H9fVJ4sScAc4SY+XLRJPrBnAduEIE9PWBKZbunPI67Vj5dwviTge+G1BD2fYoMaa9SATsR9TGHhG0M8Q+3CeO" +
        "2xqxP9NEwB+sf+MBEdRD42f1GMDj6wbXM+Am8Dlx4p2vPyeJLLJGnIDLRIk9VZ8/QgT6OdqT/QKRmb8qpXxPNHEWm6Z5xIjuNW9o" +
        "rDwwti3E1M3KwHtniMA8RGTWD4nAvVT36XR9bYK2TH5GHMcJIstO0za7fib2fR74kugXbPy517F/e5FlyphyMUFnDHiYGOt+Wh8X" +
        "iaA8U1+bJE7YJ7RjvAnacjG7tYtEVvqWuChcJU7i+aZpfu5tQz8bFkaUs1vZv/q5Ob5N6wNTQdNEkGa2zaHBcaJZlfu3QlzEVmmz" +
        "7X5iyJH9gztEj2CBqEa+IKqTn2gvYM3QAhAZwC+tvyqolPIW0Zj5lDiZPyaCuF865tRTBts0cSLvIzL0EvALUVZfJbLQDeAHoqx8" +
        "1DTNk6Htqf/cCOpxM/K4a5PrheNw3Z9jxLRPdpPniFL5bSJoV4mLVgZu7ms+cvx7F7hd9zEbfAvArc6KN28t/A0G8GvQWQp4hMjK" +
        "54lVRXNEpjpOnPyFOHmXiOxSiJN+hgjiyc7rd4ipp4X6mAeuN01zr/e3c132xlPEAorfzFj1d/tzt5t+twbRn9g8tj1HBPExoukE" +
        "cZHKsWt2kvcTF6h9ROA+IiqNa8C/637dJErnbOY59n0JBvAWdMrNSSJj9UvMd2mnTnL65CzwDhHIedxzjLxGG8hZYkIEck5BXSGC" +
        "+BsiuBeJsfKzEdsHm1dF0XmOfH4ou5VSZmm7yWeIEvkzoro4V/fjABGsOUzIMnmq7keW+k+I4Py57svXdT++Am72q4p6UWnwBpCx" +
        "GMBb1P1GjhFBcIhobL1NdJ8v0o6XzxCNn2ziLBHj5JX6mVO0Y+XM2g+IIPgPUXYuEKX2tX4Qj7gjKm+Wz4y7Dqz2M3UpJRtSF4mA" +
        "/ZhoSB0ngjbL4GzS5bTZDJGNc/z7iBjHfk1cfK4QK9Ru0y5oWR44bhMwfNODnmcAv4IXLSEcGEe+TwTDJSJAPiAC+x0i62ZTZ7X+" +
        "XCcCeR8ROFNE4NynLa0zk30L/I8ImsWBoBw5lqxzt4eIbHucKJG7Sx1P0ZbJ2ZDLwM0LzXR9Pcvke0SwXq/beIW40Gy6W6tzodm0" +
        "pBRv5h+bAfyKRpWrAwE8SWTkw8TY8SxRXl+mbXy9RWSwZdpgWaNd+dXtXi8RWfkH4BYRKFeJwP6+W9bXrDYxUOofIAI2q4MLtKX+" +
        "Udq7qFY6j9yWXHTR1O283duOq0QGflEDbqL/1NCx02gG8GvWuY2uu3ppaCpmPxEs2b2eI4L4JBHk++tbs1RdpS2vszk0VV+7R5TW" +
        "C0RGvk5kwF+JcvVp0zTr9SJyuD7eI7rnlzrbcJK4iOTSyCWifO+O0afqY5UI3F+IiuBafcwT2faH3v4OToFhtn0lBvAb8BI3sk/T" +
        "BtRJ2umoi0RmPFVfy5sncvVXzinP0K7JXqWdivqRdpXXl0Snd4m41zaz/lz9e6eIoD1AG7jdudu8YOyvf2+FuDDcIi4YXxKBe5Mo" +
        "7x8BSyPK+E1fXFePi8H7CgzgN2xggcTGeK+/wqmUcoR2DJrrh88SgXeEdrooy9mcU54hAjC710+JTvUCMV1zg+haHycy7mdE8Oai" +
        "i1wp9ZTNmb7fTX5Ie7tkLryYB77t7svAzfx+tdAbYgD/DkYsmBiVlQ8QTaXMynNEVr5MBPMJIhuuEwGXiyagXZedgbdEZMtfaW97" +
        "PE5k3FwplSX6Wud3Z2k75Bm0/abZnfra4sDwoH/zg9n2DTGAt8HALXkwYgFGKeUkkZUv095jfJLIyrO049HMyv1FFFOdz4e2KZXj" +
        "W9gc+Lk2+SHR2b5Fu1Iqp62WetvYnbYCv173d2MAb5Nxv9C8vi+710eJ0vdDIqA/IRZWZCm8TFsOd0vh6frvnL/NjNktvQuRzX8k" +
        "GmJXO4/btFNUTwf2JTvje+YrgnYLA3iHGNG9fm5OuXaSzxCNrlze2G1G5ZxtZuU12jup8rNz7rbb/LpDTEldIxZfLADfNE1zu7ed" +
        "2U3OxSA5nvcbNLaBAbyDvMTNBZO04+QTREBn93qOCOaj9XOWiYycdwPl/clPiemnvAPqC6JU/o7oJi8S3eShiiC3cdu+ZEDBAN6h" +
        "XvL2vlmirM6vr7lArL9+j+he5wKQnNu9T7sAJJtT88QCkG62787dNrTztmbbHcIA3sFG3GD/oiWROVbOJZGXiWmj94lx7l2iPP6C" +
        "CNjv6nOLxGqpUXO3G0+N+vvaHgbwLjMiKw51r/NL9S4Rja5ZYpHHxr3GA/PQdpN3GQN4F+plxhdl5fwC9ZxuWiamhx6O+JqcsTO+" +
        "dgYDeA8Y58b8Eb+zpS8C0M7hl9rtDVnqPjdefYnfsUzehczAe8yI7vVziyywm7wnGMB70IixbD+AHdtKu0Uppft/Q5QkSZIkSZIk" +
        "SZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkSZIkaYv+D0E5cjuF44sdAAAAAElFTkSuQmCC";

    /** kind: 0=back, 1=home, 2=recent, 3=back (keyboard open) */
    public static Drawable get(Resources res, int kind) {
        String s = kind == 0 ? BACK : kind == 1 ? HOME : kind == 2 ? RECENT : BACK_IME;
        byte[] d = Base64.decode(s, Base64.DEFAULT);
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inDensity = 480;                       // xxhdpi source
        o.inTargetDensity = res.getDisplayMetrics().densityDpi;
        Bitmap b = BitmapFactory.decodeByteArray(d, 0, d.length, o);
        return new BitmapDrawable(res, b);
    }
}
