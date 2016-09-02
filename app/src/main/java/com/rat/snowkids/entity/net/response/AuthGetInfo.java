package com.rat.snowkids.entity.net.response;

import com.rat.snowkids.entity.net.response.base.ResponseInfo;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class AuthGetInfo extends ResponseInfo implements Serializable {

    private String shortCode;
    private int duration;//生效时长，单位毫秒
    private int userStatus;//用户被封

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }
}