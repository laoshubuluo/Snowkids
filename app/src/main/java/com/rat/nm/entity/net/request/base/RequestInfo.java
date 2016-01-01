package com.rat.nm.entity.net.request.base;

import android.content.Context;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class RequestInfo extends AbstractRequestInfo {
    private LocationInfo locationInfo;
    private SystemInfo systemInfo;
    private AppInfo appInfo;
    private ActionInfo actionInfo;

    public RequestInfo(Context context, ActionInfo actionInfo) {
//        this.locationInfo = new LocationInfo(context);
//        this.systemInfo = new SystemInfo(context);
//        this.appInfo = new AppInfo(context);
        this.actionInfo = actionInfo;
    }
}