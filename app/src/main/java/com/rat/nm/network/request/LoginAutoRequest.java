//package com.rat.nm.network.request;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Handler;
//import android.widget.Toast;
//
//import com.umeng.analytics.MobclickAgent;
//import com.wmgj.amen.R;
//import com.wmgj.amen.activity.user.LoginOpenPlatformActivity;
//import com.wmgj.amen.appmanager.AppManager;
//import com.wmgj.amen.appmanager.DbConnectionManager;
//import com.wmgj.amen.common.ResponseConstant;
//import com.wmgj.amen.common.WebConstant;
//import com.wmgj.amen.dao.BibleDao;
//import com.wmgj.amen.dao.UserDao;
//import com.wmgj.amen.dao.impl.UserDaoImpl;
//import com.wmgj.amen.entity.net.request.LoginActionInfo;
//import com.wmgj.amen.entity.net.request.base.RequestInfo;
//import com.wmgj.amen.entity.net.response.LoginRegisterInfo;
//import com.wmgj.amen.entity.user.User;
//import com.wmgj.amen.network.request.base.PostJsonRequest;
//import com.wmgj.amen.util.AppUtils;
//import com.wmgj.amen.util.GsonUtil;
//import com.wmgj.amen.util.LogUtil;
//
//import org.json.JSONObject;
//
//import java.util.List;
//
///**
// * author : L.jinzhu
// * date : 2015/8/24
// * introduce : 自动登录请求request
// */
//public class LoginAutoRequest extends PostJsonRequest {
//    private UserDao userDao;
//
//    private String token;
//    private BibleDao bibleDao;
//
//    public LoginAutoRequest(Handler h, Context c) {
//        this.handler = h;
//        this.context = c;
//        this.token = AppUtils.getInstance().getUserToken();
//        this.userDao = new UserDaoImpl();
//        bibleDao = new BibleDao(c);
//    }
//
//    @Override
//    protected String getParamsJson() {
//        LoginActionInfo actionInfo = new LoginActionInfo(46, token);
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
//        return "loginAuto";
//    }
//
//    @Override
//    protected void responseSuccess(JSONObject response) {
//        try {
//            LogUtil.i("response success json: [" + requestTag() + "]: " + response.toString());
//            LoginRegisterInfo info = GsonUtil.fromJson(response.toString(), LoginRegisterInfo.class);
//            //响应正常
//            if (ResponseConstant.SUCCESS == info.getCode()) {
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
//                // 集成友盟账号统计功能--自有登录
//                MobclickAgent.onProfileSignIn(String.valueOf(info.getUserInfo().getUid()));
//
//                LogUtil.i("login auto success");
//            }
//            //响应失败
//            else {
//                Toast.makeText(context, context.getString(R.string.login_auto_failure), Toast.LENGTH_LONG).show();
//                AppManager.getAppManager().appExitAndCleanup(context, true);
//                Intent i = new Intent(context, LoginOpenPlatformActivity.class);
//                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i);
//                LogUtil.e("login auto failure: code: " + info.getCode() + ",message: " + info.getMessage(), null);
//            }
//        } catch (Throwable e) {
//            LogUtil.e("login auto error", e);
//        }
//    }
//}
