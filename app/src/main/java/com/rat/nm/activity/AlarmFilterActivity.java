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

    @ViewInject(R.id.alarmType)
    private TextView alarmType;
    @ViewInject(R.id.timeStart)
    private TextView timeStart;
    @ViewInject(R.id.timeEnd)
    private TextView timeEnd;

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
        alarmType.setOnClickListener(this);
        timeStart.setOnClickListener(this);
        timeEnd.setOnClickListener(this);
        queryBtn.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
            case R.id.timeStart:
                i = new Intent(AlarmFilterActivity.this, DateInputActivity.class);
                startActivityForResult(i, ActivityResultConstant.REQUEST_CODE_DATE_START);
                break;
            case R.id.timeEnd:
                i = new Intent(AlarmFilterActivity.this, DateInputActivity.class);
                startActivityForResult(i, ActivityResultConstant.REQUEST_CODE_DATE_END);
                break;
            case R.id.queryBtn:
                i = new Intent(AlarmFilterActivity.this, AlarmListActivity.class);
                startActivity(i);
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
        String date;
        if (null == data) return;
        switch (requestCode) {
            case ActivityResultConstant.REQUEST_CODE_DATE_START:
                date = data.getExtras().getString("date");
                timeStart.setText(date);
                break;
            case ActivityResultConstant.REQUEST_CODE_DATE_END:
                date = data.getExtras().getString("date");
                timeEnd.setText(date);
                break;
            default:
                break;
        }
    }
}