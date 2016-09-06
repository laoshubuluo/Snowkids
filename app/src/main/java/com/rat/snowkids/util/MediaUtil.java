package com.rat.snowkids.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;

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
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor fileDescriptor = assetManager.openFd("1.mp3");

            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(
                    fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public void start() {
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }

    public void stop() {
        mediaPlayer.stop();
    }
}