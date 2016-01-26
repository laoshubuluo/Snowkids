package com.rat.nm.entity.net.response;

import com.rat.nm.entity.model.DeviceType;
import com.rat.nm.entity.net.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class DeviceTypeListGetRspInfo extends ResponseInfo {
    private List<DeviceType> deviceTypeList;

    public List<DeviceType> getDeviceTypeList() {
        return deviceTypeList;
    }

    public void setDeviceTypeList(List<DeviceType> deviceTypeList) {
        this.deviceTypeList = deviceTypeList;
    }
}