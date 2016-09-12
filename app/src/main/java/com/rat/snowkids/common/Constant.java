package com.rat.snowkids.common;

import android.os.Handler;

/**
 * @author L.jinzhu
 * @Description: 定义一些常量
 * @date 2015-08-31 上午11:45:51
 */
public class Constant {
    private Constant() {
    }

    //系统偏好参数写入文件
    public static final String PREFS_NAME = "dp_prefs";

    public static Handler handlerInMainActivity;
    public static Handler handlerInMonitorService;

}
