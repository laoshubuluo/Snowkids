package com.rat.snowkids.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.rat.snowkids.activity.MainActivity;
import com.rat.snowkids.common.Constant;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.entity.model.Power;
import com.rat.snowkids.util.AppUtils;
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
        // Toast.makeText(getApplicationContext(), this.getClass().getSimpleName() + " start success", Toast.LENGTH_SHORT).show();
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
        return Service.START_STICKY;
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
//                        // 启动自己
//                        Intent intent = new Intent(Moni(), MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getApplication().startActivity(intent);
                    }
                }
                break;
            case MessageSignConstant.POWER_LEVEL_OK:
                if (AppUtils.getInstance().isPowerFullRemind())
                    MediaUtil.getInstance(getApplicationContext()).start4PF();
                break;
        }
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(MonitorService.this, MonitorService.class);
        startService(intent);
    }
}