package com.rat.nm.network.request;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.common.ResponseConstant;
import com.rat.nm.common.WebConstant;
import com.rat.nm.entity.net.request.base.ActionInfo;
import com.rat.nm.entity.net.request.base.RequestInfo;
import com.rat.nm.entity.net.response.DeviceTypeListGetRspInfo;
import com.rat.nm.network.request.base.PostJsonRequest;
import com.rat.nm.util.GsonUtil;
import com.rat.nm.util.LogUtil;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/9/11
 * introduce : 设备类型列表请求request
 */
public class DeviceTypeListGetRequest extends PostJsonRequest {

    public DeviceTypeListGetRequest(Handler handler, Context context) {
        this.handler = handler;
        this.context = context;
    }

    @Override
    protected String getParamsJson() {
        ActionInfo actionInfo = new ActionInfo(0);
        RequestInfo r = new RequestInfo(context, actionInfo);
        return GsonUtil.toJson(r);
    }

    @Override
    protected String getUrl() {
        return WebConstant.BASE_URL + "devicetype/list";
    }

    @Override
    protected String requestTag() {
        return "deviceTypeListGet";
    }

    @Override
    protected void responseSuccess(JSONObject response) {
        Bundle b = new Bundle();
        Message msg = new Message();
        try {
            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
            DeviceTypeListGetRspInfo info = GsonUtil.fromJson(response.toString(), DeviceTypeListGetRspInfo.class);
            //响应正常
            if (ResponseConstant.SUCCESS == info.getCode()) {
                b.putSerializable("deviceTypeList", (Serializable) info.getDeviceTypeList());
                msg.what = MessageSignConstant.DEVICE_TYPE_LIST_GET_SUCCESS;
                msg.setData(b);
                handler.sendMessage(msg);
                LogUtil.i(requestTag() + " success");
            }
            //响应失败
            else {
                b.putInt("code", info.getCode());
                b.putString("message", info.getMessage());
                msg.what = MessageSignConstant.DEVICE_TYPE_LIST_GET_FAILURE;
                msg.setData(b);
                handler.sendMessage(msg);
                LogUtil.e(requestTag() + " failure: code: " + info.getCode() + ",message: " + info.getMessage(), null);
            }
        } catch (Throwable e) {
            handler.sendEmptyMessage(MessageSignConstant.UNKNOWN_ERROR);
            LogUtil.e(requestTag() + " error", e);
        }
    }
}