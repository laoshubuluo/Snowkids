package com.rat.nm.common;

import com.rat.nm.util.AppUtils;
import com.rat.nm.util.StringUtils;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 网络请求接口常量
 */
public class WebConstant {
    private WebConstant() {
    }

    // 密钥
    public static String WEB_KEY = "PCLtMrolKeSCJwVn";

    /**
     * 服务器URL
     */
    public static String getServerUrl() {
        return "http://" + getServerIp() + "/nms-rest/nms/";
    }

    private static String getServerIp() {
        String ip = "10.128.2.80:8080";
        if (StringUtils.isNullOrBlank(AppUtils.getInstance().getServerIp())) {
            return ip;
        } else {
            return AppUtils.getInstance().getServerIp();
        }
    }
}
