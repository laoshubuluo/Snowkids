package com.rat.snowkids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Message;

import com.rat.snowkids.common.Actions;
import com.rat.snowkids.common.Constant;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.util.LogUtil;

public class PowerLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.i("receive broadcast,action:" + intent.getAction());
        if ("android.intent.action.ACTION_BATTERY_OKAY".equals(intent.getAction())) {
            // 通知service
            Message msg = new Message();
            msg.what = MessageSignConstant.POWER_LEVEL_OK;
            if (null != Constant.handlerInMonitorService)
                Constant.handlerInMonitorService.sendMessage(msg);
            // 通知activity
            context.sendBroadcast(new Intent(Actions.POWER_LEVEL_OK));
        }
    }
}