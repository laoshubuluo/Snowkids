package com.rat.snowkids.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

import com.rat.snowkids.activity.MainActivity;
import com.rat.snowkids.common.Actions;
import com.rat.snowkids.common.Constant;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.entity.model.Power;
import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.DateUtil;
import com.rat.snowkids.util.LogUtil;
import com.rat.snowkids.util.MediaUtil;
import com.rat.snowkids.util.PowerUtil;
import com.snowkids.snowkids.R;

/**
 * author : L.jinzhu
 * date : 2016/9/11
 * introduce : 守护监控service
 */
public class MonitorService extends Service implements Handler.Callback {
    private Handler handler;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.i("onCreate:" + this.getClass().getSimpleName());
        handler = new Handler(this);
        Constant.handlerInMonitorService = handler;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Snowkids")
                .setContentText("Snowkids电量监控、防盗提醒进行中")
                .setSmallIcon(R.mipmap.notifycation_logo)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(12346, notification);

        initBroadcastReceiver();
        return Service.START_STICKY;
    }

    /**
     * 初始化广播
     */
    public void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(receiver, filter);
    }

    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        Power power = PowerUtil.getPowerData(getApplicationContext());
        switch (msg.what) {
            case MessageSignConstant.POWER_CONNECTION_STATUS:
                // 开始充电
                if (power.isCharging()) {
                    MediaUtil.getInstance(getApplicationContext()).pauseTP();
                }
                // 拔掉电源
                else {
                    // 报警
                    if (AppUtils.getInstance().isTheftProofRemind()) {
                        MediaUtil.getInstance(getApplicationContext()).start4TP();
                    }
                }
                break;
        }
        return false;
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.i("receive broadcast,action:" + intent.getAction());
            Power power = PowerUtil.getPowerData(getApplicationContext());
            if (intent.getAction().equals(Intent.ACTION_BATTERY_CHANGED)) {
                if (power.isPowerFull() && AppUtils.getInstance().isPowerFullRemind()) {
                    MediaUtil.getInstance(getApplicationContext()).start4PF();
                    Toast.makeText(context, getApplication().getString(R.string.power_full), Toast.LENGTH_LONG).show();
                }
                // 通知activity
                context.sendBroadcast(new Intent(Actions.POWER_CHANGED));
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);

        Intent intent = new Intent(MonitorService.this, MonitorService.class);
        startService(intent);
    }
}