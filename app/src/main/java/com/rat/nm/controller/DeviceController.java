package com.rat.nm.controller;

import android.content.Context;
import android.os.Handler;

import com.rat.nm.entity.enums.DataGetType;
import com.rat.nm.network.VolleyManager;
import com.rat.nm.network.request.DeviceGetRequest;
import com.rat.nm.network.request.DeviceListGetRequest;
import com.rat.nm.network.request.DeviceTypeListGetRequest;

/**
 * author : L.jinzhu
 * date : 2015/8/24
 * introduce : 设备控制器
 */
public class DeviceController {

    private Handler handler;
    private Context context;

    public DeviceController(Context c, Handler h) {
        this.context = c;
        this.handler = h;
    }

    /**
     * 设备获取
     */
    public void get(String deviceId) {
        DeviceGetRequest request = new DeviceGetRequest(handler, context, deviceId);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }

    /**
     * 设备获取
     */
    public void getList(int totalPage, int currentPage, DataGetType dataGetType, String deviceType) {
        DeviceListGetRequest request = new DeviceListGetRequest(handler, context, totalPage, currentPage, deviceType, dataGetType);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }

    /**
     * 设备类型获取
     */
    public void getTypeList() {
        DeviceTypeListGetRequest request = new DeviceTypeListGetRequest(handler, context);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }
}
