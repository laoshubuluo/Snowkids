package com.rat.nm.controller;

import android.content.Context;
import android.os.Handler;

import com.rat.nm.network.VolleyManager;
import com.rat.nm.network.request.LoginRequest;


/**
 * author : L.jinzhu
 * date : 2015/8/24
 * introduce : 登录控制器
 */
public class LoginController {

    private Handler handler;
    private Context context;

    public LoginController(Context c, Handler h) {
        this.context = c;
        this.handler = h;
    }

    /**
     * 自有登录
     */
    public void login(String phoneNum, String password, int type) {
        LoginRequest loginRequest = new LoginRequest(handler, context, phoneNum, password, type);
        VolleyManager.getInstance(context).add2RequestQueue(loginRequest.getRequest());
    }

//
//    /**
//     * 自有（自动）登录
//     */
//    public void loginAuto() {
//        LoginAutoRequest request = new LoginAutoRequest(handler, context);
//        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
//    }
}
