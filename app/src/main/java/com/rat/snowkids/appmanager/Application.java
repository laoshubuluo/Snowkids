package com.rat.snowkids.appmanager;

import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.ImageUtil;
import com.umeng.analytics.MobclickAgent;

/**
 * author : L.jinzhu
 * date : 2015/8/18
 * introduce : Application，通过在AndroidManifest.xml中声明，替代系统自身的android.app.Application
 */

public class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化AppUtils
        AppUtils.initial(this);

        // 增加CrashHandler，处理未捕获的异常
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        // 基础框架初始化
        ImageUtil.initImageLoader(this);// ImageLoader框架初始化图片处理工具

        // 初始化友盟
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);

        // 初始化友盟推送
    }
}