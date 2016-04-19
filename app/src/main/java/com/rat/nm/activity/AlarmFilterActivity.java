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
import com.rat.nm.entity.enums.AlarmType;
import com.rat.nm.util.LogUtil;
import com.rat.nm.view.WheelView;

import java.util.Arrays;

public class AlarmFilterActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.alarmType)
    private WheelView alarmType;
    @ViewInject(R.id.deviceType)
    private WheelView deviceType;
    @ViewInject(R.id.deviceName)
    private WheelView deviceName;
    @ViewInject(R.id.timeStart)
    private TextView timeStart;
    @ViewInject(R.id.timeEnd)
    private TextView timeEnd;

    @ViewInject(R.id.queryBtn)
    private Button queryBtn;

    private static final String[] alarmTypeMsgList = new String[]{"All", AlarmType.INFO.getMessage(), AlarmType.ALARM.getMessage(), AlarmType.FAULT.getMessage()};

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
        timeStart.setOnClickListener(this);
        timeEnd.setOnClickListener(this);
        queryBtn.setOnClickListener(this);

        alarmType.setOffset(1);
        alarmType.setItems(Arrays.asList(alarmTypeMsgList));
        alarmType.setSeletion(1);
        alarmType.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                LogUtil.i("selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        deviceType.setOffset(1);
        deviceType.setItems(Arrays.asList(alarmTypeMsgList));
        deviceType.setSeletion(1);
        deviceType.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                LogUtil.i("selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
        deviceName.setOffset(1);
        deviceName.setItems(Arrays.asList(alarmTypeMsgList));
        deviceName.setSeletion(1);
        deviceName.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                LogUtil.i("selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
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
                i.putExtra("date", timeStart.getText().toString());
                startActivityForResult(i, ActivityResultConstant.REQUEST_CODE_DATE_START);
                break;
            case R.id.timeEnd:
                i = new Intent(AlarmFilterActivity.this, DateInputActivity.class);
                i.putExtra("date", timeEnd.getText().toString());
                startActivityForResult(i, ActivityResultConstant.REQUEST_CODE_DATE_END);
                break;
            case R.id.queryBtn:
                i = new Intent(AlarmFilterActivity.this, AlarmListActivity.class);
                i.putExtra("alarmType", alarmType.getSeletedItem().toString().trim().replace("All", ""));
                i.putExtra("timeStart", timeStart.getText().toString().trim());
                i.putExtra("timeEnd", timeEnd.getText().toString().trim());
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