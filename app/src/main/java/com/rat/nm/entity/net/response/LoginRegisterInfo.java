package com.rat.nm.entity.net.response;


import com.rat.nm.entity.net.response.base.ResponseInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class LoginRegisterInfo extends ResponseInfo {

    private String token;
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

//    public int getFirstReg() {
//        return firstReg;
//    }
//
//    public void setFirstReg(int firstReg) {
//        this.firstReg = firstReg;
//    }
//
//    public int getLocation() {
//        return location;
//    }
//
//    public void setLocation(int location) {
//        this.location = location;
//    }
//
//    public User getUserInfo() {
//        return userInfo;
//    }
//
//    public void setUserInfo(User userInfo) {
//        this.userInfo = userInfo;
//    }
//
//    public List<User> getFriendList() {
//        return friendList;
//    }
//
//    public void setFriendList(List<User> friendList) {
//        this.friendList = friendList;
//    }
}