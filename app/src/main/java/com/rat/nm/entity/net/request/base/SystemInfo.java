package com.rat.nm.entity.net.request.base;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class SystemInfo implements Serializable {

    private String idfa;
    private String device;
    private String osver;
    private String osType;
    private String language;

    public SystemInfo(String idfa, String device, String osver, String osType, String language) {
        this.idfa = idfa;
        this.device = device;
        this.osver = osver;
        this.osType = osType;
        this.language = language;
    }
}