package com.rat.snowkids.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.rat.snowkids.common.ResponseConstant;


/**
 * 用户管理类
 *
 * @author L.jinzhu
 * @date 2015-09-07 18:25:16
 */
public class UserUtils {
    private static UserUtils instance;
    private static Context context;

    /**
     * 获取一个单例
     *
     * @return
     */
    public static synchronized UserUtils getInstance(Context c) {
        if (instance == null) {
            context = c;
            instance = new UserUtils();
        }
        return instance;
    }

    /**
     * 构造方法
     */
    private UserUtils() {
    }


    /**
     * 检查token是否失效
     *
     * @return
     */
    public boolean isTokenError(int code, String message) {
//        if (ResponseConstant.TOKEN_ERROR == code) {
//            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
//            logout();
//            Intent intent = new Intent((Activity) context, LoginActivity.class);
//            context.startActivity(intent);
//            return true;
//        } else
            return false;
    }

    /**
     * 退出登录
     */
    public void logout() {
//        AppUtils.getInstance().setUserName("");
//        AppUtils.getInstance().setUserToken("");
//        AppUtils.getInstance().updateIsRemeberMe(false);
//        Intent i = new Intent(context, LoginActivity.class);
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(i);
    }
}
