package com.rat.nm.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

public class SettingsActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件




        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
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


    /**
     * 点击事件
     */
    @OnClick(R.id.settingsLL)
    public void runningDetailLLOnClick(View v) {
        Toast.makeText(getApplicationContext(), getString(R.string.function_unavailable), Toast.LENGTH_SHORT).show();
    }
}