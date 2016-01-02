package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.adapter.AlarmDetailAdapter;
import com.rat.nm.entity.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmDetailActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.alarmDetailLV)
    protected ListView alarmDetailLV;

    private List<Alarm> alarmDetailList = new ArrayList<Alarm>();
    private AlarmDetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.alarm);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        for (int i = 100; i < 200; i++) {
            int level;
            String content;
            if (i % 4 == 0) {
                level = Alarm.LEVEL_4;
                content = "com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: ";
            } else if (i % 3 == 0) {
                level = Alarm.LEVEL_3;
                content = "g.gradle.process.internal.Exec ";
            } else if (i % 2 == 0) {
                level = Alarm.LEVEL_2;
                content = "common.process.ProcessException: org.gradle.process.internal ";
            } else {
                level = Alarm.LEVEL_1;
                content = "com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: ";
            }
            alarmDetailList.add(new Alarm(i, "告警", content, level, "2015.08.18"));
        }
        adapter = new AlarmDetailAdapter(getApplicationContext(), alarmDetailList);
        alarmDetailLV.setAdapter(adapter);
    }

    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
//        if (customProgressDialog != null)
//            customProgressDialog.dismiss();
//        if (promptDialog == null || promptDialog.isShowing())
//            promptDialog = new PromptDialog(LoginActivity.this);
//        switch (msg.what) {
//            case MessageSignConstant.LOGIN_SUCCESS:
////                User user = (User) msg.getData().getSerializable("user");
//                Intent i = new Intent(LoginActivity.this, MenuActivity.class);
//                startActivity(i);
//                finish();
//                break;
//        }
        return false;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
            default:
                break;
        }
    }
}