package com.rat.nm.entity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 设备类型实体
 */
public class DeviceType implements Serializable {
    private String id;
    @SerializedName("displayName")
    private String name4Show;// 显示名称
    @SerializedName("deviceName")
    private String nameInEN;// 英文名称
    private String nameInZH;// 中文名称
    private String type;// 设备类型
    private String describe;// 描述
    @SerializedName("picUrl")
    private String imageUrl;// 图片地址

    public DeviceType(String id, String name4Show, String type) {
        this.id = id;
        this.name4Show = name4Show;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName4Show() {
        return name4Show;
    }

    public void setName4Show(String name4Show) {
        this.name4Show = name4Show;
    }

    public String getNameInEN() {
        return nameInEN;
    }

    public void setNameInEN(String nameInEN) {
        this.nameInEN = nameInEN;
    }

    public String getNameInZH() {
        return nameInZH;
    }

    public void setNameInZH(String nameInZH) {
        this.nameInZH = nameInZH;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescribe() {
        return describe;
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
}
