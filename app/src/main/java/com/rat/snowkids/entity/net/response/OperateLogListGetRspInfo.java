package com.rat.snowkids.entity.net.response;

import com.google.gson.annotations.SerializedName;
import com.rat.snowkids.entity.model.OperateLog;
import com.rat.snowkids.entity.net.response.base.ResponseInfo;

import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 响应实体
 */
public class OperateLogListGetRspInfo extends ResponseInfo {
    private int totalPage;//总页数
    private int currentPage;//当前页
    @SerializedName("operateList")
    private List<OperateLog> operateLogList;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<OperateLog> getOperateLogList() {
        return operateLogList;
    }

    public void setOperateLogList(List<OperateLog> operateLogList) {
        this.operateLogList = operateLogList;
    }
}