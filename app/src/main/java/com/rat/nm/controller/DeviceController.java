package com.rat.nm.controller;

import android.content.Context;
import android.os.Handler;

import com.rat.nm.network.VolleyManager;
import com.rat.nm.network.request.DeviceGetRequest;

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
}
