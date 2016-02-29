package com.rat.nm.entity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 设备实体
 */
public class Device implements Serializable {
    @SerializedName("deviceId")
    private String id;
    @SerializedName("displayName")
    private String name4Show;// 显示名称
    @SerializedName("deviceName")
    private String nameInEN;// 英文名称
    private String nameInZH;// 中文名称
    @SerializedName("deviceType")
    private String type;// 设备类型
    private String model;// 设备型号
    private String describe;// 描述
    @SerializedName("deviceStatus")
    private String runningStatus;// 运行状态
    @SerializedName("picUrl")
    private String imageUrl;// 图片地址
    private List<Parameter> parameterList;// 参数列表

    public Device(String id, String name4Show, String runningStatus) {
        this.id = id;
        this.name4Show = name4Show;
        this.runningStatus = runningStatus;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRunningStatus(String runningStatus) {
        this.runningStatus = runningStatus;
    }

    public void setParameterList(List<Parameter> parameterList) {
        this.parameterList = parameterList;
    }
}
