package com.rat.snowkids.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 配置文件读取工具
 */
public class PropertiesUtil {

    private Context context;

    public PropertiesUtil(Context context) {
        this.context = context;
    }

    public String get(String fileName, String key) {
        InputStream in = null;
        try {
            Properties prop = new Properties();
            in = context.getAssets().open(fileName);
            prop.load(in);
            return prop.get(key).toString();
        } catch (IOException e) {
        } finally {
            try {
                if (null != in)
                    in.close();
            } catch (Throwable e) {
            }
        }
        return null;
    }
}
