package com.rat.nm.entity.net.request.base;

import java.io.Serializable;

/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class LocationInfo implements Serializable {
    private String cityName;
    private String countryCode;
    private double longitude;
    private double latitude;
    private String address;

    public LocationInfo(String cityName, String countryCode, double longitude, double latitude, String address) {
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }
}