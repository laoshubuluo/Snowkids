package com.rat.snowkids.entity.net.request.base;

import android.content.Context;


/**
 * author : L.jinzhu
 * date : 2015/8/12
 * introduce : 请求实体
 */
public class LocationInfo extends AbstractRequestInfo {
    private String cityName;
    private String countryCode;
    private double longitude;
    private double latitude;
    private String address;

    public LocationInfo(Context context) {
//        this.longitude = Constant.longitude;
//        this.latitude = Constant.latitude;
//        this.cityName = Constant.cityName;
//        this.address = Constant.address;
        this.countryCode = "+86";
    }
}