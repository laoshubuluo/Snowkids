package com.rat.nm.entity.enums;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 告警类型
 */
public enum OperateLogType {
    INFO(1, "Auto"),// 自动
    ALARM(2, "Manual"),// 手动
    UNKNOW(0, "Unknow");

    private int code;
    private String message;

    OperateLogType(int code, String message) {
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