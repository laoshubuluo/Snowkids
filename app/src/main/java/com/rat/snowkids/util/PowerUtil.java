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
        // 你可以读到充电状态,如果在充电，可以读到是usb还是交流电
        // 是否在充电
        int status = batteryIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL;
        // 充电方式
        int chargePlug = batteryIntent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

        Power power = new Power();
        power.setIsCharging(isCharging);
        power.setIsPowerFull(status == BatteryManager.BATTERY_STATUS_FULL);
        power.setIsUsbPower(usbCharge);
        power.setIsACPower(acCharge);
        return power;
    }
}
