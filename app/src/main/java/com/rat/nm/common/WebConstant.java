package com.rat.nm.common;

/**
 * author : L.jinzhu
 * date : 2015/8/1
 * introduce : 网络请求接口常量
 */
public class WebConstant {
    private WebConstant() {
    }

    //服务器URL
//    public static String BASE_URL = "https://app.amenba.com/amen";
    public static String BASE_URL = "https://verifyapp.amenba.com/amen";

    //分辨异端url
    public static String HERESY_URL = "http://app.amenba.com/html/heretical_doctrine.html";
    //未能连接到互联网url
    public static String NETWORK_CONNECT_URL = "http://app.amenba.com/html/heretical_doctrine.html";
    //注册协议url
    public static String REGISTER_PROTOCOL_URL = "http://app.amenba.com/html/register_notice.html";

    //微信开放平台url
    public static String WEIXIN_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    public static String WEIXIN_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";
}
