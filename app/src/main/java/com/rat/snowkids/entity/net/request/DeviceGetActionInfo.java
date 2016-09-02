package com.rat.snowkids.entity.net.request;

import com.rat.snowkids.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class DeviceGetActionInfo extends ActionInfo {
    private String deviceId;

    public DeviceGetActionInfo(int actionId, String deviceId) {
        super(actionId);
        this.deviceId = deviceId;
    }
}