package com.rat.snowkids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;

import com.rat.snowkids.common.Constant;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.entity.model.Power;
import com.rat.snowkids.util.LogUtil;
import com.rat.snowkids.util.PowerUtil;

public class PowerConnectionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Power power = PowerUtil.getPowerData(context);
        LogUtil.i("receive broadcast,action:" + intent.getAction() + "|" + power.toString());
        Bundle bundle = new Bundle();
        bundle.putSerializable("power", power);
        Message msg = new Message();
        msg.what = MessageSignConstant.POWER_CONNECTION_STATUS;
        msg.setData(bundle);
        Constant.handlerInMainActivity.sendMessage(msg);
        Constant.handlerInMonitorService.sendMessage(msg);
    }
}