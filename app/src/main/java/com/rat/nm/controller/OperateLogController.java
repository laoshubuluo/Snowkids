package com.rat.nm.controller;

import android.content.Context;
import android.os.Handler;

import com.rat.nm.entity.enums.DataGetType;
import com.rat.nm.network.VolleyManager;
import com.rat.nm.network.request.OperateLogGetRequest;
import com.rat.nm.network.request.OperateLogListGetRequest;

/**
 * author : L.jinzhu
 * date : 2015/8/24
 * introduce : 操作日志控制器
 */
public class OperateLogController {

    private Handler handler;
    private Context context;

    public OperateLogController(Context c, Handler h) {
        this.context = c;
        this.handler = h;
    }

    /**
     * 操作日志获取
     */
    public void get(String operateLogId) {
        OperateLogGetRequest request = new OperateLogGetRequest(handler, context, operateLogId);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }

    /**
     * 操作日志获取
     */
    public void getList(int totalPage, int currentPage, DataGetType dataGetType, String operateUser, String operateType, String timeStart, String timeEnd) {
        OperateLogListGetRequest request = new OperateLogListGetRequest(handler, context, totalPage, currentPage, dataGetType, operateUser, operateType, timeStart, timeEnd);
        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
    }
}
