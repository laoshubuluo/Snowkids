package com.rat.nm.entity;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 设备实体
 */
public class Parameter implements Serializable {
    private int id;
    private String key;
    private String des;
    private String value;

    public Parameter(int id, String key, String des, String value) {
        this.id = id;
        this.key = key;
        this.des = des;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
