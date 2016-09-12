package com.rat.snowkids.util;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import com.rat.snowkids.entity.model.Power;

/**
 * 电源工具
 *
 * @author L.jinzhu
 * @date 2015-09-01 17:59:32
 */
public class PowerUtil {
    public static Power getPowerData(Context context) {
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent intent = context.registerReceiver(null, ifilter);
        return getPower(intent);
    }

    private static Power getPower(Intent batteryIntent) {
        // 充电状态:usb还是交流电
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        // 充电方式
        int chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        // 电池状态
        int level = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);// 当前剩余电量
        int scale = batteryIntent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);// 电量最大值
        float batteryPct = level / (float) scale; // 电量百分比

        // 电池剩余时间，各种方式无法测算准确，各大厂商数据均为写死，so，写死~
        // 100% = 12小时 = 12*60 = 720分钟
        int totalTime = 720 + (int) (Math.random() * 60);
        int timeLeftInt = (int) (totalTime * batteryPct);
        String timeLeftStr = timeLeftInt / 60 + "小时" + timeLeftInt % 60 + "分";

        Power power = new Power();
        power.setIsCharging(isCharging);
        power.setIsPowerFull(status == BatteryManager.BATTERY_STATUS_FULL);
        power.setIsUsbPower(usbCharge);
        power.setIsACPower(acCharge);
        power.setLevel(level);
        power.setBatteryPct(batteryPct);
        power.setTimeLeft(timeLeftStr);
        return power;
    }
}
