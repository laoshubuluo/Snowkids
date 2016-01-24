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
//import com.rat.nm.entity.enums.DataGetType;
//import com.rat.nm.entity.net.request.DeviceGetActionInfo;
//import com.rat.nm.entity.net.request.base.RequestInfo;
//import com.rat.nm.entity.net.response.DeviceGetRspInfo;
//import com.rat.nm.network.request.base.PostJsonRequest;
//import com.rat.nm.util.GsonUtil;
//import com.rat.nm.util.LogUtil;
//
//import org.json.JSONObject;
//
///**
// * author : L.jinzhu
// * date : 2015/9/11
// * introduce : 设备列表请求request
// */
//public class DeviceListGetRequest extends PostJsonRequest {
//    private DataGetType dataGetType;// 数据获取类型
//    private int totalPage;
//    private int currentPage;
//    private String deviceId;
//
//    public DeviceListGetRequest(Handler handler, Context context, int totalPage, int currentPage, String deviceId, DataGetType dataGetType) {
//        this.handler = handler;
//        this.context = context;
//        this.totalPage = totalPage;
//        this.currentPage = currentPage;
//        this.dataGetType = dataGetType;
//        this.deviceId = deviceId;
//        if (dataGetType.equals(DataGetType.UPDATE)) {
//            this.totalPage = 0;
//            this.currentPage = 0;
//            this.dataGetType = DataGetType.PAGE_DOWN;
//        } else
//            this.dataGetType = dataGetType;
//    }
//
//    @Override
//    protected String getParamsJson() {
//        DeviceGetActionInfo actionInfo = new DeviceGetActionInfo(32, deviceId);
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
//        return "deviceGet";
//    }
//
//    @Override
//    protected void responseSuccess(JSONObject response) {
//        Bundle b = new Bundle();
//        Message msg = new Message();
//        try {
//            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
//            DeviceGetRspInfo info = GsonUtil.fromJson(response.toString(), DeviceGetRspInfo.class);
//            //响应正常
//            if (ResponseConstant.SUCCESS == info.getCode()) {
//                b.putInt("totalPage", info.getTotalPage());
//                b.putInt("currentPage", info.getCurrentPage());
//                b.putString("dataGetType", dataGetType == null ? "" : dataGetType.getType());
//                b.putSerializable("bibleDiscuss", info.getBibleInfo());
//                msg.what = MessageSignConstant.BIBLE_DISCUSS_DETAIL_GET_SUCCESS;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.i("bible discuss detail get success");
//            }
//            //响应失败
//            else {
//                b.putInt("code", info.getCode());
//                b.putString("message", info.getMessage());
//                msg.what = MessageSignConstant.BIBLE_DISCUSS_DETAIL_GET_FAILURE;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.e("bible discuss detail get failure: code: " + info.getCode() + ",message: " + info.getMessage(), null);
//            }
//        } catch (Throwable e) {
//            handler.sendEmptyMessage(MessageSignConstant.UNKNOWN_ERROR);
//            LogUtil.e("bible discuss detail get error", e);
//        }
//    }
//}
