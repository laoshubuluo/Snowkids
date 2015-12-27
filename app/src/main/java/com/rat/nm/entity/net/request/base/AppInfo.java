package com.rat.nm.entity.net.request.base;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class AppInfo implements Serializable {

    private String language;
    private int version;
    private String bundleId;

    public AppInfo(String language, int version, String bundleId) {
        this.language = language;
        this.version = version;
        this.bundleId = bundleId;
    }
}