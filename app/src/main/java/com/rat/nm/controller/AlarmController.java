package com.rat.nm.controller;

import android.content.Context;
import android.os.Handler;

import com.rat.nm.entity.enums.DataGetType;
import com.rat.nm.network.VolleyManager;
import com.rat.nm.network.request.AlarmGetRequest;
import com.rat.nm.network.request.AlarmListGetRequest;

/**
 * author : L.jinzhu
 * date : 2015/8/24
 * introduce : 告警控制器
 */
public class AlarmController {

    private Handler handler;
    private Context context;

    public AlarmController(Context c, Handler h) {
        this.context = c;
        this.handler = h;
    }

    /**
     * 告警获取
     */
    public void get(String alarmId) {
        AlarmGetRequest request = new AlarmGetRequest(handler, context, alarmId);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }

    /**
     * 告警获取
     */
    public void getList(int totalPage, int currentPage, DataGetType dataGetType, String alarmType) {
        AlarmListGetRequest request = new AlarmListGetRequest(handler, context, totalPage, currentPage, alarmType, dataGetType);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }
}
