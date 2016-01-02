package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.adapter.AlarmListAdapter;
import com.rat.nm.entity.Alarm;

import java.util.ArrayList;
import java.util.List;

public class AlarmListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.alarmListLV)
    protected ListView alarmListLV;

    private List<Alarm> alarmList = new ArrayList<Alarm>();
    private AlarmListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.alarm_list);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);

        alarmListLV.setOnItemClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        for (int i = 100; i < 200; i++) {
            int level;
            if (i % 4 == 0)
                level = Alarm.LEVEL_4;
            else if (i % 3 == 0)
                level = Alarm.LEVEL_3;
            else if (i % 2 == 0)
                level = Alarm.LEVEL_2;
            else
                level = Alarm.LEVEL_1;
            alarmList.add(new Alarm(i, "Alarm! alarm:" + i, "", level, ""));
        }
        adapter = new AlarmListAdapter(getApplicationContext(), alarmList);
        alarmListLV.setAdapter(adapter);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Alarm alarm = alarmList.get(position);
        Intent i = new Intent(AlarmListActivity.this, AlarmDetailActivity.class);
        startActivity(i);
    }
}