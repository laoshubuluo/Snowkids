package com.rat.nm.network.request;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.common.ResponseConstant;
import com.rat.nm.common.WebConstant;
import com.rat.nm.entity.net.request.LoginActionInfo;
import com.rat.nm.entity.net.request.base.RequestInfo;
import com.rat.nm.entity.net.response.LoginRegisterInfo;
import com.rat.nm.network.request.base.PostJsonRequest;
import com.rat.nm.util.GsonUtil;
import com.rat.nm.util.LogUtil;

import org.json.JSONObject;

/**
 * author : L.jinzhu
 * date : 2015/8/24
 * introduce : 登录请求request
 */
public class LoginRequest extends PostJsonRequest {
    //    private UserDao userDao;
    private String phoneNum;
    private String password;

    public LoginRequest(Handler h, Context c, String phoneNum, String password) {
        this.handler = h;
        this.context = c;
        this.phoneNum = phoneNum;
        this.password = password;
//        this.userDao = new UserDaoImpl();
    }

    @Override
    protected String getParamsJson() {
        LoginActionInfo actionInfo = new LoginActionInfo(5, phoneNum, password);
        RequestInfo r = new RequestInfo(context, actionInfo);
        return GsonUtil.toJson(r);
    }

    @Override
    protected String getUrl() {
        return WebConstant.BASE_URL;
    }

    @Override
    protected String requestTag() {
        return "login";
    }

    @Override
    protected void responseSuccess(JSONObject response) {
        Bundle b = new Bundle();
        Message msg = new Message();
        try {
            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
            LoginRegisterInfo info = GsonUtil.fromJson(response.toString(), LoginRegisterInfo.class);
            //响应正常
            if (ResponseConstant.SUCCESS == info.getCode()) {
//                // 数据库指向用户自己的数据库
//                AppUtils.getInstance().setUserId(String.valueOf(info.getUserInfo().getUid()));
//                AppUtils.getInstance().setUserToken(info.getToken());
//                DbConnectionManager.getInstance().reload();
//                // 保存好友列表
//                List<User> friendList = info.getFriendList();
//                if (null != friendList && 0 != friendList.size()) {
//                    for (User friend : friendList) {
//                        friend.setTimestamp(info.getTimestamp());
//                        userDao.deleteUser(friend.getUid());//单个删除已存在数据
//                    }
//                    userDao.add(friendList);//批量保存
//                    //保存好友后删除旧时间戳的好友数据，保持与服务器同步
//                    userDao.deleteOldFriendData(info.getTimestamp());
//                }
//                // 保存陌生人列表
//                List<User> strangerList = info.getStrangerList();
//                if (null != strangerList && 0 != strangerList.size())
//                    userDao.add(strangerList);//批量保存
//                // 保存自己
//                userDao.add(info.getUserInfo(), User.STATUS_MY);
//                // 更新登录状态
//                AppUtils.getInstance().updateLoginStatus(info.getFirstReg());

                // 通知UI
//                b.putSerializable("user", info.getUserInfo());
                msg.what = MessageSignConstant.LOGIN_SUCCESS;
                msg.setData(b);
                handler.sendMessage(msg);

                LogUtil.i(requestTag() + " success");
            }
            //响应失败
            else {
                b.putInt("code", info.getCode());
                b.putString("message", info.getMessage());
                msg.what = MessageSignConstant.LOGIN_FAILURE;
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