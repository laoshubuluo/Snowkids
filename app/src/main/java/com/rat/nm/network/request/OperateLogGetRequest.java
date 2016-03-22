package com.rat.nm.network.request;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.common.ResponseConstant;
import com.rat.nm.common.WebConstant;
import com.rat.nm.entity.net.request.OperateLogGetActionInfo;
import com.rat.nm.entity.net.request.base.RequestInfo;
import com.rat.nm.entity.net.response.OperateLogGetRspInfo;
import com.rat.nm.network.request.base.PostJsonRequest;
import com.rat.nm.util.GsonUtil;
import com.rat.nm.util.LogUtil;

import org.json.JSONObject;

/**
 * author : L.jinzhu
 * date : 2015/9/11
 * introduce : 操作日志详情请求request
 */
public class OperateLogGetRequest extends PostJsonRequest {
    private String operateLogId;

    public OperateLogGetRequest(Handler handler, Context context, String operateLogId) {
        this.handler = handler;
        this.context = context;
        this.operateLogId = operateLogId;
    }

    @Override
    protected String getParamsJson() {
        OperateLogGetActionInfo actionInfo = new OperateLogGetActionInfo(0, operateLogId);
        RequestInfo r = new RequestInfo(context, actionInfo);
        return GsonUtil.toJson(r);
    }

    @Override
    protected String getUrl() {
        return WebConstant.getServerUrl();
    }

    @Override
    protected String requestTag() {
        return "operateLogGet";
    }

    @Override
    protected void responseSuccess(JSONObject response) {
        Bundle b = new Bundle();
        Message msg = new Message();
        try {
            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
            OperateLogGetRspInfo info = GsonUtil.fromJson(response.toString(), OperateLogGetRspInfo.class);
            //响应正常
            if (ResponseConstant.SUCCESS == info.getCode()) {
                b.putSerializable("operateLog", info.getOperateLog());
                msg.what = MessageSignConstant.OPERATE_LOG_GET_SUCCESS;
                msg.setData(b);
                handler.sendMessage(msg);
                LogUtil.i(requestTag() + " success");
            }
            //响应失败
            else {
                b.putInt("code", info.getCode());
                b.putString("message", info.getMessage());
                msg.what = MessageSignConstant.OPERATE_LOG_GET_FAILURE;
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