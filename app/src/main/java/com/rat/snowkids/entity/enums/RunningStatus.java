package com.rat.snowkids.entity.enums;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 运行状态
 */
public enum RunningStatus {
    ONLINE(1, "online"),// 在线
    OFFLINE(2, "offline"),// 不在线
    UNKNOW(0, "unknow");

    private int code;
    private String message;

    RunningStatus(int code, String message) {
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