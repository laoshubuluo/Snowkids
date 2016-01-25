package com.rat.nm.entity.net.request;

import com.rat.nm.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class AlarmListGetActionInfo extends ActionInfo {
    private String alarmType;
    private int totalPage;//总页数
    private int currentPage;//当前页
    private String pageType;//down下一页 up上一页

    public AlarmListGetActionInfo(int actionId, String alarmType, int totalPage, int currentPage, String pageType) {
        super(actionId);
        this.alarmType = alarmType;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageType = pageType;
    }
}