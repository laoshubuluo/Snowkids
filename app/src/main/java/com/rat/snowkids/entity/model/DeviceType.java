package com.rat.snowkids.entity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 设备类型实体
 */
public class DeviceType implements Serializable {
    private String id;
    @SerializedName("typeName")
    private String name;
    private String describe;// 描述
    @SerializedName("typUrl")
    private String imageUrl;// 图片地址

    public DeviceType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
