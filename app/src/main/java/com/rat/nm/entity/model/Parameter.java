package com.rat.nm.entity.model;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 参数实体
 */
public class Parameter implements Serializable {
    private String id;
    private String key;
    private String value;
    private String describe;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
