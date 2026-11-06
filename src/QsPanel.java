package ics.recents;

import android.animation.LayoutTransition;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * KitKat-style quick settings panel. Opens when you swipe down on the right
 * side of the status bar (like Android 4.4). Runs inside com.android.systemui.
 */
public class QsPanel {
    static final String TAG = "[IcsQs] ";

    static View root;
    static View panelView;
    static Context ctx;
    static BroadcastReceiver rcv;
    static final Handler H = new Handler(Looper.getMainLooper());

    // swipe tracking
    static float downX, downY;
    static boolean armed, fired;

    // tiles
    static ImageView iUser, iWifi, iSig, iBat, iAir, iBt, iLoc, iAlarm, iBri;
    static TextView tUser, tWifi, tSig, tBat, tAir, tBt, tLoc, tAlarm, tBri;

    // ------------------------------------------------------------------ hook
    static void hookStatusBar(ClassLoader cl) {
        try {
            Class<?> c = XposedHelpers.findClassIfExists(
                    "com.android.systemui.statusbar.phone.PhoneStatusBarView", cl);
            if (c == null) { XposedBridge.log(TAG + "PhoneStatusBarView not found"); return; }
            XC_MethodHook h = new XC_MethodHook() {
                @Override protected void beforeHookedMethod(MethodHookParam p) {
                    try { onTouch(p); } catch (Throwable t) { XposedBridge.log(TAG + "touch: " + t); }
                }
            };
            for (String m : new String[]{"onInterceptTouchEvent", "onTouchEvent"}) {
                int n = XposedBridge.hookAllMethods(c, m, h).size();
                XposedBridge.log(TAG + "status bar hook " + m + " x" + n);
            }
        } catch (Throwable t) { XposedBridge.log(TAG + "hookStatusBar failed: " + t); }
    }

    static void onTouch(XC_MethodHook.MethodHookParam p) {
        MotionEvent ev = (MotionEvent) p.args[0];
        View v = (View) p.thisObject;
        int a = ev.getActionMasked();
        if (a == MotionEvent.ACTION_DOWN) {
            downX = ev.getX(); downY = ev.getY();
            fired = false;
            armed = root == null && downX > v.getWidth() * 0.62f;   // right side only
            return;
        }
        if (fired) {
            p.setResult(true);
            if (a == MotionEvent.ACTION_UP || a == MotionEvent.ACTION_CANCEL) { fired = false; armed = false; }
            return;
        }
        if (a == MotionEvent.ACTION_MOVE && armed) {
            float slop = ViewConfiguration.get(v.getContext()).getScaledTouchSlop();
            float dy = ev.getY() - downY, dx = Math.abs(ev.getX() - downX);
            if (dy > slop * 1.2f && dy > dx) {
                fired = true;
                // tell the real shade the gesture is over, then keep the rest for ourselves
                MotionEvent c = MotionEvent.obtain(ev);
                c.setAction(MotionEvent.ACTION_CANCEL);
                try { XposedBridge.invokeOriginalMethod(p.method, p.thisObject, new Object[]{c}); }
                catch (Throwable ignored) {}
                c.recycle();
                p.setResult(true);
                show(v.getContext());
            }
        } else if (a == MotionEvent.ACTION_UP || a == MotionEvent.ACTION_CANCEL) {
            armed = false;
        }
    }

