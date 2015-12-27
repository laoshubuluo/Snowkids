//package com.rat.nm.controller;
//
//import android.content.Context;
//import android.os.Handler;
//
//import com.rat.nm.network.VolleyManager;
//
//
///**
// * author : L.jinzhu
// * date : 2015/8/24
// * introduce : 应用控制器
// */
//public class AppController {
//
//    private Handler handler;
//    private Context context;
//
//    public AppController(Context c, Handler h) {
//        this.context = c;
//        this.handler = h;
//    }
//
//    /**
//     * 更新欢迎界面数据（从服务器更新）
//     */
//    public void updateWelcomeData() {
//        WelcomeRequest welcomeRequest = new WelcomeRequest(context);
//        VolleyManager.getInstance(context).add2RequestQueue(welcomeRequest.getRequest());
//    }
//
//    /**
//     * 获取欢迎界面数据（从本地获取）
//     */
//    public WelcomeData getWelcomeData() {
//        return new WelcomeDataDaoImpl(context).getNewAvailableData();
//    }
//
//    /**
//     * 应用信息上报请求
//     */
//    public void appInfoInform() {
//        AppInfoInformRequest request = new AppInfoInformRequest(context);
//        VolleyManager.getInstance(context).add2RequestQueue(request.getRequest());
//    }
//}
