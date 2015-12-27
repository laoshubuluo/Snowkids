package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

import com.rat.nm.view.MenuItemView;

public class MenuActivity extends BaseActivity {

    @ViewInject(R.id.top_name)
    private TextView titleView;
    @ViewInject(R.id.top_left)
    private TextView backView;

    @ViewInject(R.id.profileMIV)
    private MenuItemView profileMIV;
    @ViewInject(R.id.parameterMIV)
    private MenuItemView parameterMIV;
    @ViewInject(R.id.alarmInfoMIV)
    private MenuItemView alarmInfoMIV;
    @ViewInject(R.id.operationLogMIV)
    private MenuItemView operationLogMIV;
    @ViewInject(R.id.runningStateMIV)
    private MenuItemView runningStateMIV;
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
        titleView.setText(R.string.menu);
        backView.setBackgroundResource(R.mipmap.arrow_back);
        backView.setOnClickListener(this);

        profileMIV.initView(MenuItemView.PROFILE);
        profileMIV.initData(0);
        parameterMIV.initView(MenuItemView.PARAMETER);
        parameterMIV.initData(1);
        alarmInfoMIV.initView(MenuItemView.ALARM_INFO);
        //alarmInfoMIV.initData(3);
        operationLogMIV.initView(MenuItemView.OPERTION_LOG);
        //operationLogMIV.initData(3);
        runningStateMIV.initView(MenuItemView.RUNNING_STATE);
        runningStateMIV.initData(20);
        settingsMIV.initView(MenuItemView.SETTINGS);
        settingsMIV.initData(99);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
//        switch (msg.what) {
//            case MessageSignConstant.DEMO:
//                Demo demo = (Demo) msg.getData().getSerializable("demo");
////                tv.setText(demo.getName());
//        }
        return false;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
        }
    }
}