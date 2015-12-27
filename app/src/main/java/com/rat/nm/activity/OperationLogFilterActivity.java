package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

public class OperationLogFilterActivity extends BaseActivity {


    @ViewInject(R.id.queryBtn)
    private Button queryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_log_filter);

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
    @OnClick(R.id.queryBtn)
    public void queryBtnOnClick(View v) {
        Intent i = new Intent(OperationLogFilterActivity.this, OperationLogDetailActivity.class);
        startActivity(i);
    }
}