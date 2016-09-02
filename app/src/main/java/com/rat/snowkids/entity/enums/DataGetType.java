package com.rat.snowkids.entity.enums;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 数据获取类型
 */
public enum DataGetType {
    PAGE_DOWN("down"),
    UPDATE("update");

    private String type;

    DataGetType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}