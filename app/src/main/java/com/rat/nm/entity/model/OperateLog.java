package com.rat.nm.entity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 操作日志实体
 */
public class OperateLog implements Serializable {
    @SerializedName("operId")
    private String id;// 日志id
    @SerializedName("operateLogType")
    private String type;// 操作类型:自动Auto、手动Manual
    @SerializedName("deviceId")
    private String deviceId;// 设备ID
    @SerializedName("deviceName")
    private String deviceName;// 设备名
    @SerializedName("userId")
    private String userId;// 用户ID
    @SerializedName("userName")
    private String userName;// 用户名称
    private String log;// 操作日志
    @SerializedName("operTime")
    private String time;// 时间

    public OperateLog(String type, String deviceName, String userName, String time) {
        this.type = type;
        this.deviceName = deviceName;
        this.userName = userName;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
