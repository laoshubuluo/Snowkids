package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.entity.Alarm;
import com.rat.nm.view.AlarmInfoView;
import com.rat.nm.view.RunningAlarmListItemView;

import java.util.ArrayList;
import java.util.List;

public class AlarmDetailActivity extends BaseActivity {

    @ViewInject(R.id.alarmInfoLV)
    protected ListView alarmInfoLV;

    private List<Alarm> alarmInfo = new ArrayList<Alarm>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_detail);



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
        for (int i = 100; i < 200; i++) {
            int level;
            String content;
            if (i % 4 == 0) {
                level = RunningAlarmListItemView.LEVEL_4;
                content = "com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: ";
            } else if (i % 3 == 0) {
                level = RunningAlarmListItemView.LEVEL_3;
                content = "g.gradle.process.internal.Exec ";
            } else if (i % 2 == 0) {
                level = RunningAlarmListItemView.LEVEL_2;
                content = "common.process.ProcessException: org.gradle.process.internal ";
            } else {
                level = RunningAlarmListItemView.LEVEL_1;
                content = "com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: ";
            }
            alarmInfo.add(new Alarm(i, "告警", content, level, "2015.08.18"));
        }
        alarmInfoLV.setAdapter(baseAdapter);
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return alarmInfo.size();
        }

        @Override
        public Object getItem(int position) {
            return alarmInfo.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            AlarmInfoView alarmInfoView = new AlarmInfoView(getApplication());
            Alarm alarm = alarmInfo.get(position);
            alarmInfoView.initData(alarm.getContent(), alarm.getStatus(), alarm.getTime());
            return alarmInfoView;
        }
    };


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
    @OnClick(R.id.loginBtn)
    public void signInBtnOnClick(View v) {
//        DemoService ds = new DemoService(getApplicationContext(), handler);
//        ds.testQuery();
//
//        DBDemo dbDemo = new DBDemo(getApplication());
//        dbDemo.save();
//        List<Demo> demoList = dbDemo.getAll();
//        for (Demo d : demoList)
//            LogUtil.e(d.getName() + "||" + d.getPassword());
        Intent i = new Intent(AlarmDetailActivity.this, AlarmDetailActivity.class);
        startActivity(i);
    }


}