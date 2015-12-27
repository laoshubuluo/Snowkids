package com.rat.nm.entity.net.request.base;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class RequestInfo implements Serializable {
    private LocationInfo locationInfo;
    private SystemInfo systemInfo;
    private AppInfo appInfo;
    private ActionInfo actionInfo;

    public RequestInfo(LocationInfo locationInfo, SystemInfo systemInfo, AppInfo appInfo, ActionInfo actionInfo) {
        this.locationInfo = locationInfo;
        this.systemInfo = systemInfo;
        this.appInfo = appInfo;
        this.actionInfo = actionInfo;
    }
}