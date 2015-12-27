package com.rat.nm.entity.net.request.base;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class ActionInfo implements Serializable {

    int actionId;

    public ActionInfo(int actionId) {
        this.actionId = actionId;
    }
}