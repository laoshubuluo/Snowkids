//package com.rat.nm.util;
//
//import android.content.Context;
//
//import com.rat.nm.common.Constant;
//
//
///**
// * author : L.jinzhu
// * date : 2015/10/27
// * introduce : 定位服务
// */
//public class LocationUtil implements OnGetGeoCoderResultListener {
//    // 定位相关
//    private LocationClient mLocClient;
//    private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用
//    private MyLocationListenner myListener = new MyLocationListenner();
//    private double latitude;// 纬度
//    private double longitude;// 经度
//
//    private int tryTime = 5;//尝试次数，成功或到达尝试次数，均退出定位
//
//    public void start(Context context) {
//        LogUtil.i("location get start");
//        // 定位初始化
//        mLocClient = new LocationClient(context);
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);// 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000); // 设置定时定位的时间间隔
//        mLocClient.setLocOption(option);
//        mLocClient.start();
//        // 初始化搜索模块，注册事件监听
//        mSearch = GeoCoder.newInstance();
//        mSearch.setOnGetGeoCodeResultListener(this);
//    }
//
//    @Override
//    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
//    }
//
//    @Override
//    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
//        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
//            LogUtil.e("location get error: reverse geo error: " + result.error, null);
//            return;
//        }
//        // 赋值全局变量，供其他模块调用
//        Constant.cityName = result.getAddressDetail().city;
//        Constant.address = result.getAddress();
//        if (StringUtils.isNotBlank(Constant.cityName) || StringUtils.isNotBlank(Constant.address))
//            mSearch.destroy();
//        LogUtil.i("location get success: cityName: " + result.getAddressDetail().city + " address: " + result.getAddress());
//    }
//
//    /**
//     * 定位SDK监听函数
//     */
//    public class MyLocationListenner implements BDLocationListener {
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            if (tryTime == 0) {
//                stop();
//                return;
//            }
//            if (location == null) {
//                LogUtil.i("location get error: tryTime left：" + tryTime--);
//                return;
//            }
//            // 原生经纬度
//            latitude = location.getLatitude();
//            longitude = location.getLongitude();
//            if (longitude != 0 || latitude != 0) {
//                // 赋值全局变量，供其他模块调用
//                Constant.longitude = longitude;
//                Constant.latitude = latitude;
//                // 偏移经纬度
//                LatLng ll = new LatLng(latitude, longitude);
//                // 反Geo搜索
//                mSearch.reverseGeoCode(new ReverseGeoCodeOption().location(ll));
//                LogUtil.i("location get success: longitude: " + longitude + " latitude: " + latitude);
//                stop();
//            } else {
//                LogUtil.i("location get error: tryTime left：" + tryTime--);
//            }
//        }
//    }
//
//    private void stop() {
//        // 定位关闭
//        if (null != mLocClient)
//            mLocClient.stop();
//        myListener = null;
//        LogUtil.i("location get stop");
//    }
//}