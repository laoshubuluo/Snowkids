package com.rat.snowkids.common;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : Message标记常量
 */
public class MessageSignConstant {
    private MessageSignConstant() {
    }

    public static final int SERVER_OR_NETWORK_ERROR = 500; // 服务器or网络错误
    public static final int UNKNOWN_ERROR = 9999; // 未知错误、内部错误

    public static final int LOGIN_SUCCESS = 1101;
    public static final int LOGIN_FAILURE = 1102;

    public static final int POWER_CONNECTION_STATUS = 1117;
    public static final int POWER_LEVEL_STATUS = 1118;
}
