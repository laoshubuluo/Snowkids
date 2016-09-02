package com.rat.snowkids.entity.net.response;


import com.google.gson.annotations.SerializedName;
import com.rat.snowkids.entity.net.response.base.ResponseInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class LoginRegisterInfo extends ResponseInfo {

    private String token;
    @SerializedName("ENVList")
    private String environmentStr;
    private String[] environmentList;// 环境列表
//    private int firstReg;//是否为首次注册0：登录（非首次注册） 1：首次注册（信息不完整）
//    private int location;//0:不需要更新地址位置，1:需要更新地址位置
//    private User userInfo;//用户信息
//    private List<User> friendList;//非首次注册的（登录的）会下发通讯录

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String[] getEnvironmentList() {
        environmentList = environmentStr.split(",");
        if (null == environmentList)
            environmentList = new String[0];
        return environmentList;
    }

    public void setEnvironmentList(String[] environmentList) {
        this.environmentList = environmentList;
    }
}