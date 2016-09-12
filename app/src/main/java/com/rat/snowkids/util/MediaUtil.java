package com.rat.snowkids.util;

import android.content.Context;
import android.media.MediaPlayer;

import com.snowkids.snowkids.R;


/**
 * 播放资源
 *
 * @author L.jinzhu
 * @date 2015-09-07 18:25:16
 */
public class MediaUtil {
    private static MediaPlayer mediaPlayer4TP;// 防盗告警
    private static MediaPlayer mediaPlayer4PF;// 电满告警
    private static MediaUtil instance;

    /**
     * 获取一个单例
     *
     * @return
     */
    public static synchronized MediaUtil getInstance(Context context) {
        if (instance == null) {
            instance = new MediaUtil(context);
        }
        return instance;
    }

    /**
     * 构造方法
     *
     * @param context
     */
    private MediaUtil(Context context) {
        try {
            mediaPlayer4TP = MediaPlayer.create(context, R.raw.guard);
            if (mediaPlayer4TP != null) {
                mediaPlayer4TP.stop();
            }
            mediaPlayer4TP.prepare();

            mediaPlayer4PF = MediaPlayer.create(context, R.raw.batteryfull);
            if (mediaPlayer4PF != null) {
                mediaPlayer4PF.stop();
            }
            mediaPlayer4PF.prepare();
        } catch (Throwable e) {
            LogUtil.e("media prepare error:", e);
            e.printStackTrace();
        }
    }

    public void start4PF() {
        if (null != mediaPlayer4PF && !mediaPlayer4PF.isPlaying())
            mediaPlayer4PF.start();
    }

    public void pausePF() {
        if (null != mediaPlayer4PF && mediaPlayer4PF.isPlaying())
            mediaPlayer4PF.pause();
    }

    public void start4TP() {
        if (null != mediaPlayer4TP && !mediaPlayer4TP.isPlaying())
            mediaPlayer4TP.start();
    }

    public void pauseTP() {
        if (null != mediaPlayer4TP && mediaPlayer4TP.isPlaying())
            mediaPlayer4TP.pause();
    }

    public void stop() {
        if (null != mediaPlayer4TP)
            mediaPlayer4TP.stop();
        mediaPlayer4TP.release();
        mediaPlayer4TP = null;

        if (null != mediaPlayer4PF)
            mediaPlayer4PF.stop();
        mediaPlayer4PF.release();
        mediaPlayer4PF = null;
    }
}