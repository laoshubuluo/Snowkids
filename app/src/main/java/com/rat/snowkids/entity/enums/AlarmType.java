package com.rat.snowkids.entity.enums;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 告警类型
 */
public enum AlarmType {
    INFO(1, "Info"),// 信息
    ALARM(2, "Alarm"),// 警告
    FAULT(3, "Fault"),// 故障
    UNKNOW(0, "Unknow");

    private int code;
    private String message;

    AlarmType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}