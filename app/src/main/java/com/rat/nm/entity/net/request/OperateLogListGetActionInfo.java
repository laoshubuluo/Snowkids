package com.rat.nm.entity.net.request;

import com.rat.nm.entity.net.request.base.ActionInfo;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class OperateLogListGetActionInfo extends ActionInfo {
    private String operateLogType;
    private int totalPage;//总页数
    private int currentPage;//当前页
    private String pageType;//down下一页 up上一页

    public OperateLogListGetActionInfo(int actionId, String operateLogType, int totalPage, int currentPage, String pageType) {
        super(actionId);
        this.operateLogType = operateLogType;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageType = pageType;
    }
}