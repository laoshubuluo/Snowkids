package com.rat.nm.entity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 告警实体
 */
public class Alarm implements Serializable {
    @SerializedName("alarmId")
    private String id;// 告警id
    private String type;// 告警类型:信息Info、警告Alarm、故障Fault
    @SerializedName("deviceId")
    private String deviceId;// 设备ID
    @SerializedName("alarmStart")
    private String timeStart;// 告警开始时间
    @SerializedName("alarmEnd")
    private String timeEnd;// 告警结束时间
    @SerializedName("alarmLog")
    private String log;// 告警日志
    private String nmx;

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

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public String getNmx() {
        return nmx;
    }

    public void setNmx(String nmx) {
        this.nmx = nmx;
    }
}
