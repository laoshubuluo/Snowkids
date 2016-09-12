package com.rat.snowkids.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.rat.snowkids.appmanager.Application;
import com.rat.snowkids.common.Constant;


/**
 * 设置一些系统偏好参数
 *
 * @author L.jinzhu
 * @date 2015-09-07 18:25:16
 */
public class AppUtils {
    private static SharedPreferences sharedPreferences;
    private static PackageInfo packageInfo;
    private static AppUtils instance;
    private static Application context;

    /**
     * 获取一个单例
     *
     * @return
     */
    public static synchronized AppUtils getInstance() {
        if (instance == null) {
            instance = new AppUtils(context);
        }
        return instance;
    }

    /**
     * 构造方法
     *
     * @param context
     */
    private AppUtils(Context context) {
        sharedPreferences = context.getSharedPreferences(Constant.PREFS_NAME, Context.MODE_PRIVATE);
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    public static AppUtils initial(Application context) {
        AppUtils.context = context;
        if (instance == null) {
            instance = new AppUtils(context);
        }
        return instance;
    }

    /**
     * 重置appUtils
     */
    public static void reset() {
        instance = null;
    }

    /**
     * 获取application对象
     *
     * @return
     */
    public Application getApplication() {
        return context;
    }

    /**
     * 获取当前应用版本
     *
     * @return
     */
    public int getCurrentVersion() {
        return packageInfo.versionCode;
    }

    /**
     * 是否满电提醒
     */
    public boolean isPowerFullRemind() {
        return sharedPreferences.getBoolean("isPowerFullRemind", true);
    }

    /**
     * 更新状态：是否满电提醒
     */
    public void updateIsPowerFullRemind(boolean isPowerFullRemind) {
        sharedPreferences.edit().putBoolean("isPowerFullRemind", isPowerFullRemind).commit();
    }

    /**
     * 是否防盗提醒
     */
    public boolean isTheftProofRemind() {
        return sharedPreferences.getBoolean("isTheftProofRemind", true);
    }

    /**
     * 更新状态：是否防盗提醒
     */
    public void updateIsTheftProofRemind(boolean isTheftProofRemind) {
        sharedPreferences.edit().putBoolean("isTheftProofRemind", isTheftProofRemind).commit();
    }

    /**
     * 是否夜间模式
     */
    public boolean isNightMode() {
        return sharedPreferences.getBoolean("isNightMode", true);
    }

    /**
     * 更新状态：是否夜间模式
     */
    public void updateIsNightMode(boolean isNightMode) {
        sharedPreferences.edit().putBoolean("isNightMode", isNightMode).commit();
    }
}
