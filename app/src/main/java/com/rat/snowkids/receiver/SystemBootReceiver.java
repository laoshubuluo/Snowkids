package com.rat.snowkids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class SystemBootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //接收广播：解除锁屏广播
//        if (intent.getAction().equals("android.intent.action.USER_PRESENT")) {
//            connRongClient(context);
//        }
//        //接收广播：网络状态变化广播
//        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
//            connRongClient(context);
//        }
//        //接收广播：系统启动广播
//        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
//            connRongClient(context);
//        }
    }

}