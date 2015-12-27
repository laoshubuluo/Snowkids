package com.rat.nm.activity;

import android.os.Bundle;
import android.os.Message;

import com.lidroid.xutils.ViewUtils;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

public class OperationLogDetailActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_log_detail);

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

}