package com.rat.snowkids.entity.model;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 操作环境
 */
public class Environment implements Serializable {
    private String current;
    private String[] list;

    public Environment(String[] list) {
        if (null != list && list.length > 0) {
            this.current = list[0];
            this.list = list;
        } else {
            this.current = "";
            this.list = new String[0];
        }
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String[] getList() {
        if (null == list)
            this.list = new String[0];
        return list;
    }

    public void setList(String[] list) {
        this.list = list;
    }
}
