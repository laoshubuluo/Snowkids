package com.rat.snowkids.network.request;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.common.ResponseConstant;
import com.rat.snowkids.common.WebConstant;
import com.rat.snowkids.entity.net.request.DeviceGetActionInfo;
import com.rat.snowkids.entity.net.request.base.RequestInfo;
import com.rat.snowkids.entity.net.response.DeviceGetRspInfo;
import com.rat.snowkids.network.request.base.PostJsonRequest;
import com.rat.snowkids.util.GsonUtil;
import com.rat.snowkids.util.LogUtil;

import org.json.JSONObject;

/**
 * author : L.jinzhu
 * date : 2015/9/11
 * introduce : 设备详情请求request
 */
public class DeviceGetRequest extends PostJsonRequest {
    private String deviceId;

    public DeviceGetRequest(Handler handler, Context context, String deviceId) {
        this.handler = handler;
        this.context = context;
        this.deviceId = deviceId;
    }

    @Override
    protected String getParamsJson() {
        DeviceGetActionInfo actionInfo = new DeviceGetActionInfo(0, deviceId);
        RequestInfo r = new RequestInfo(context, actionInfo);
        return GsonUtil.toJson(r);
    }

    @Override
    protected String getUrl() {
        return WebConstant.getServerUrl() + "device/info";
    }

    @Override
    protected String requestTag() {
        return "deviceGet";
    }

    @Override
    protected void responseSuccess(JSONObject response) {
        Bundle b = new Bundle();
        Message msg = new Message();
        try {
            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
            DeviceGetRspInfo info = GsonUtil.fromJson(response.toString(), DeviceGetRspInfo.class);
            //响应正常
            if (ResponseConstant.SUCCESS == info.getCode()) {
                b.putSerializable("device", info.getDevice());
                msg.what = MessageSignConstant.DEVICE_GET_SUCCESS;
                msg.setData(b);
                handler.sendMessage(msg);
                LogUtil.i(requestTag() + " success");
            }
            //响应失败
            else {
                b.putInt("code", info.getCode());
                b.putString("message", info.getMessage());
                msg.what = MessageSignConstant.DEVICE_GET_FAILURE;
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