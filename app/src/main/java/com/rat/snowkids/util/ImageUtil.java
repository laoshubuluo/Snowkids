package com.rat.snowkids.util;

import android.content.Context;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.snowkids.snowkids.R;

import java.io.File;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 图片工具
 */
public class ImageUtil {
    private static int corners = 15;

    private ImageUtil() {
    }

    // 初始化imageLoader
    public static void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(
                context, StorageUtil.getCacheDirectory(context));
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3) // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024))//建议内存设在5-10M,可以有比较好的表现
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        // 将保存的时候的URI名称用MD5 加密
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                        // .diskCacheFileCount(100) //缓存的文件数量
                .diskCache(new UnlimitedDiscCache(cacheDir))
                        // 自定义缓存路径
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout 5s readTimeout 30s
                .build();// 开始构建
        ImageLoader.getInstance().init(config);
    }
//
//    // 获取图片信息
//    public static DisplayImageOptions getImageOptions() {
//        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.picture_error)
//                .imageScaleType(ImageScaleType.EXACTLY).showImageOnFail(R.mipmap.picture_error).cacheInMemory(true).cacheOnDisk(true).build();
//        return options;
//    }
}