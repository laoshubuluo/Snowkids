package com.rat.snowkids.entity.net.request.base;

import com.google.gson.annotations.SerializedName;
import com.rat.snowkids.util.AppUtils;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class ActionInfo extends AbstractRequestInfo {

    int actionId;
    @SerializedName("ENV")
    String environment;

    public ActionInfo(int actionId) {
        this.actionId = actionId;
        this.environment = AppUtils.getInstance().getUserEnvironment().getCurrent();
    }
}