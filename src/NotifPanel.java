package ics.recents;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Looper;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * KitKat-style notification panel. Opens when you swipe down on the LEFT side of the status bar.
 * Notifications come from a NotificationListenerService registered as a system service
 * (the same way SystemUI itself listens).
 */
public class NotifPanel {
    static final String TAG = "[IcsNotif] ";

    static View root;
    static View panelView;
    static Context ctx;
    static LinearLayout listBox;
    static TextView carrierView;
    static QsListener listener;
    static boolean registering;
    static final Handler H = new Handler(Looper.getMainLooper());

    // ------------------------------------------------------------------ listener
    public static class QsListener extends NotificationListenerService {
        @Override public void onNotificationPosted(StatusBarNotification sbn) { postRefresh(); }
        @Override public void onNotificationRemoved(StatusBarNotification sbn) { postRefresh(); }
        @Override public void onNotificationPosted(StatusBarNotification sbn, RankingMap rm) { postRefresh(); }
        @Override public void onNotificationRemoved(StatusBarNotification sbn, RankingMap rm) { postRefresh(); }
        @Override public void onListenerConnected() { postRefresh(); }
    }

    static void postRefresh() {
        H.post(new Runnable() {
            @Override public void run() { if (root != null) rebuildList(); }
        });
    }

    static void ensureListener(Context c) {
        if (listener != null || registering) return;
        registering = true;
        try {
            QsListener l = new QsListener();
            ComponentName cn = new ComponentName("ics.recents", "ics.recents.NotifPanel$QsListener");
            XposedHelpers.callMethod(l, "registerAsSystemService", c, cn, -1 /* USER_ALL */);
            listener = l;
            XposedBridge.log(TAG + "listener registered");
        } catch (Throwable t) {
            XposedBridge.log(TAG + "listener failed: " + t);
        } finally { registering = false; }
    }

