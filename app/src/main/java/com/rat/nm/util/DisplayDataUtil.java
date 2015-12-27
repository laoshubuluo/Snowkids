package com.rat.nm.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 显示数据工具
 */
public class DisplayDataUtil {
    private DisplayDataUtil() {
    }

    /*
     * 获取分辨率
     */
    public static int getResolution(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        // int screenWidthDip = dm.widthPixels;
        int screenHeightDip = dm.heightPixels;
        int resolution = screenHeightDip;
        if (resolution <= 720) {
            resolution = 720;
        } else {
            resolution = 1080;
        }
        return resolution;
    }
}