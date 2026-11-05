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
                    lp.dimAmount = 0.6f;
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
        FrameLayout root = new FrameLayout(c);
        root.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) { dismiss(c); }
        });

        ScrollView sv = new ScrollView(c);
        final LinearLayout list = new LinearLayout(c);
        list.setOrientation(LinearLayout.VERTICAL);
        list.setPadding(dp(c, 8), dp(c, 48), dp(c, 8), dp(c, 64));
        sv.addView(list);
        root.addView(sv, new FrameLayout.LayoutParams(-1, -1));

        PackageManager pm = c.getPackageManager();
        for (Object info : recentTasks(c)) {
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

            final LinearLayout row = new LinearLayout(c);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setPadding(0, dp(c, 6), 0, dp(c, 6));

            LinearLayout left = new LinearLayout(c);
            left.setOrientation(LinearLayout.VERTICAL);
            left.setGravity(Gravity.BOTTOM);
            TextView tv = new TextView(c);
            tv.setText(label); tv.setTextColor(Color.WHITE);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            tv.setSingleLine(true);
            View line = new View(c);
            line.setBackgroundColor(0xFF33B5E5);
            left.addView(tv);
            left.addView(line, new LinearLayout.LayoutParams(-1, dp(c, 2)));
            row.addView(left, new LinearLayout.LayoutParams(0, dp(c, 160), 0.3f));

            LinearLayout card = new LinearLayout(c);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setBackgroundColor(0xFF1A1A1A);
            LinearLayout head = new LinearLayout(c);
            head.setGravity(Gravity.CENTER_VERTICAL);
            head.setPadding(dp(c, 6), dp(c, 4), dp(c, 6), dp(c, 4));
            ImageView ic = new ImageView(c);
            if (icon != null) ic.setImageDrawable(icon);
            head.addView(ic, new LinearLayout.LayoutParams(dp(c, 22), dp(c, 22)));
            TextView ht = new TextView(c);
            ht.setText(label); ht.setTextColor(0xFFCCCCCC); ht.setSingleLine(true);
            ht.setPadding(dp(c, 6), 0, 0, 0);
            head.addView(ht);
            card.addView(head);
            ImageView thumb = new ImageView(c);
            thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Bitmap bm = thumbnail(taskId);
            if (bm != null) thumb.setImageBitmap(bm);
            card.addView(thumb, new LinearLayout.LayoutParams(-1, 0, 1f));
            row.addView(card, new LinearLayout.LayoutParams(0, dp(c, 160), 0.7f));

            card.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) { dismiss(c); startTask(taskId); }
            });
            card.setOnLongClickListener(new View.OnLongClickListener() {
                @Override public boolean onLongClick(View v) {
                    removeTask(taskId); list.removeView(row); return true;
                }
            });

            list.addView(row);
        }
        return root;
    }

    @SuppressWarnings("unchecked")
    static List<Object> recentTasks(Context c) throws Throwable {
        Object atm = XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("android.app.ActivityTaskManager", null), "getInstance");
        Object userId = XposedHelpers.callStaticMethod(
                XposedHelpers.findClass("android.os.UserHandle", null), "myUserId");
        Object slice = XposedHelpers.callMethod(atm, "getRecentTasks", 20, 0x2, userId);
        return (List<Object>) XposedHelpers.callMethod(slice, "getList");
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
