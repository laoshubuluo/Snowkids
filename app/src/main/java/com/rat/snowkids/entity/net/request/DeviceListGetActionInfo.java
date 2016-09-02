package com.rat.snowkids.entity.net.request;

import com.rat.snowkids.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class DeviceListGetActionInfo extends ActionInfo {
    private String deviceType;
    private String deviceName;
    private int totalPage;//总页数
    private int currentPage;//当前页
    private String pageType;//down下一页 up上一页

    public DeviceListGetActionInfo(int actionId, String deviceType, String deviceName, int totalPage, int currentPage, String pageType) {
        super(actionId);
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageType = pageType;
    }
}