package com.rat.snowkids.entity.net.request;

import com.google.gson.annotations.SerializedName;
import com.rat.snowkids.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class OperateLogListGetActionInfo extends ActionInfo {
    @SerializedName("userName")
    private String operateUser;
    private String operateType;
    @SerializedName("operStart")
    private String timeStart;
    @SerializedName("operEnd")
    private String timeEnd;

    private int totalPage;//总页数
    private int currentPage;//当前页
    private String pageType;//down下一页 up上一页

    public OperateLogListGetActionInfo(int actionId, int totalPage, int currentPage, String pageType, String operateUser, String operateType, String timeStart, String timeEnd) {
        super(actionId);
        this.operateUser = operateUser;
        this.operateType = operateType;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageType = pageType;
    }
}