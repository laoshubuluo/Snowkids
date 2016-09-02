package com.rat.snowkids.entity.net.response;

import com.rat.snowkids.entity.model.Device;
import com.rat.snowkids.entity.net.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class DeviceListGetRspInfo extends ResponseInfo {
    private int totalPage;//总页数
    private int currentPage;//当前页
    private List<Device> deviceList;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}