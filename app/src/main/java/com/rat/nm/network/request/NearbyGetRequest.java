//package com.rat.nm.network.request;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//
//import com..common.MessageSignConstant;
//import com..common.ResponseConstant;
//import com..common.WebConstant;
//import com..dao.NearbyDao;
//import com..dao.UserDao;
//import com..dao.impl.NearbyDaoImpl;
//import com..dao.impl.UserDaoImpl;
//import com..entity.enums.DataGetType;
//import com..entity.group.Nearby;
//import com..entity.net.request.NearbyActionInfo;
//import com..entity.net.request.base.RequestInfo;
//import com..entity.net.response.NearbyGetInfo;
//import com..entity.user.User;
//import com..network.request.base.PostJsonRequest;
//import com..util.AppUtils;
//import com..util.GsonUtil;
//import com..util.LogUtil;
//
//import org.json.JSONObject;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * author : L.jinzhu
// * date : 2015/9/11
// * introduce : 好友搜索请求request
// */
//public class NearbyGetRequest extends PostJsonRequest {
//    private NearbyDao nearbyDao;
//    private UserDao userDao;
//    private DataGetType dataGetType;// 数据获取类型
//    private int location;
//    private int totalPage;//总页数
//    private int currentPage;//当前页
//    private String pageType;
//
//    public NearbyGetRequest(Handler handler, Context context, int totalPage, int currentPage, DataGetType dataGetType) {
//        this.handler = handler;
//        this.context = context;
//        this.totalPage = totalPage;
//        this.currentPage = currentPage;
//        this.dataGetType = dataGetType;
//        if (dataGetType.equals(DataGetType.UPDATE)) {
//            this.totalPage = 0;
//            this.currentPage = 0;
//            this.pageType = DataGetType.PAGE_DOWN.getType();
//        } else
//            this.pageType = dataGetType.getType();
//        //是否首次上报位置信息
//        if (AppUtils.getInstance().isFirstInformLocation())
//            this.location = 1;
//        else
//            this.location = 0;
//        this.nearbyDao = new NearbyDaoImpl();
//        this.userDao = new UserDaoImpl();
//    }
//
//    @Override
//    protected String getParamsJson() {
//        NearbyActionInfo actionInfo = new NearbyActionInfo(28, location, totalPage, currentPage, pageType);
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
//        return "nearbyGet";
//    }
//
//    @Override
//    protected void responseSuccess(JSONObject response) {
//        Bundle b = new Bundle();
//        Message msg = new Message();
//        try {
//            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
//            NearbyGetInfo info = GsonUtil.fromJson(response.toString(), NearbyGetInfo.class);
//            //响应正常
//            if (ResponseConstant.SUCCESS == info.getCode()) {
//                List<User> userList = info.getUserList() == null ? new ArrayList<User>() : info.getUserList();
//                // 只有刷新，才保存邻舍和用户信息
//                if (DataGetType.UPDATE.getType().equals(dataGetType.getType())) {
//                    nearbyDao.deleteAll();
//                    for (User u : userList) {
//                        Nearby n = new Nearby(u.getUid(), "");
//                        nearbyDao.add(n);
//                        // 本地用户数据不为空，则更新noteName字段
//                        User userInLocal = userDao.getUserByUid(String.valueOf(u.getUid()));
//                        if (userInLocal.isUserExist())
//                            u.setNoteName(userInLocal.getNoteName());
//                        else
//                            userDao.add(u, User.STATUS_NOT_FRIEND);
//                    }
//                }
//
//                b.putInt("totalPage", info.getTotalPage());
//                b.putInt("currentPage", info.getCurrentPage());
//                b.putString("dataGetType", dataGetType.getType());
//                b.putSerializable("userList", (Serializable) userList);
//                msg.what = MessageSignConstant.NEARBY_GET_SUCCESS;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.i("nearby get success");
//
//                //更新状态：非首次上报位置信息
//                AppUtils.getInstance().updateInformLocationStatus();
//            }
//            //响应失败
//            else {
//                b.putInt("code", info.getCode());
//                b.putString("message", info.getMessage());
//                msg.what = MessageSignConstant.NEARBY_GET_FAILURE;
//                msg.setData(b);
//                handler.sendMessage(msg);
//                LogUtil.e("nearby get failure: code: " + info.getCode() + ",message: " + info.getMessage(), null);
//            }
//        } catch (Throwable e) {
//            handler.sendEmptyMessage(MessageSignConstant.UNKNOWN_ERROR);
//            LogUtil.e("nearby get error", e);
//        }
//    }
//}