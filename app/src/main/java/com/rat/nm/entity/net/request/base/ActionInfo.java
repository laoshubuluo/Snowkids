package com.rat.nm.entity.net.request.base;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class ActionInfo extends AbstractRequestInfo {

    int actionId;

    public ActionInfo(int actionId) {
        this.actionId = actionId;
    }
}