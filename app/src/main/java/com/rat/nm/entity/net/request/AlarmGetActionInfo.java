package com.rat.nm.entity.net.request;

import com.rat.nm.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class AlarmGetActionInfo extends ActionInfo {
    private String alarmId;

    public AlarmGetActionInfo(int actionId, String alarmId) {
        super(actionId);
        this.alarmId = alarmId;
    }
}