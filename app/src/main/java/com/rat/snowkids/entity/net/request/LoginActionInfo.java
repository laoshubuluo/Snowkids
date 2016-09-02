package com.rat.snowkids.entity.net.request;


import com.rat.snowkids.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class LoginActionInfo extends ActionInfo {

    private String userId;// 用户名(或手机号等暂不支持)
    private String password;
    private int type;// 类型，是否需要用户信息:0：不需要，1：需要

    public LoginActionInfo(int actionId, String userId, String password, int type) {
        super(actionId);
        this.userId = userId;
        this.password = password;
        this.type = type;
    }
}