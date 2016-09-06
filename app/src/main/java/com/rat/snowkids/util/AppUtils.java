package com.rat.snowkids.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

import com.rat.snowkids.appmanager.Application;
import com.rat.snowkids.common.Constant;
import com.rat.snowkids.entity.model.Environment;


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
     * 是否记住账号密码
     */
    public boolean isRemeberMe() {
        return sharedPreferences.getBoolean("isRemeberMe", false);
    }

    /**
     * 更新状态：是否记住账号密码
     */
    public void updateIsRemeberMe(boolean isRemeberMe) {
        sharedPreferences.edit().putBoolean("isRemeberMe", isRemeberMe).commit();
    }


    /**
     * 是否接收push消息
     */
    public boolean isReceivePushMessage() {
        return sharedPreferences.getBoolean("isReceivePushMessage", true);
    }

    /**
     * 更新状态：是否接收push消息
     */
    public void updateIsReceivePushMessage(boolean isReceivePushMessage) {
        sharedPreferences.edit().putBoolean("isReceivePushMessage", isReceivePushMessage).commit();
    }

    /**
     * 获取用户的ID
     *
     * @return
     */
    public String getUserName() {
        return sharedPreferences.getString("userName", "");
    }

    /**
     * 保存用户名
     *
     * @param userName
     */
    public void setUserName(String userName) {
        sharedPreferences.edit().putString("userName", userName).commit();
    }

    /**
     * 获取用户token
     *
     * @return
     */
    public String getUserToken() {
        return sharedPreferences.getString("userToken", "");
    }

    /**
     * 保存用户的token
     *
     * @param token
     */
    public void setUserToken(String token) {
        sharedPreferences.edit().putString("userToken", token).commit();
    }

    /**
     * 获取用户environment
     *
     * @return
     */
    public Environment getUserEnvironment() {
        String jsonStr = sharedPreferences.getString("userEnvironment" + getUserName(), "");
        Environment environment;
        if (StringUtils.isNotBlank(jsonStr))
            environment = GsonUtil.fromJson(jsonStr, Environment.class);
        else
            environment = new Environment(null);
        return environment;
    }

    /**
     * 保存用户的environment
     *
     * @param environment
     */
    public void setUserEnvironment(Environment environment) {
        if (null == environment || null == environment.getList())
            return;
        Environment eOld = getUserEnvironment();
        // 历史环境为空则更新新的
        if (StringUtils.isNullOrBlank(eOld.getCurrent())) {
            setUserEnvironment4Update(environment);
            return;
        }
        // 历史环境不在新列表中则更新新的
        for (String s : environment.getList()) {
            if (eOld.getCurrent().equals(s))
                return;
        }
        setUserEnvironment4Update(environment);
    }

    /**
     * 保存用户的environment
     *
     * @param environment
     */
    public void setUserEnvironment4Update(Environment environment) {
        String jsonStr = GsonUtil.toJson(environment);
        sharedPreferences.edit().putString("userEnvironment" + getUserName(), jsonStr).commit();
    }

    /**
     * 获取服务地址
     */
    public String getServerIp() {
        return sharedPreferences.getString("serverIp", "").replace(" ", "").replace("：", ":").trim();
    }

    /**
     * 保存服务地址
     */
    public void setServerIp(String serverIp) {
        sharedPreferences.edit().putString("serverIp", serverIp).commit();
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

}
