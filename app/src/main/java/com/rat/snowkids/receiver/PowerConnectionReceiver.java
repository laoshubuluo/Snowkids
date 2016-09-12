package com.rat.snowkids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

import com.rat.snowkids.common.Actions;
import com.rat.snowkids.common.Constant;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.util.LogUtil;

public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.i("receive broadcast,action:" + intent.getAction());
        // 通知service
        Message msg = new Message();
        msg.what = MessageSignConstant.POWER_CONNECTION_STATUS;
        if (null != Constant.handlerInMonitorService)
            Constant.handlerInMonitorService.sendMessage(msg);
        // 通知activity
        context.sendBroadcast(new Intent(Actions.POWER_CONNECT_STATUS_CHANGE));
    }
}