package com.rat.snowkids.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.rat.snowkids.entity.model.Power;
import com.rat.snowkids.util.LogUtil;
import com.rat.snowkids.util.PowerUtil;

public class PowerLevelReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtil.i("receive broadcast,action:" + intent.getAction());
        Power power = PowerUtil.getPowerData(context);
        Toast.makeText(context, power.toString(), Toast.LENGTH_LONG).show();
    }
}