//package com.rat.nm.network.request;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//
//import com.rat.nm.common.MessageSignConstant;
//import com.rat.nm.common.ResponseConstant;
//import com.rat.nm.common.WebConstant;
//import com.rat.nm.entity.net.request.AlarmGetActionInfo;
//import com.rat.nm.entity.net.request.base.RequestInfo;
//import com.rat.nm.entity.net.response.AlarmGetRspInfo;
//import com.rat.nm.network.request.base.PostJsonRequest;
//import com.rat.nm.util.GsonUtil;
//import com.rat.nm.util.LogUtil;
//
//import org.json.JSONObject;
//
///**
// * author : L.jinzhu
// * date : 2015/9/11
// * introduce : 告警详情请求request
// */
//public class AlarmGetRequest extends PostJsonRequest {
//    private String alarmId;
//
//    public AlarmGetRequest(Handler handler, Context context, String alarmId) {
//        this.handler = handler;
//        this.context = context;
//        this.alarmId = alarmId;
//    }
//
//    @Override
//    protected String getParamsJson() {
//        AlarmGetActionInfo actionInfo = new AlarmGetActionInfo(0, alarmId);
//        RequestInfo r = new RequestInfo(context, actionInfo);
//        return GsonUtil.toJson(r);
//    }
//
//    @Override
//    protected String getUrl() {
//        return WebConstant.getServerUrl() + "auth/login";
//    }
//
//    @Override
//    protected String requestTag() {
//        return "alarmGet";
//    }
//
//    @Override
//    protected void responseSuccess(JSONObject response) {
//        Bundle b = new Bundle();
//        Message msg = new Message();
//        try {
//            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
//            AlarmGetRspInfo info = GsonUtil.fromJson(response.toString(), AlarmGetRspInfo.class);
//            //响应正常
//            if (ResponseConstant.SUCCESS == info.getCode()) {
//                b.putSerializable("alarm", info.getAlarm());
//                msg.what = MessageSignConstant.ALARM_GET_SUCCESS;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.i(requestTag() + " success");
//            }
//            //响应失败
//            else {
//                b.putInt("code", info.getCode());
//                b.putString("message", info.getMessage());
//                msg.what = MessageSignConstant.ALARM_GET_FAILURE;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.e(requestTag() + " failure: code: " + info.getCode() + ",message: " + info.getMessage(), null);
//            }
//        } catch (Throwable e) {
//            handler.sendEmptyMessage(MessageSignConstant.UNKNOWN_ERROR);
//            LogUtil.e(requestTag() + " error", e);
//        }
//    }
//}