package com.rat.nm.util;

import android.util.Log;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 日志管理工具
 */
public class LogUtil {

    private static final String TAG = "com.rat.nm";
    public static boolean enable = true;

    private LogUtil() {
    }

    public static void i(String msg) {
        if (enable) {
            Log.i(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (enable) {
            Log.v(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (enable) {
            Log.d(TAG, msg);
        }
    }

    public static void e(String msg, Throwable e) {
        if (enable) {
            Log.e(TAG, msg, e);
        }
    }

    public static void w(String msg) {
        if (enable) {
            Log.w(TAG, msg);
        }
    }
}
