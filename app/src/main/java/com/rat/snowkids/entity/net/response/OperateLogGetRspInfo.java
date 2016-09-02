package com.rat.snowkids.entity.net.response;

import com.rat.snowkids.entity.model.OperateLog;
import com.rat.snowkids.entity.net.response.base.ResponseInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class OperateLogGetRspInfo extends ResponseInfo {
    private OperateLog operateLog;

    public OperateLog getOperateLog() {
        return operateLog;
    }

    public void setOperateLog(OperateLog operateLog) {
        this.operateLog = operateLog;
    }
}