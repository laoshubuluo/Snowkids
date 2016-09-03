package com.rat.snowkids.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.snowkids.activity.base.BaseActivity;
import com.rat.snowkids.entity.model.OperateLog;
import com.snowkids.snowkids.R;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.logTV)
    private TextView logTV;

    private OperateLog operateLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        operateLog = (OperateLog) getIntent().getSerializableExtra("operateLog");
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.operate_log);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        if (null == operateLog)
            return;
        logTV.setText(operateLog.getLog());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.top_left:
                finish();
            default:
                break;
        }
    }
}