package ics.recents;

import android.app.KeyguardManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

/**
 * Lock screen notifications drawn as the shade-panel rows (flat dark rows, big bold title, time + small icon on
 * the right). The native cards are made invisible (kept in place, so the media player stays), these rows are
 * shown under the media card. Tap = unlock, then open; swipe sideways = dismiss. Private content stays hidden
 * on a secure lock screen unless the user's "show sensitive content" setting allows it.
 */
public class LockNotifs extends LinearLayout {
    static final String TAG = "[IcsSysUi] ";
    static final String TAGKEY = "ics_lock_notifs";
    static LockNotifs instance;

    final Handler h = new Handler(Looper.getMainLooper());
    final View root;
    final float u;
    String sig = "";
    boolean shown;
    StatusBarNotification pendingSbn;
    long pendingUntil;
    boolean warnedNssl;

    LockNotifs(Context c, View root) {
        super(c);
        this.root = root;
        u = c.getResources().getDisplayMetrics().density;
        setOrientation(LinearLayout.VERTICAL);
        setVisibility(View.GONE);
    }

    // ------------------------------------------------------------ install
    static void attach(final View top) {
        if (!(top instanceof FrameLayout)) return;
        top.post(new Runnable() {
            @Override public void run() {
                try {
                    FrameLayout f = (FrameLayout) top;
                    for (int i = 0; i < f.getChildCount(); i++)
                        if (TAGKEY.equals(f.getChildAt(i).getTag())) return;
                    LockNotifs n = new LockNotifs(f.getContext(), f);
                    n.setTag(TAGKEY);
                    f.addView(n, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.TOP));
                    instance = n;
                    XposedBridge.log(TAG + "locknotifs added");
                } catch (Throwable t) { XposedBridge.log(TAG + "locknotifs attach: " + t); }
            }
        });
    }

    @Override protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try { NotifPanel.ensureListener(getContext()); } catch (Throwable t) { XposedBridge.log(TAG + "locknotifs listener: " + t); }
        h.post(poll);
    }

    @Override protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        h.removeCallbacks(poll);
    }

    final Runnable poll = new Runnable() {
        @Override public void run() {
            try { tick(); } catch (Throwable t) { XposedBridge.log(TAG + "locknotifs tick: " + t); }
            h.postDelayed(this, 350);
        }
    };

    // ------------------------------------------------------------ helpers
    View byName(String name) {
        int id = getResources().getIdentifier(name, "id", "com.android.systemui");
        return id == 0 ? null : root.findViewById(id);
    }

    boolean locked() {
        KeyguardManager km = (KeyguardManager) getContext().getSystemService(Context.KEYGUARD_SERVICE);
        return km != null && km.isKeyguardLocked();
    }

    boolean lockScreenShowing() {
        Context c = getContext();
        PowerManager pm = (PowerManager) c.getSystemService(Context.POWER_SERVICE);
        if (!locked() || pm == null || !pm.isInteractive()) return false;
        View st = byName("keyguard_status_view");
        if (st == null || !st.isShown() || st.getAlpha() < 0.9f) return false;
        View bouncer = byName("keyguard_bouncer_container");
        if (bouncer instanceof ViewGroup) {
            ViewGroup g = (ViewGroup) bouncer;
            for (int i = 0; i < g.getChildCount(); i++) {
                View k = g.getChildAt(i);
                if (k.isShown() && k.getAlpha() > 0.5f) return false;
            }
        }
        return true;
    }

    int bottomIn(View v) {
        int[] a = new int[2], b = new int[2];
        v.getLocationInWindow(a);
        root.getLocationInWindow(b);
        return a[1] - b[1] + v.getHeight();
    }

    // ------------------------------------------------------------ main loop
    void tick() {
        checkPending();
        if (!lockScreenShowing()) { release(); return; }
        rebuildIfChanged();
        if (getChildCount() == 0) { release(); return; }
        position();
        if (getVisibility() != View.VISIBLE) setVisibility(View.VISIBLE);
        shown = true;
        hideNative(true);
    }

    /** overlay off, native cards back */
    void release() {
        if (shown) {
            shown = false;
            hideNative(false);
        }
        if (getVisibility() != View.GONE) setVisibility(View.GONE);
        if (sig.length() > 0) { sig = ""; removeAllViews(); }
    }

    int rowsTop() {
        int top = 0;
        View st = byName("keyguard_status_view");
        if (st != null) top = bottomIn(st);
        View media = byName("qs_media_controls");
        if (media != null && media.isShown()) top = Math.max(top, bottomIn(media));
        return top + (int) (8 * u);
    }

    int availBottom() {
        int H = getResources().getDisplayMetrics().heightPixels;
        return (int) (H * 0.79f - 155 * u);                 // keep clear of the unlock ring's top target
    }

    void position() {
        int top = rowsTop();
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) getLayoutParams();
        if (lp.topMargin != top) { lp.topMargin = top; setLayoutParams(lp); }
    }

    /** make the native cards invisible without moving them (NSSL animates alpha itself, transitionAlpha it doesn't touch) */
    void hideNative(boolean hide) {
        View nssl = byName("notification_stack_scroller");
        if (!(nssl instanceof ViewGroup)) {
            if (!warnedNssl) { warnedNssl = true; XposedBridge.log(TAG + "locknotifs: notification_stack_scroller not found"); }
            return;
        }
        int media = getResources().getIdentifier("qs_media_controls", "id", "com.android.systemui");
        ViewGroup g = (ViewGroup) nssl;
        for (int i = 0; i < g.getChildCount(); i++) {
            View ch = g.getChildAt(i);
            if (media != 0 && ch.findViewById(media) != null) continue;      // keep the media player
            try { XposedHelpers.callMethod(ch, "setTransitionAlpha", hide ? 0f : 1f); } catch (Throwable ignored) {}
        }
    }

    // ------------------------------------------------------------ privacy
    int secureInt(String name, int def) {
        Context c = getContext();
        try {
            int user = (Integer) XposedHelpers.callStaticMethod(android.app.ActivityManager.class, "getCurrentUser");
            return (Integer) XposedHelpers.callStaticMethod(Settings.Secure.class, "getIntForUser",
                    c.getContentResolver(), name, def, user);
        } catch (Throwable t) {
            try { return Settings.Secure.getInt(c.getContentResolver(), name, def); } catch (Throwable t2) { return def; }
        }
    }

    /** 0 = show, 1 = show with content hidden, 2 = do not show */
    int redact(StatusBarNotification s) {
        KeyguardManager km = (KeyguardManager) getContext().getSystemService(Context.KEYGUARD_SERVICE);
        if (km == null || !km.isKeyguardSecure()) return 0;
        int vis = s.getNotification().visibility;
        if (vis == Notification.VISIBILITY_SECRET) return 2;
        if (vis == Notification.VISIBILITY_PUBLIC) return 0;
        return secureInt("lock_screen_allow_private_notifications", 0) != 0 ? 0 : 1;
    }

    // ------------------------------------------------------------ list
    void rebuildIfChanged() {
        if (NotifPanel.listener == null) return;
        if (secureInt("lock_screen_show_notifications", 1) == 0) { if (sig.length() > 0) { sig = ""; removeAllViews(); } return; }
        StatusBarNotification[] arr = null;
        try { arr = NotifPanel.listener.getActiveNotifications(); } catch (Throwable t) { return; }
        ArrayList<StatusBarNotification> items = new ArrayList<StatusBarNotification>();
        StringBuilder sg = new StringBuilder();
        if (arr != null) {
            for (StatusBarNotification s : arr) {
                Notification n = s.getNotification();
                if ((n.flags & Notification.FLAG_GROUP_SUMMARY) != 0) continue;
                if (n.extras != null && n.extras.containsKey(Notification.EXTRA_MEDIA_SESSION)) continue;   // native media card covers it
                if (redact(s) == 2) continue;
                items.add(s);
            }
        }
        Collections.sort(items, new Comparator<StatusBarNotification>() {
            @Override public int compare(StatusBarNotification a, StatusBarNotification b) {
                return Long.compare(b.getPostTime(), a.getPostTime());
            }
        });
        int top = rowsTop();
        int rowPx = NotifPanel.dp(getContext(), 61) + 2;
        int maxRows = Math.max(1, (availBottom() - top) / rowPx);
        boolean more = items.size() > maxRows && maxRows >= 2;
        int count = more ? maxRows - 1 : Math.min(items.size(), maxRows);
        for (int i = 0; i < count; i++) {
            StatusBarNotification s = items.get(i);
            sg.append(s.getKey()).append(s.getPostTime()).append(redact(s)).append(';');
        }
        sg.append(items.size()).append('/').append(count);
        String now = sg.toString();
        if (now.equals(sig)) return;
        sig = now;
        removeAllViews();
        Context c = getContext();
        for (int i = 0; i < count; i++) {
            StatusBarNotification s = items.get(i);
            try {
                View row;
                if (redact(s) == 1) row = NotifPanel.row(c, s, appLabel(s), "Contents hidden", true);
                else row = NotifPanel.row(c, s);
                row.setOnTouchListener(new LockTouch(c, s));
                addView(row);
            } catch (Throwable t) { XposedBridge.log(TAG + "locknotifs row: " + t); }
        }
        if (more) {
            TextView m = new TextView(c);
            m.setText("+" + (items.size() - count) + " more");
            m.setTextColor(0xFFB8B8B8);
            m.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
            m.setGravity(Gravity.CENTER);
            m.setBackgroundColor(0xFF1A1A1A);
            addView(m, new LinearLayout.LayoutParams(-1, NotifPanel.dp(c, 36)));
        }
    }

    CharSequence appLabel(StatusBarNotification s) {
        try {
            PackageManager pm = getContext().getPackageManager();
            return pm.getApplicationLabel(pm.getApplicationInfo(s.getPackageName(), 0));
        } catch (Throwable t) { return s.getPackageName(); }
    }

    // ------------------------------------------------------------ tap / swipe
    static class LockTouch extends NotifPanel.RowTouch {
        LockTouch(Context c, StatusBarNotification sbn) { super(c, sbn); }
        @Override void open() { LockNotifs.openRow(sbn); }
    }

    static void openRow(StatusBarNotification sbn) {
        LockNotifs me = instance;
        if (me == null) return;
        if (!me.locked()) { me.fire(sbn); return; }
        me.pendingSbn = sbn;
        me.pendingUntil = android.os.SystemClock.uptimeMillis() + 12000;
        LockRing.swipeUp(me.root, me.h, me.u);               // unlock first (bouncer if secure), then open
        XposedBridge.log(TAG + "locknotifs: unlock to open " + sbn.getPackageName());
    }

    void checkPending() {
        if (pendingSbn == null) return;
        if (android.os.SystemClock.uptimeMillis() > pendingUntil) { pendingSbn = null; return; }
        if (!locked()) { StatusBarNotification s = pendingSbn; pendingSbn = null; fire(s); }
    }

    void fire(StatusBarNotification sbn) {
        try {
            PendingIntent pi = sbn.getNotification().contentIntent;
            if (pi != null) pi.send();
            if ((sbn.getNotification().flags & Notification.FLAG_AUTO_CANCEL) != 0 && sbn.isClearable()
                    && NotifPanel.listener != null) NotifPanel.listener.cancelNotification(sbn.getKey());
            XposedBridge.log(TAG + "locknotifs: opened " + sbn.getPackageName());
        } catch (Throwable t) { XposedBridge.log(TAG + "locknotifs open failed: " + t); }
    }
}
