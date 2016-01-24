package com.rat.nm.entity.model;

import java.io.Serializable;
import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 设备实体
 */
public class Device implements Serializable {
    private String id;
    private String name4Show;// 显示名称
    private String nameInEN;// 英文名称
    private String nameInZH;// 中文名称
    private String type;// 设备类型
    private String model;// 设备型号
    private String describe;// 描述
    private String runningStatus;// 运行状态
    private List<Parameter> parameterList;// 参数列表

    public String getId() {
        return id;
    }

    public String getName4Show() {
        return name4Show;
    }

    public String getNameInEN() {
        return nameInEN;
    }

    public String getNameInZH() {
        return nameInZH;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public String getDescribe() {
        return describe;
    }

    public String getRunningStatus() {
        return runningStatus;
    }

    public List<Parameter> getParameterList() {
        return parameterList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName4Show(String name4Show) {
        this.name4Show = name4Show;
    }

    public void setNameInEN(String nameInEN) {
        this.nameInEN = nameInEN;
    }

    public void setNameInZH(String nameInZH) {
        this.nameInZH = nameInZH;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }
}
