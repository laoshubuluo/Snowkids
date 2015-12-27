package com.rat.nm.util;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

public class ImageLoaderUtils {
    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true).cacheOnDisk(true).build();

    public static String getAcceptableUri(String file) {
        if (StringUtils.isNullOrBlank(file)) {
            return "";
        }
        if (file.startsWith("http")) {
            return file;
        }
        return "file://" + file;
    }

    public static String getAcceptableUri(int resourceId) {
        return "mipmap://" + resourceId;
    }

    public static String getSmallPic(String pic) {
        return "file://" + pic;
    }

    public static String getSmallPic2(String pic) {
        return pic;
    }

    public static DisplayImageOptions getDefaultDisplayOptions() {
        return options;
    }
}