    // ------------------------------------------------------------------ show / hide
    static int dp(Context c, float v) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v,
                c.getResources().getDisplayMetrics());
    }

    static void show(Context c0) {
        if (root != null) return;
        final Context c = c0.getApplicationContext() != null ? c0.getApplicationContext() : c0;
        ctx = c;
        H.post(new Runnable() {
            @Override public void run() {
                try {
                    if (root != null) return;
                    ensureListener(c);
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
                    rebuildList();
                    panelView.setTranslationY(-dp(c, 500));
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

    // ------------------------------------------------------------------ UI
    static class MaxScroll extends ScrollView {
        final int max;
        MaxScroll(Context c, int max) { super(c); this.max = max; }
        @Override protected void onMeasure(int w, int h) {
            super.onMeasure(w, View.MeasureSpec.makeMeasureSpec(max, View.MeasureSpec.AT_MOST));
        }
    }

    static View build(final Context c) {
        Resources res = c.getResources();
        int H0 = res.getDisplayMetrics().heightPixels;

        FrameLayout r = new FrameLayout(c);
        r.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { hide(); }
        });

        LinearLayout panel = new LinearLayout(c);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setBackgroundColor(0xFF000000);
        panel.setClickable(true);
        panelView = panel;
        r.addView(panel, new FrameLayout.LayoutParams(-1, -2));

        // header: clock + date, QS switch icon, clear-all icon
        LinearLayout head = new LinearLayout(c);
        head.setOrientation(LinearLayout.HORIZONTAL);
        head.setGravity(Gravity.BOTTOM);
        head.setPadding(dp(c, 14), dp(c, 2), dp(c, 8), dp(c, 6));

        TextClock time = new TextClock(c);
        time.setFormat12Hour("h:mm");
        time.setFormat24Hour("HH:mm");
        time.setTextColor(Color.WHITE);
        time.setTextSize(TypedValue.COMPLEX_UNIT_SP, 46);
        time.setTypeface(Typeface.create("sans-serif-thin", Typeface.NORMAL));
        time.setIncludeFontPadding(false);
        head.addView(time);

        TextClock date = new TextClock(c);
        date.setFormat12Hour("EEE, MMMM d");
        date.setFormat24Hour("EEE, MMMM d");
        date.setTextColor(0xFFB0B0B0);
        date.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        date.setAllCaps(true);
        date.setPadding(dp(c, 10), 0, 0, dp(c, 6));
        head.addView(date);

        View sp = new View(c);
        head.addView(sp, new LinearLayout.LayoutParams(0, 1, 1f));

        ImageView toggle = new ImageView(c);
        toggle.setImageDrawable(KkIcons.get(res, "qs_toggle"));
        toggle.setScaleType(ImageView.ScaleType.FIT_CENTER);
        toggle.setPadding(dp(c, 6), dp(c, 6), dp(c, 6), dp(c, 6));
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { hide(); QsPanel.show(ctx); }
        });
        head.addView(toggle, new LinearLayout.LayoutParams(dp(c, 48), dp(c, 48)));

        ImageView clear = new ImageView(c);
        clear.setImageDrawable(KkIcons.get(res, "clear"));
        clear.setScaleType(ImageView.ScaleType.FIT_CENTER);
        clear.setPadding(dp(c, 6), dp(c, 6), dp(c, 6), dp(c, 6));
        clear.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                try { if (listener != null) listener.cancelAllNotifications(); }
                catch (Throwable t) { XposedBridge.log(TAG + "clear all: " + t); }
                H.postDelayed(new Runnable() { @Override public void run() { if (root != null) rebuildList(); } }, 300);
            }
        });
        head.addView(clear, new LinearLayout.LayoutParams(dp(c, 48), dp(c, 48)));
        panel.addView(head, new LinearLayout.LayoutParams(-1, -2));

        // notification list (capped at ~70% of the screen height)
        MaxScroll sv = new MaxScroll(c, (int) (H0 * 0.70f));
        sv.setVerticalScrollBarEnabled(false);
        sv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        listBox = new LinearLayout(c);
        listBox.setOrientation(LinearLayout.VERTICAL);
        sv.addView(listBox, new ViewGroupLP());
        panel.addView(sv, new LinearLayout.LayoutParams(-1, -2));

        // carrier name bar
        carrierView = new TextView(c);
        carrierView.setTextColor(0xFFA0A0A0);
        carrierView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
        carrierView.setGravity(Gravity.CENTER);
        carrierView.setBackgroundColor(0xFF0D0D12);
        try {
            android.telephony.TelephonyManager tm =
                    (android.telephony.TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
            String op = tm.getNetworkOperatorName();
            carrierView.setText(op == null ? "" : op.toUpperCase());
        } catch (Throwable ignored) {}
        panel.addView(carrierView, new LinearLayout.LayoutParams(-1, dp(c, 44)));

        // handle line
        View line = new View(c);
        line.setBackgroundColor(0xFFC0C0C0);
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(-1, dp(c, 3));
        llp.setMargins(dp(c, 12), dp(c, 10), dp(c, 12), dp(c, 10));
        panel.addView(line, llp);
        return r;
    }

    static class ViewGroupLP extends android.view.ViewGroup.LayoutParams {
        ViewGroupLP() { super(-1, -2); }
    }

    static void rebuildList() {
        final Context c = ctx;
        if (c == null || listBox == null) return;
        listBox.removeAllViews();
        StatusBarNotification[] arr = null;
        try { if (listener != null) arr = listener.getActiveNotifications(); }
        catch (Throwable t) { XposedBridge.log(TAG + "getActive: " + t); }
        ArrayList<StatusBarNotification> items = new ArrayList<StatusBarNotification>();
        if (arr != null) {
            for (StatusBarNotification s : arr) {
                if ((s.getNotification().flags & Notification.FLAG_GROUP_SUMMARY) != 0) continue;
                items.add(s);
            }
        }
        Collections.sort(items, new Comparator<StatusBarNotification>() {
            @Override public int compare(StatusBarNotification a, StatusBarNotification b) {
                return Long.compare(b.getPostTime(), a.getPostTime());
            }
        });
        if (items.isEmpty()) {
            TextView none = new TextView(c);
            none.setText("No notifications");
            none.setTextColor(0xFF9E9E9E);
            none.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            none.setGravity(Gravity.CENTER);
            none.setPadding(0, dp(c, 28), 0, dp(c, 28));
            none.setBackgroundColor(0xFF1A1A1A);
            listBox.addView(none, new LinearLayout.LayoutParams(-1, -2));
            return;
        }
        for (StatusBarNotification s : items) {
            try { listBox.addView(row(c, s)); }
            catch (Throwable t) { XposedBridge.log(TAG + "row: " + t); }
        }
    }

    static Drawable load(Icon ic, Context c, String pkg) {
        if (ic == null) return null;
        try {
            Context pc = c;
            try { pc = c.createPackageContext(pkg, Context.CONTEXT_IGNORE_SECURITY); } catch (Throwable ignored) {}
            return ic.loadDrawable(pc);
        } catch (Throwable t) { return null; }
    }

    static View row(final Context c, final StatusBarNotification sbn) {
        final Notification n = sbn.getNotification();
        String pkg = sbn.getPackageName();
        int rowH = dp(c, 61);

        CharSequence title = n.extras.getCharSequence(Notification.EXTRA_TITLE);
        CharSequence text = n.extras.getCharSequence(Notification.EXTRA_TEXT);
        if (title == null || title.length() == 0) {
            try {
                android.content.pm.PackageManager pm = c.getPackageManager();
                title = pm.getApplicationLabel(pm.getApplicationInfo(pkg, 0));
            } catch (Throwable t) { title = pkg; }
        }

        LinearLayout item = new LinearLayout(c);
        item.setOrientation(LinearLayout.HORIZONTAL);
        item.setBackgroundColor(0xFF1A1A1A);
        LinearLayout.LayoutParams ilp = new LinearLayout.LayoutParams(-1, rowH);
        ilp.bottomMargin = 2;
        item.setLayoutParams(ilp);

        // left square: large icon, or the small icon centred
        ImageView left = new ImageView(c);
        Drawable big = load(n.getLargeIcon(), c, pkg);
        Drawable small = load(n.getSmallIcon(), c, pkg);
        if (big != null) {
            left.setScaleType(ImageView.ScaleType.CENTER_CROP);
            left.setImageDrawable(big);
        } else {
            left.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            left.setPadding(dp(c, 16), dp(c, 16), dp(c, 16), dp(c, 16));
            if (small != null) { small.mutate().setTint(Color.WHITE); left.setImageDrawable(small); }
        }
        item.addView(left, new LinearLayout.LayoutParams(rowH, rowH));

        // middle: title + text
        LinearLayout mid = new LinearLayout(c);
        mid.setOrientation(LinearLayout.VERTICAL);
        mid.setGravity(Gravity.CENTER_VERTICAL);
        mid.setPadding(dp(c, 12), 0, dp(c, 4), 0);
        TextView tt = new TextView(c);
        tt.setText(title);
        tt.setTextColor(Color.WHITE);
        tt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 19);
        tt.setTypeface(Typeface.DEFAULT_BOLD);
        tt.setSingleLine(true);
        tt.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mid.addView(tt);
        TextView tx = new TextView(c);
        tx.setText(text == null ? "" : text);
        tx.setTextColor(0xFFB8B8B8);
        tx.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        tx.setSingleLine(true);
        tx.setEllipsize(android.text.TextUtils.TruncateAt.END);
        mid.addView(tx);
        item.addView(mid, new LinearLayout.LayoutParams(0, -1, 1f));

        // right: time on top, small app icon below
        LinearLayout right = new LinearLayout(c);
        right.setOrientation(LinearLayout.VERTICAL);
        right.setGravity(Gravity.RIGHT);
        right.setPadding(0, dp(c, 8), dp(c, 12), dp(c, 8));
        TextView tm = new TextView(c);
        tm.setText(new java.text.SimpleDateFormat("h:mm a", java.util.Locale.getDefault())
                .format(new java.util.Date(sbn.getPostTime())).toUpperCase());
        tm.setTextColor(0xFFB8B8B8);
        tm.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        right.addView(tm);
        View gap = new View(c);
        right.addView(gap, new LinearLayout.LayoutParams(1, 0, 1f));
        ImageView si = new ImageView(c);
        Drawable small2 = load(n.getSmallIcon(), c, pkg);
        if (small2 != null) { small2.mutate().setTint(0xFFB8B8B8); si.setImageDrawable(small2); }
        right.addView(si, new LinearLayout.LayoutParams(dp(c, 20), dp(c, 20)));
        item.addView(right, new LinearLayout.LayoutParams(dp(c, 74), -1));

        item.setOnTouchListener(new RowTouch(c, sbn));
        return item;
    }

    /** Tap = open the notification, swipe sideways = dismiss it. */
    static class RowTouch implements View.OnTouchListener {
        final Context c; final StatusBarNotification sbn; final int slop;
        float downX, downY; boolean swiping; VelocityTracker vt;

        RowTouch(Context c, StatusBarNotification sbn) {
            this.c = c; this.sbn = sbn;
            this.slop = ViewConfiguration.get(c).getScaledTouchSlop();
        }

        void open() {
            try {
                PendingIntent pi = sbn.getNotification().contentIntent;
                hide();
                if (pi != null) pi.send();
                if ((sbn.getNotification().flags & Notification.FLAG_AUTO_CANCEL) != 0
                        && sbn.isClearable() && listener != null) {
                    listener.cancelNotification(sbn.getKey());
                }
            } catch (Throwable t) { XposedBridge.log(TAG + "open: " + t); }
        }

        void dismissed(View v) {
            try { if (listener != null) listener.cancelNotification(sbn.getKey()); }
            catch (Throwable t) { XposedBridge.log(TAG + "dismiss: " + t); }
            if (v.getParent() instanceof LinearLayout) ((LinearLayout) v.getParent()).removeView(v);
        }

        @Override public boolean onTouch(final View v, MotionEvent e) {
            switch (e.getActionMasked()) {
                case MotionEvent.ACTION_DOWN:
                    downX = e.getRawX(); downY = e.getRawY(); swiping = false;
                    if (vt != null) vt.recycle();
                    vt = VelocityTracker.obtain(); vt.addMovement(e);
                    return true;
                case MotionEvent.ACTION_MOVE: {
                    if (vt == null) return true;
                    vt.addMovement(e);
                    float dx = e.getRawX() - downX, dy = e.getRawY() - downY;
                    if (!swiping && Math.abs(dx) > slop && Math.abs(dx) > Math.abs(dy) && sbn.isClearable()) {
                        swiping = true;
                        if (v.getParent() != null) v.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    if (swiping) {
                        v.setTranslationX(dx);
                        v.setAlpha(Math.max(0f, 1f - Math.abs(dx) / v.getWidth()));
                    }
                    return true;
                }
                case MotionEvent.ACTION_UP: {
                    if (vt == null) return true;
                    vt.addMovement(e); vt.computeCurrentVelocity(1000);
                    float vx = vt.getXVelocity(); vt.recycle(); vt = null;
                    float dx = e.getRawX() - downX, dy = e.getRawY() - downY;
                    if (swiping) {
                        boolean away = Math.abs(dx) > v.getWidth() * 0.4f || (Math.abs(vx) > 1000 && vx * dx > 0);
                        if (away) {
                            float dir = dx > 0 ? 1f : -1f;
                            v.animate().translationX(dir * v.getWidth()).alpha(0f).setDuration(150)
                                    .withEndAction(new Runnable() {
                                        @Override public void run() { dismissed(v); }
                                    });
                        } else {
                            v.animate().translationX(0f).alpha(1f).setDuration(150);
                        }
                    } else if (Math.abs(dx) < slop && Math.abs(dy) < slop) {
                        open();
                    }
                    return true;
                }
                case MotionEvent.ACTION_CANCEL:
                    if (vt != null) { vt.recycle(); vt = null; }
                    v.animate().translationX(0f).alpha(1f).setDuration(150);
                    return true;
            }
            return false;
        }
    }
}
