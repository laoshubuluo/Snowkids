//package com.rat.nm.network.request;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//
//import com.wmgj.amen.common.MessageSignConstant;
//import com.wmgj.amen.common.ResponseConstant;
//import com.wmgj.amen.common.WebConstant;
//import com.wmgj.amen.entity.net.request.PasswordUpdateActionInfo;
//import com.wmgj.amen.entity.net.request.base.RequestInfo;
//import com.wmgj.amen.entity.net.response.base.ResponseInfo;
//import com.wmgj.amen.network.request.base.PostJsonRequest;
//import com.wmgj.amen.util.GsonUtil;
//import com.wmgj.amen.util.LogUtil;
//
//import org.json.JSONObject;
//
///**
// * author : L.jinzhu
// * date : 2015/8/24
// * introduce : 密码更新请求request
// */
//public class PasswordUpdateRequest extends PostJsonRequest {
//    private String phoneNum;
//    private String password;
//    private String shortCode;
//
//    public PasswordUpdateRequest(Handler h, Context c, String phoneNum, String password, String shortCode) {
//        this.handler = h;
//        this.context = c;
//        this.phoneNum = phoneNum;
//        this.password = password;
//        this.shortCode = shortCode;
//    }
//
//    @Override
//    protected String getParamsJson() {
//        PasswordUpdateActionInfo actionInfo = new PasswordUpdateActionInfo(9, phoneNum, password, shortCode);
//        RequestInfo r = new RequestInfo(context, actionInfo);
//        return GsonUtil.toJson(r);
//    }
//
//    @Override
//    protected String getUrl() {
//        return WebConstant.BASE_URL;
//    }
//
//    @Override
//    protected String requestTag() {
//        return "passwordUpdate";
//    }
//
//    @Override
//    protected void responseSuccess(JSONObject response) {
//        Bundle b = new Bundle();
//        Message msg = new Message();
//        try {
//            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
//            ResponseInfo info = GsonUtil.fromJson(response.toString(), ResponseInfo.class);
//            //响应正常
//            if (ResponseConstant.SUCCESS == info.getCode()) {
//                msg.what = MessageSignConstant.PASSWORD_UPDATE_SUCCESS;
//                handler.sendMessage(msg);
//                LogUtil.i("password update success");
//            }
//            //响应失败
//            else {
//                b.putInt("code", info.getCode());
//                b.putString("message", info.getMessage());
//                msg.what = MessageSignConstant.PASSWORD_UPDATE_FAILURE;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.e("password update failure: code: " + info.getCode() + ",message: " + info.getMessage(), null);
//            }
//        } catch (Throwable e) {
//            handler.sendEmptyMessage(MessageSignConstant.UNKNOWN_ERROR);
//            LogUtil.e("password update error", e);
//        }
//    }
//}
