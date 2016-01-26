package com.rat.nm.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.view.MenuItemView;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;

    @ViewInject(R.id.profileMIV)
    private MenuItemView profileMIV;
    @ViewInject(R.id.deviceTypeMIV)
    private MenuItemView deviceTypeMIV;
    @ViewInject(R.id.deviceMIV)
    private MenuItemView deviceMIV;
    @ViewInject(R.id.alarmMIV)
    private MenuItemView alarmMIV;
    @ViewInject(R.id.operateLogMIV)
    private MenuItemView operateLogMIV;
    @ViewInject(R.id.settingsMIV)
    private MenuItemView settingsMIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.menu);

        profileMIV.initView(MenuItemView.PROFILE);
        profileMIV.initData(0);
        deviceTypeMIV.initView(MenuItemView.DEVICE_TYPE);
        //deviceTypeMIV.initData(1);
        deviceMIV.initView(MenuItemView.DEVICE);
        deviceMIV.initData(3);
        alarmMIV.initView(MenuItemView.ALARM);
        alarmMIV.initData(3);
        operateLogMIV.initView(MenuItemView.OPERATE_LOG);
        operateLogMIV.initData(20);
        settingsMIV.initView(MenuItemView.SETTINGS);
        //settingsMIV.initData(99);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

}