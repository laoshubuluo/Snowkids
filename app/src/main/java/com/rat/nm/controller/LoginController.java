//package com.rat.nm.controller;
//
//import android.content.Context;
//import android.os.Handler;
//
//import com.wmgj.amen.network.VolleyManager;
//import com.wmgj.amen.network.request.LoginAutoRequest;
//import com.wmgj.amen.network.request.LoginOpenPlatformRequest;
//import com.wmgj.amen.network.request.LoginRequest;
//import com.wmgj.amen.network.request.TokenFromWeiXinRequest;
//import com.wmgj.amen.network.request.UserInfoFromWeiXinRequest;
//
///**
// * author : L.jinzhu
// * date : 2015/8/24
// * introduce : 登录控制器
// */
//public class LoginController {
//
//    private Handler handler;
//    private Context context;
//
//    public LoginController(Context c, Handler h) {
//        this.context = c;
//        this.handler = h;
//    }
//
//    /**
//     * 自有登录
//     */
//    public void login(String phoneNum, String password) {
//        LoginRequest loginRequest = new LoginRequest(handler, context, phoneNum, password);
//        VolleyManager.getInstance(context).add2RequestQueue(loginRequest.getRequest());
//    }
//
//
//    /**
//     * 自有（自动）登录
//     */
//    public void loginAuto() {
//        LoginAutoRequest request = new LoginAutoRequest(handler, context);
//        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
//    }
//
//    /**
//     * 开放平台登录
//     */
//    public void loginOpenPlatform(String type, String openId, Object info) {
//        LoginOpenPlatformRequest loginRequest = new LoginOpenPlatformRequest(handler, context, type, openId, info);
//        VolleyManager.getInstance(context).add2RequestQueue(loginRequest.getRequest());
//    }
//
//
//    /**
//     * 微信开放平台登录步骤二：通过code获取token
//     */
//    public void getTokenByCodeFromWeiXin(String code) {
//        TokenFromWeiXinRequest request = new TokenFromWeiXinRequest(handler, context, code);
//        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
//    }
//
//    /**
//     * 微信开放平台登录步骤三：通过token获取用户信息
//     */
//    public void getUserInfoByTokenFromWeiXin(String accessToken, String openId) {
//        UserInfoFromWeiXinRequest request = new UserInfoFromWeiXinRequest(handler, context, accessToken, openId);
//        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
//    }
//}
