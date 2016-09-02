package com.rat.snowkids.entity.net.request;

import com.rat.snowkids.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class OperateLogGetActionInfo extends ActionInfo {
    private String operateLogId;

    public OperateLogGetActionInfo(int actionId, String operateLogId) {
        super(actionId);
        this.operateLogId = operateLogId;
    }
}