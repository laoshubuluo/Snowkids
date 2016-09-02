package com.rat.snowkids.common;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : Respone标记常量
 */
public class ResponseConstant {
    private ResponseConstant() {
    }

    public static final int SUCCESS = 1;
    public static final int TOKEN_ERROR = 7;// 用户鉴权失败，需要重新登录
}
