package ics.recents;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Hook implements IXposedHookLoadPackage {
    static final String TAG = "[IcsRecents] ";
    static View panel;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpp) {
        Class<?> helper = XposedHelpers.findClassIfExists(
                "com.android.quickstep.OverviewCommandHelper", lpp.classLoader);
        if (helper == null) return;
        XposedBridge.log(TAG + "found OverviewCommandHelper in " + lpp.packageName);

        // Logging only: shows what the helper receives
        XposedBridge.hookAllMethods(helper, "addCommand", new de.robv.android.xposed.XC_MethodHook() {
            @Override protected void beforeHookedMethod(MethodHookParam p) {
                XposedBridge.log(TAG + "addCommand " + java.util.Arrays.toString(p.args));
            }
        });

        // Real entry point for the recents button (3-button nav)
        Class<?> binder = XposedHelpers.findClassIfExists(
                "com.android.quickstep.TouchInteractionService$TISBinder", lpp.classLoader);
        XposedBridge.log(TAG + "TISBinder " + (binder != null ? "found" : "NOT found"));
        if (binder == null) return;
        XC_MethodReplacement open = new XC_MethodReplacement() {
            @Override protected Object replaceHookedMethod(MethodHookParam p) {
                XposedBridge.log(TAG + "binder " + p.method.getName());
                toggle(appContext());
                return null;
            }
        };
        XposedBridge.hookAllMethods(binder, "onOverviewToggle", open);
        XposedBridge.hookAllMethods(binder, "onOverviewShown", open);
    }

    static Context appContext() {
        try {
            return (Context) XposedHelpers.callStaticMethod(
                    XposedHelpers.findClass("android.app.ActivityThread", null), "currentApplication");
        } catch (Throwable t) { return null; }
    }

    static int dp(Context c, int v) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, v,
                c.getResources().getDisplayMetrics());
    }

    static void toggle(final Context c) {
        if (c == null) return;
        new android.os.Handler(android.os.Looper.getMainLooper()).post(new Runnable() {
            @Override public void run() {
                try {
                    WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
                    if (panel != null) { wm.removeView(panel); panel = null; return; }
                    panel = build(c);
                    WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT,
                            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                                    | WindowManager.LayoutParams.FLAG_DIM_BEHIND,
                            android.graphics.PixelFormat.TRANSLUCENT);
                    lp.dimAmount = 0.5f;
                    wm.addView(panel, lp);
                } catch (Throwable t) {
                    XposedBridge.log(TAG + "show failed: " + t);
                    panel = null;
                }
            }
        });
    }

    static void dismiss(Context c) {
        try {
            if (panel != null) {
                ((WindowManager) c.getSystemService(Context.WINDOW_SERVICE)).removeView(panel);
                panel = null;
            }
        } catch (Throwable ignored) { panel = null; }
    }

    static View build(final Context c) throws Throwable {
        final int W = c.getResources().getDisplayMetrics().widthPixels;
        FrameLayout root = new FrameLayout(c);
        root.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { dismiss(c); }
        });

        final ScrollView sv = new ScrollView(c);
        sv.setFillViewport(true);
        sv.setVerticalScrollBarEnabled(false);
        sv.setOverScrollMode(View.OVER_SCROLL_NEVER);
        final LinearLayout list = new LinearLayout(c);
        list.setOrientation(LinearLayout.VERTICAL);
        list.setGravity(Gravity.BOTTOM);
        list.setPadding(0, dp(c, 8), 0, dp(c, 8));
        list.setLayoutTransition(new android.animation.LayoutTransition());
        sv.addView(list, new ViewGroup.LayoutParams(-1, -2));
        root.addView(sv, new FrameLayout.LayoutParams(-1, -1));

        // ICS proportions: label column ~32%, card ~46%, ~22% empty on the right
        final int labelPad = (int) (W * 0.042f);
        final int labelW = (int) (W * 0.32f);
        final int cardW = (int) (W * 0.46f);
        final int headH = (int) (cardW * 0.14f);
        final int cardH = (int) (cardW * 0.87f);
        final int gap = dp(c, 4);
        final int[] count = {0};

        PackageManager pm = c.getPackageManager();
        java.util.ArrayList<Object> tasks = new java.util.ArrayList<Object>(recentTasks(c));
        java.util.Collections.reverse(tasks);   // most recent at the bottom, like ICS

        for (Object info : tasks) {
            final int taskId = XposedHelpers.getIntField(info, "taskId");
            android.content.Intent base =
                    (android.content.Intent) XposedHelpers.getObjectField(info, "baseIntent");
            if (base == null || base.getComponent() == null) continue;
            String pkg = base.getComponent().getPackageName();
            if (pkg.equals(c.getPackageName())) continue;

            CharSequence label = pkg; Drawable icon = null;
            try {
                label = pm.getApplicationLabel(pm.getApplicationInfo(pkg, 0));
                icon = pm.getApplicationIcon(pkg);
            } catch (Throwable ignored) {}

            LinearLayout item = new LinearLayout(c);
            item.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams ilp = new LinearLayout.LayoutParams(labelW + cardW, cardH);
            ilp.bottomMargin = gap;
            list.addView(item, ilp);

            // left: label sitting on a blue underline
            LinearLayout left = new LinearLayout(c);
            left.setOrientation(LinearLayout.VERTICAL);
            left.setGravity(Gravity.BOTTOM);
            left.setPadding(labelPad, 0, 0, 0);
            TextView tv = new TextView(c);
            tv.setText(label); tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            tv.setSingleLine(true);
            tv.setEllipsize(android.text.TextUtils.TruncateAt.END);
            tv.setPadding(0, 0, dp(c, 4), dp(c, 2));
            View line = new View(c);
            line.setBackgroundColor(0xFF33B5E5);
            left.addView(tv, new LinearLayout.LayoutParams(-1, -2));
            left.addView(line, new LinearLayout.LayoutParams(-1, Math.max(2, dp(c, 1))));
            item.addView(left, new LinearLayout.LayoutParams(labelW, -1));

            // right: card = dark header (icon + title) + top-aligned thumbnail
            LinearLayout card = new LinearLayout(c);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setBackgroundColor(0xFF000000);
            LinearLayout head = new LinearLayout(c);
            head.setGravity(Gravity.CENTER_VERTICAL);
            head.setBackgroundColor(0xFF1C1C1C);
            head.setPadding(dp(c, 4), 0, dp(c, 4), 0);
            ImageView ic = new ImageView(c);
            if (icon != null) ic.setImageDrawable(icon);
            int icSize = (int) (headH * 0.75f);
            head.addView(ic, new LinearLayout.LayoutParams(icSize, icSize));
            TextView ht = new TextView(c);
            ht.setText(label); ht.setTextColor(0xFFE0E0E0);
            ht.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
            ht.setSingleLine(true);
            ht.setEllipsize(android.text.TextUtils.TruncateAt.END);
            ht.setPadding(dp(c, 5), 0, 0, 0);
            head.addView(ht);
            card.addView(head, new LinearLayout.LayoutParams(-1, headH));

            ImageView thumb = new ImageView(c);
            thumb.setBackgroundColor(0xFF000000);
            final Bitmap bm = thumbnail(taskId);
            if (bm != null) {
                thumb.setScaleType(ImageView.ScaleType.MATRIX);
                thumb.setImageBitmap(bm);
                thumb.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
                    @Override public void onLayoutChange(View v, int l, int t, int r, int b,
                                                         int ol, int ot, int or, int ob) {
                        int vw = r - l;
                        if (vw <= 0 || bm.getWidth() <= 0) return;
                        float sc = (float) vw / bm.getWidth();
                        android.graphics.Matrix m = new android.graphics.Matrix();
                        m.setScale(sc, sc);               // scale to width, keep the top, crop bottom
                        ((ImageView) v).setImageMatrix(m);
                    }
                });
            }
            card.addView(thumb, new LinearLayout.LayoutParams(-1, 0, 1f));
            item.addView(card, new LinearLayout.LayoutParams(cardW, -1));

            count[0]++;
            item.setOnTouchListener(new Swipe(c, taskId, list, count));
        }

        if (count[0] == 0) {
            TextView none = new TextView(c);
            none.setText("No recent apps");
            none.setTextColor(0xFFCCCCCC);
            none.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            none.setPadding(dp(c, 24), 0, 0, dp(c, 24));
            list.addView(none);
        }

        sv.post(new Runnable() {
            @Override public void run() { sv.fullScroll(View.FOCUS_DOWN); }
        });
        return root;
    }

    /** Tap = open the task, swipe left/right = close it (like ICS). */
    static class Swipe implements View.OnTouchListener {
        final Context c; final int taskId; final LinearLayout list; final int[] count;
        final int slop; float downX, downY; boolean swiping; android.view.VelocityTracker vt;

        Swipe(Context c, int taskId, LinearLayout list, int[] count) {
            this.c = c; this.taskId = taskId; this.list = list; this.count = count;
            this.slop = android.view.ViewConfiguration.get(c).getScaledTouchSlop();
        }

        @Override public boolean onTouch(final View v, android.view.MotionEvent e) {
            switch (e.getActionMasked()) {
                case android.view.MotionEvent.ACTION_DOWN:
                    downX = e.getRawX(); downY = e.getRawY(); swiping = false;
                    if (vt != null) vt.recycle();
                    vt = android.view.VelocityTracker.obtain();
                    vt.addMovement(e);
                    return true;
                case android.view.MotionEvent.ACTION_MOVE: {
                    if (vt == null) return true;
                    vt.addMovement(e);
                    float dx = e.getRawX() - downX, dy = e.getRawY() - downY;
                    if (!swiping && Math.abs(dx) > slop && Math.abs(dx) > Math.abs(dy)) {
                        swiping = true;
                        if (v.getParent() != null) v.getParent().requestDisallowInterceptTouchEvent(true);
                    }
                    if (swiping) {
                        v.setTranslationX(dx);
                        v.setAlpha(Math.max(0f, 1f - Math.abs(dx) / v.getWidth()));
                    }
                    return true;
                }
                case android.view.MotionEvent.ACTION_UP: {
                    if (vt == null) return true;
                    vt.addMovement(e);
                    vt.computeCurrentVelocity(1000);
                    float vx = vt.getXVelocity();
                    vt.recycle(); vt = null;
                    float dx = e.getRawX() - downX, dy = e.getRawY() - downY;
                    if (swiping) {
                        boolean away = Math.abs(dx) > v.getWidth() * 0.4f
                                || (Math.abs(vx) > 1000 && vx * dx > 0);
                        if (away) {
                            float dir = dx > 0 ? 1f : -1f;
                            v.animate().translationX(dir * v.getWidth()).alpha(0f).setDuration(150)
                                    .withEndAction(new Runnable() {
                                        @Override public void run() {
                                            removeTask(taskId);
                                            list.removeView(v);
                                            count[0]--;
                                            if (count[0] <= 0) dismiss(c);
                                        }
                                    });
                        } else {
                            v.animate().translationX(0f).alpha(1f).setDuration(150);
                        }
                    } else if (Math.abs(dx) < slop && Math.abs(dy) < slop) {
                        dismiss(c);
                        startTask(taskId);
                    }
                    return true;
                }
                case android.view.MotionEvent.ACTION_CANCEL:
                    if (vt != null) { vt.recycle(); vt = null; }
                    v.animate().translationX(0f).alpha(1f).setDuration(150);
                    return true;
            }
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    static List<Object> recentTasks(Context c) throws Throwable {
        Object atm = XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("android.app.ActivityTaskManager", null), "getInstance");
        Object userId = XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("android.os.UserHandle", null), "myUserId");
        Object res = XposedHelpers.callMethod(atm, "getRecentTasks", 20, 0x2, userId);
        if (res instanceof List) return (List<Object>) res;
        return (List<Object>) XposedHelpers.callMethod(res, "getList");
    }

    static Bitmap thumbnail(int taskId) {
        try {
            Object svc = XposedHelpers.callStaticMethod(
                    XposedHelpers.findClass("android.app.ActivityTaskManager", null), "getService");
            Object snap = XposedHelpers.callMethod(svc, "getTaskSnapshot", taskId, false);
            if (snap == null) return null;
            Object hb = XposedHelpers.callMethod(snap, "getHardwareBuffer");
            Object cs = XposedHelpers.callMethod(snap, "getColorSpace");
            return Bitmap.wrapHardwareBuffer((android.hardware.HardwareBuffer) hb,
                    (android.graphics.ColorSpace) cs);
        } catch (Throwable t) { return null; }
    }

    static void startTask(int taskId) {
        try {
            Object svc = XposedHelpers.callStaticMethod(
                    XposedHelpers.findClass("android.app.ActivityTaskManager", null), "getService");
            XposedHelpers.callMethod(svc, "startActivityFromRecents", taskId, null);
        } catch (Throwable t) { XposedBridge.log(TAG + "startTask: " + t); }
    }

    static void removeTask(int taskId) {
        try {
            Object svc = XposedHelpers.callStaticMethod(
                    XposedHelpers.findClass("android.app.ActivityTaskManager", null), "getService");
            XposedHelpers.callMethod(svc, "removeTask", taskId);
        } catch (Throwable t) { XposedBridge.log(TAG + "removeTask: " + t); }
    }
}
