package com.rat.snowkids.util;

import android.content.Context;
import android.media.MediaPlayer;

import com.snowkids.snowkids.R;

import java.io.IOException;


/**
 * 播放资源
 *
 * @author L.jinzhu
 * @date 2015-09-07 18:25:16
 */
public class MediaUtil {
    private static MediaPlayer mediaPlayer;
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
            mediaPlayer = MediaPlayer.create(context, R.raw.music1);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (null != mediaPlayer && !mediaPlayer.isPlaying())
            mediaPlayer.start();
    }

    public void pause() {
        if (null != mediaPlayer && mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    public void stop() {
        if (null != mediaPlayer)
            mediaPlayer.stop();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}