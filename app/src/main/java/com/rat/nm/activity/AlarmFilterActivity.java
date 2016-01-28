package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.activity.base.DateInputActivity;
import com.rat.nm.common.ActivityResultConstant;

public class AlarmFilterActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.queryBtn)
    private Button queryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_filter);

        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.alarm_filter);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
            case R.id.queryBtn:
//                Intent i = new Intent(AlarmFilterActivity.this, AlarmListActivity.class);
//                startActivity(i);

                Intent i = new Intent(AlarmFilterActivity.this, DateInputActivity.class);
                startActivityForResult(i, 0);
                break;
            default:
                break;
        }
    }

    /**
     * 接收新activity返回数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case ActivityResultConstant.DATE_INPUT:
                String name = data.getExtras().getString("name");
                topTitleView.setText(name);
                break;
            default:
                break;
        }
    }

}