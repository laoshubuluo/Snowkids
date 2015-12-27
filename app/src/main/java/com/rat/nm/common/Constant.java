package com.rat.nm.common;

import java.util.HashSet;

/**
 * @author shisheng.zhao
 * @Description: 定义一些常量
 * @date 2015-08-31 上午11:45:51
 */
public class Constant {
    private Constant() {
    }

    //微信开放平台
    public static final String WEIXIN_APP_ID = "wxb3509f0204b8d3cb";
    public static final String WEIXIN_APP_SECRET = "457fdf85cd950d433626fec52f9c610d";

    //QQ开放平台
    public static final String QQ_APP_ID = "1104784484";
    public static final String QQ_APP_KEY = "ASJIYRxo7CfkLPy9";

    //系统偏好参数写入文件
    public static final String PREFS_NAME = "dp_prefs";
    public static String tempHead = "";

    //数据库[通用]常量
    public final static int DB_COMMON_VERSION = 3;

    // 客服号
    public static long UID_SERVICE_START = 1000;
    public static long UID_SERVICE_END = 2000;
    public static String UID_SERVICE_ICON = "http://app.amenba.com/image/headimg/user_index_1/1000/1000.1448272532092.jpg";

    // 查经讨论提示（小红点）
    public static int BIBLE_DISCUSS_NOTICE;//1：代表显示红点 2: 代表显示数字
    public static String BIBLE_DISCUSS_COUNT = "";//字符类型，显示数字(1、2、99、n)
    public static long BIBLE_DISCUSS_UID;

    // 定位数据
    public static String cityName;
    public static double longitude;
    public static double latitude;
    public static String address;

    public static int[] deviceWidthHeight;

    public static int screenWidth;
    public static int screenHeight;

    public static String bibleKeyWord = "";

    public static HashSet<String> groupMap = new HashSet<String>();
}
