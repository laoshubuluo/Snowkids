package com.rat.nm.entity.net.request;


import com.rat.nm.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class LoginActionInfo extends ActionInfo {

    private String phoneNum;
    private String password;


    public LoginActionInfo(int actionId, String phoneNum, String password) {
        super(actionId);
        this.phoneNum = phoneNum;
        this.password = password;
    }
}