    // ------------------------------------------------------------------ show / hide
    static void show(Context c0) {
        if (root != null) return;
        final Context c = c0.getApplicationContext() != null ? c0.getApplicationContext() : c0;
        ctx = c;
        H.post(new Runnable() {
            @Override public void run() {
                try {
                    if (root != null) return;
                    WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
                    root = build(c);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                            -1, -1, 2038 /* TYPE_APPLICATION_OVERLAY */,
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                                    | WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                            android.graphics.PixelFormat.TRANSLUCENT);
                    lp.dimAmount = 0.5f;
                    lp.gravity = Gravity.TOP;
                    wm.addView(root, lp);
                    registerReceiver(c);
                    refresh();
                    panelView.setTranslationY(-dp(c, 400));
                    panelView.animate().translationY(0).setDuration(180).start();
                } catch (Throwable t) {
                    XposedBridge.log(TAG + "show failed: " + t);
                    root = null;
                }
            }
        });
    }

    static void hide() {
        final View r = root;
        if (r == null) return;
        root = null;
        try { if (rcv != null) ctx.unregisterReceiver(rcv); } catch (Throwable ignored) {}
        rcv = null;
        final Context c = ctx;
        try {
            panelView.animate().translationY(-panelView.getHeight()).setDuration(150)
                    .withEndAction(new Runnable() {
                        @Override public void run() {
                            try { ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).removeView(r); }
                            catch (Throwable ignored) {}
                        }
                    }).start();
        } catch (Throwable t) {
            try { ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).removeView(r); }
            catch (Throwable ignored) {}
        }
    }

    static void registerReceiver(Context c) {
        rcv = new BroadcastReceiver() {
            @Override public void onReceive(Context context, Intent intent) { refresh(); }
        };
        IntentFilter f = new IntentFilter();
        f.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        f.addAction("android.net.wifi.STATE_CHANGE");
        f.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
        f.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        f.addAction(Intent.ACTION_BATTERY_CHANGED);
        f.addAction("android.location.MODE_CHANGED");
        try { c.registerReceiver(rcv, f); } catch (Throwable t) { XposedBridge.log(TAG + "receiver: " + t); }
    }

    // ------------------------------------------------------------------ UI
    static int dp(Context c, float v) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v,
                c.getResources().getDisplayMetrics());
    }

    static View build(final Context c) {
        Resources res = c.getResources();
        int W = res.getDisplayMetrics().widthPixels;
        int sbh;
        int id = res.getIdentifier("status_bar_height", "dimen", "android");
        sbh = id > 0 ? res.getDimensionPixelSize(id) : dp(c, 24);

        FrameLayout r = new FrameLayout(c);
        r.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { hide(); }
        });

        LinearLayout panel = new LinearLayout(c);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setBackgroundColor(0xFF000000);
        panel.setClickable(true);
        panelView = panel;
        FrameLayout.LayoutParams plp = new FrameLayout.LayoutParams(-1, -2);
        plp.topMargin = 0;   // overlay is already inset below the status bar
        r.addView(panel, plp);

        // ---- header: big thin clock + date, list icon on the right
        LinearLayout head = new LinearLayout(c);
        head.setOrientation(LinearLayout.HORIZONTAL);
        head.setGravity(Gravity.BOTTOM);
        head.setPadding(dp(c, 14), dp(c, 2), dp(c, 14), dp(c, 6));

        TextClock time = new TextClock(c);
        time.setFormat12Hour("h:mm");
        time.setFormat24Hour("HH:mm");
        time.setTextColor(Color.WHITE);
        time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 46);
        time.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        time.setIncludeFontPadding(false);
        head.addView(time);

        TextClock date = new TextClock(c);
        date.setFormat12Hour("EEE d MMM");
        date.setFormat24Hour("EEE d MMM");
        date.setTextColor(0xFFB0B0B0);
        date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        date.setAllCaps(true);
        date.setPadding(dp(c, 10), 0, 0, dp(c, 6));
        head.addView(date);

        View sp = new View(c);
        head.addView(sp, new LinearLayout.LayoutParams(0, 1, 1f));

        ImageView list = new ImageView(c);
        list.setImageDrawable(KkIcons.get(res, "qs_list"));
        list.setScaleType(ImageView.ScaleType.FIT_CENTER);
        list.setPadding(dp(c, 6), dp(c, 6), dp(c, 6), dp(c, 6));
        list.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                hide();
                try {
                    Object sbm = ctx.getSystemService("statusbar");
                    XposedHelpers.callMethod(sbm, "expandNotificationsPanel");
                } catch (Throwable t) { XposedBridge.log(TAG + "expand: " + t); }
            }
        });
        head.addView(list, new LinearLayout.LayoutParams(dp(c, 48), dp(c, 48)));
        panel.addView(head, new LinearLayout.LayoutParams(-1, -2));

        // ---- 3x3 grid of dark flat tiles
        int tileW = W / 3;
        int tileH = tileW;   // KitKat tiles are square
        int gap = 2;

        // same order as the real KitKat panel
        LinearLayout row1 = row(c);
        iUser = new ImageView(c); tUser = new TextView(c);
        row1.addView(tile(c, iUser, tUser, tileW, tileH, gap, null));
        iBri = new ImageView(c); tBri = new TextView(c);
        row1.addView(tile(c, iBri, tBri, tileW, tileH, gap, new Runnable() {
            @Override public void run() { cycleBrightness(); }
        }));
        final ImageView iSet = new ImageView(c); final TextView tSet = new TextView(c);
        iSet.setImageDrawable(KkIcons.get(res, "qs_gear")); tSet.setText("SETTINGS");
        row1.addView(tile(c, iSet, tSet, tileW, tileH, gap, new Runnable() {
            @Override public void run() { openSettings(Settings.ACTION_SETTINGS); }
        }));
        panel.addView(row1);

        LinearLayout row2 = row(c);
        iWifi = new ImageView(c); tWifi = new TextView(c);
        row2.addView(tile(c, iWifi, tWifi, tileW, tileH, gap, new Runnable() {
            @Override public void run() { toggleWifi(); }
        }));
        iSig = new ImageView(c); tSig = new TextView(c);
        row2.addView(tile(c, iSig, tSig, tileW, tileH, gap, null));
        iBat = new ImageView(c); tBat = new TextView(c);
        row2.addView(tile(c, iBat, tBat, tileW, tileH, gap, new Runnable() {
            @Override public void run() { openSettings("android.intent.action.POWER_USAGE_SUMMARY"); }
        }));
        panel.addView(row2);

        LinearLayout row3 = row(c);
        iAir = new ImageView(c); tAir = new TextView(c);
        row3.addView(tile(c, iAir, tAir, tileW, tileH, gap, new Runnable() {
            @Override public void run() { toggleAirplane(); }
        }));
        iBt = new ImageView(c); tBt = new TextView(c);
        row3.addView(tile(c, iBt, tBt, tileW, tileH, gap, new Runnable() {
            @Override public void run() { toggleBluetooth(); }
        }));
        iLoc = new ImageView(c); tLoc = new TextView(c);
        row3.addView(tile(c, iLoc, tLoc, tileW, tileH, gap, new Runnable() {
            @Override public void run() { toggleLocation(); }
        }));
        panel.addView(row3);

        LinearLayout row4 = row(c);
        iAlarm = new ImageView(c); tAlarm = new TextView(c);
        row4.addView(tile(c, iAlarm, tAlarm, tileW, tileH, gap, new Runnable() {
            @Override public void run() { openSettings(android.provider.AlarmClock.ACTION_SHOW_ALARMS); }
        }));
        panel.addView(row4);

        // thin handle line at the bottom
        View line = new View(c);
        line.setBackgroundColor(0xFFC0C0C0);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-1, dp(c, 2));
        llp.setMargins(dp(c, 12), dp(c, 10), dp(c, 12), dp(c, 8));
        panel.addView(line, llp);
        return r;
    }

    static LinearLayout row(Context c) {
        LinearLayout l = new LinearLayout(c);
        l.setOrientation(LinearLayout.HORIZONTAL);
        return l;
    }

    static View tile(Context c, ImageView icon, TextView label, int w, int h, int gap, final Runnable click) {
        LinearLayout t = new LinearLayout(c);
        t.setOrientation(LinearLayout.VERTICAL);
        t.setGravity(Gravity.CENTER);
        StateListDrawable bg = new StateListDrawable();
        bg.addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(0xFF3C3C3C));
        bg.addState(new int[]{}, new ColorDrawable(0xFF1F1F1F));
        t.setBackground(bg);
        icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
        t.addView(icon, new LinearLayout.LayoutParams(dp(c, 32), dp(c, 32)));
        label.setTextColor(0xFFDDDDDD);
        label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        label.setGravity(Gravity.CENTER);
        label.setMaxLines(2);
        label.setPadding(dp(c, 4), dp(c, 8), dp(c, 4), 0);
        t.addView(label, new LinearLayout.LayoutParams(-1, -2));
        if (click != null) {
            t.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    try { click.run(); } catch (Throwable e) { XposedBridge.log(TAG + "click: " + e); }
                    H.postDelayed(new Runnable() { @Override public void run() { refresh(); } }, 700);
                }
            });
        }
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(w - gap, h);
        lp.setMargins(gap, gap, 0, 0);
        t.setLayoutParams(lp);
        return t;
    }

    // ------------------------------------------------------------------ state
    static void set(ImageView i, TextView t, Drawable d, String text, boolean on) {
        if (i == null || t == null) return;
        if (d != null) i.setImageDrawable(d);
        i.setAlpha(on ? 1f : 0.35f);
        t.setText(text);
        t.setAlpha(on ? 1f : 0.6f);
    }

    static void refresh() {
        final Context c = ctx;
        if (c == null || root == null) return;
        Resources res = c.getResources();
        try { // user
            android.os.UserManager um = (android.os.UserManager) c.getSystemService(Context.USER_SERVICE);
            String name = um.getUserName();
            set(iUser, tUser, glyph(res, 5), (name == null || name.length() == 0 ? "OWNER" : name).toUpperCase(), true);
        } catch (Throwable t) { XposedBridge.log(TAG + "user: " + t); }
        try { // brightness
            int br = Settings.System.getInt(c.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 128);
            set(iBri, tBri, KkIcons.get(res, "qs_bri"), (br * 100 / 255) + "%", true);
        } catch (Throwable t) { XposedBridge.log(TAG + "bri: " + t); }
        try { // wifi
            android.net.wifi.WifiManager wm = (android.net.wifi.WifiManager) c.getSystemService(Context.WIFI_SERVICE);
            boolean on = wm.isWifiEnabled();
            String ssid = "";
            int lvl = 4;
            try {
                android.net.wifi.WifiInfo wi = wm.getConnectionInfo();
                ssid = wi.getSSID();
                if (ssid != null) ssid = ssid.replace("\"", "");
                if (ssid == null || ssid.contains("unknown")) ssid = "";
                lvl = Math.max(0, Math.min(4, android.net.wifi.WifiManager.calculateSignalLevel(wi.getRssi(), 5)));
            } catch (Throwable ignored) {}
            String text = !on ? "WI-FI OFF" : (ssid.length() > 0 ? ssid.toUpperCase() : "WI-FI ON");
            set(iWifi, tWifi, KkIcons.get(res, "wifi" + (on ? lvl : 4)), text, on);
        } catch (Throwable t) { XposedBridge.log(TAG + "wifi: " + t); }
        try { // mobile signal
            android.telephony.TelephonyManager tm =
                    (android.telephony.TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
            int lvl = 0;
            try { lvl = tm.getSignalStrength().getLevel(); } catch (Throwable ignored) {}
            String op = tm.getNetworkOperatorName();
            boolean air = Settings.Global.getInt(c.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
            boolean has = !air && op != null && op.length() > 0;
            set(iSig, tSig, KkIcons.get(res, "sig" + Math.max(0, Math.min(4, lvl))),
                    has ? op.toUpperCase() : "NO SIGNAL", has);
        } catch (Throwable t) { XposedBridge.log(TAG + "sig: " + t); }
        try { // battery
            Intent b = c.registerReceiver(null, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
            int level = b.getIntExtra("level", 0) * 100 / Math.max(1, b.getIntExtra("scale", 100));
            boolean plugged = b.getIntExtra("plugged", 0) != 0;
            set(iBat, tBat, KkIcons.battery(res, level, plugged), level + "%", true);
        } catch (Throwable t) { XposedBridge.log(TAG + "bat: " + t); }
        try { // airplane
            boolean air = Settings.Global.getInt(c.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
            set(iAir, tAir, KkIcons.get(res, "qs_air"), "AIRPLANE MODE", air);
        } catch (Throwable t) { XposedBridge.log(TAG + "air: " + t); }
        try { // bluetooth
            BluetoothAdapter ad = BluetoothAdapter.getDefaultAdapter();
            boolean on = ad != null && ad.isEnabled();
            set(iBt, tBt, KkIcons.get(res, "qs_bt"), on ? "BLUETOOTH ON" : "BLUETOOTH OFF", on);
        } catch (Throwable t) { XposedBridge.log(TAG + "bt: " + t); }
        try { // location
            android.location.LocationManager lm = (android.location.LocationManager) c.getSystemService(Context.LOCATION_SERVICE);
            boolean on = lm.isLocationEnabled();
            set(iLoc, tLoc, KkIcons.get(res, "qs_pin"), on ? "LOCATION" : "LOCATION OFF", on);
        } catch (Throwable t) { XposedBridge.log(TAG + "loc: " + t); }
        try { // next alarm
            android.app.AlarmManager am = (android.app.AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
            android.app.AlarmManager.AlarmClockInfo ai = am.getNextAlarmClock();
            String text = "NO ALARM";
            if (ai != null) {
                text = new java.text.SimpleDateFormat("EEE HH:mm", java.util.Locale.getDefault())
                        .format(new java.util.Date(ai.getTriggerTime())).toUpperCase();
            }
            set(iAlarm, tAlarm, KkIcons.get(res, "qs_alarm"), text, ai != null);
        } catch (Throwable t) { XposedBridge.log(TAG + "alarm: " + t); }
    }

    // ------------------------------------------------------------------ actions
    static void openSettings(String action) {
        try {
            Intent i = new Intent(action).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ctx.startActivity(i);
            hide();
        } catch (Throwable t) { XposedBridge.log(TAG + "open " + action + ": " + t); }
    }

    static void cycleBrightness() {
        try {
            android.content.ContentResolver cr = ctx.getContentResolver();
            int cur = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS, 128);
            int[] lv = {25, 70, 128, 190, 255};
            int next = lv[0];
            for (int v : lv) { if (v > cur + 5) { next = v; break; } }
            Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS_MODE, 0);
            Settings.System.putInt(cr, Settings.System.SCREEN_BRIGHTNESS, next);
        } catch (Throwable t) { XposedBridge.log(TAG + "brightness: " + t); }
    }

    static void toggleWifi() {
        try {
            android.net.wifi.WifiManager wm = (android.net.wifi.WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
            boolean ok = wm.setWifiEnabled(!wm.isWifiEnabled());
            XposedBridge.log(TAG + "setWifiEnabled -> " + ok);
        } catch (Throwable t) { XposedBridge.log(TAG + "wifi toggle: " + t); }
    }

    static void toggleBluetooth() {
        try {
            BluetoothAdapter ad = BluetoothAdapter.getDefaultAdapter();
            if (ad == null) return;
            if (ad.isEnabled()) ad.disable(); else ad.enable();
        } catch (Throwable t) { XposedBridge.log(TAG + "bt toggle: " + t); }
    }

    static void toggleAirplane() {
        try {
            boolean on = Settings.Global.getInt(ctx.getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0) == 1;
            Object cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            XposedHelpers.callMethod(cm, "setAirplaneMode", !on);
        } catch (Throwable t) { XposedBridge.log(TAG + "airplane toggle: " + t); }
    }

    static void toggleLocation() {
        try {
            android.location.LocationManager lm = (android.location.LocationManager) ctx.getSystemService(Context.LOCATION_SERVICE);
            XposedHelpers.callMethod(lm, "setLocationEnabledForUser", !lm.isLocationEnabled(),
                    android.os.Process.myUserHandle());
        } catch (Throwable t) { XposedBridge.log(TAG + "location toggle: " + t); }
    }

    // ------------------------------------------------------------------ drawn glyphs
    /** 0 = brightness, 1 = settings gear, 2 = location pin, 3 = alarm clock, 4 = list */
    static Drawable glyph(Resources res, int kind) {
        Bitmap bmp = Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_8888);
        Canvas cv = new Canvas(bmp);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.WHITE);
        if (kind == 1) {                              // gear
            cv.translate(64, 64);
            p.setStyle(Paint.Style.FILL);
            for (int i = 0; i < 8; i++) {
                cv.drawRoundRect(new RectF(-9, -58, 9, -36), 3, 3, p);
                cv.rotate(45);
            }
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(20);
            cv.drawCircle(0, 0, 36, p);
        } else if (kind == 0) {                       // brightness (sun with A)
            cv.translate(64, 64);
            p.setStyle(Paint.Style.FILL);
            for (int i = 0; i < 12; i++) {
                cv.drawRoundRect(new RectF(-6, -60, 6, -42), 3, 3, p);
                cv.rotate(30);
            }
            cv.drawCircle(0, 0, 38, p);
            Paint t = new Paint(Paint.ANTI_ALIAS_FLAG);
            t.setColor(Color.BLACK);
            t.setTextSize(52);
            t.setTypeface(Typeface.DEFAULT_BOLD);
            t.setTextAlign(Paint.Align.CENTER);
            cv.drawText("A", 0, 18, t);
        } else if (kind == 2) {                       // location pin
            p.setStyle(Paint.Style.FILL);
            cv.drawCircle(64, 50, 34, p);
            Path tri = new Path();
            tri.moveTo(34, 68); tri.lineTo(64, 116); tri.lineTo(94, 68); tri.close();
            cv.drawPath(tri, p);
            Paint clr = new Paint(Paint.ANTI_ALIAS_FLAG);
            clr.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            cv.drawCircle(64, 50, 13, clr);
        } else if (kind == 3) {                       // alarm clock
            p.setStyle(Paint.Style.STROKE);
            p.setStrokeWidth(9);
            p.setStrokeCap(Paint.Cap.ROUND);
            cv.drawCircle(64, 72, 38, p);
            cv.drawLine(64, 72, 64, 48, p);
            cv.drawLine(64, 72, 82, 82, p);
            p.setStyle(Paint.Style.FILL);
            cv.drawCircle(24, 30, 14, p);
            cv.drawCircle(104, 30, 14, p);
        } else if (kind == 5) {                       // user silhouette
            p.setStyle(Paint.Style.FILL);
            cv.drawCircle(64, 42, 24, p);
            RectF body = new RectF(24, 74, 104, 150);
            cv.drawArc(body, 180, 180, true, p);
        } else {                                      // list icon (header)
            p.setStyle(Paint.Style.FILL);
            for (int i = 0; i < 3; i++) {
                int y = 30 + i * 32;
                cv.drawRect(14, y - 6, 26, y + 6, p);
                cv.drawRect(38, y - 6, 114, y + 6, p);
            }
        }
        return new BitmapDrawable(res, bmp);
    }
}
