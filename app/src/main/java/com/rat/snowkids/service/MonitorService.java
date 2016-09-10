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
                .setContentTitle("")
                .setContentText("")
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
        switch (msg.what) {
            case MessageSignConstant.POWER_CONNECTION_STATUS:
                Power power = (Power) msg.getData().getSerializable("power");
                // 开始充电
                if (power.isCharging()) {
                    MediaUtil.getInstance(getApplicationContext()).pause();
                }
                // 拔掉电源
                else {
                    // 报警
                    if (AppUtils.getInstance().isTheftProofRemind()) {
                        MediaUtil.getInstance(getApplicationContext()).start();
//                        // 启动自己
//                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        getApplication().startActivity(intent);
                    }
                }
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