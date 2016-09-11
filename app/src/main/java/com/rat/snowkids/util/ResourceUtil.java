package com.rat.snowkids.util;

import com.snowkids.snowkids.R;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 资源文件
 */
public class ResourceUtil {
    public static int getResource(int resourceId) {
        int rightResourceId;
        // 夜间资源
        if (AppUtils.getInstance().isNightMode() && DateUtil.isOnNight()) {
            switch (resourceId) {
                case R.mipmap.settings_turn_on:
                    rightResourceId = R.mipmap.settings_turn_on_night;
                    break;
                case R.mipmap.settings_turn_off:
                    rightResourceId = R.mipmap.settings_turn_off_night;
                    break;
                default:
                    rightResourceId = resourceId;
            }
        }
        // 白天资源
        else {
            rightResourceId = resourceId;
        }
        return rightResourceId;
    }
}
