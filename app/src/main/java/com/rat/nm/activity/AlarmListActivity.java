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
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.entity.Alarm;
import com.rat.nm.view.RunningAlarmListItemView;

import java.util.ArrayList;
import java.util.List;

public class AlarmListActivity extends BaseActivity {

    @ViewInject(R.id.alarmListLV)
    protected ListView alarmListLV;

    private List<Alarm> alarmList = new ArrayList<Alarm>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);

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
        for (int i = 100; i < 200; i++) {
            int level;
            if (i % 4 == 0)
                level = RunningAlarmListItemView.LEVEL_4;
            else if (i % 3 == 0)
                level = RunningAlarmListItemView.LEVEL_3;
            else if (i % 2 == 0)
                level = RunningAlarmListItemView.LEVEL_2;
            else
                level = RunningAlarmListItemView.LEVEL_1;

            alarmList.add(new Alarm(i, "Alarm! alarm:" + i, "", level, ""));
        }
        alarmListLV.setAdapter(baseAdapter);
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return alarmList.size();
        }

        @Override
        public Object getItem(int position) {
            return alarmList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RunningAlarmListItemView item;
            item = new RunningAlarmListItemView(getApplication());
            Alarm alarm = alarmList.get(position);
            item.initData(position, alarm.getName(), alarm.getStatus());
//            if (convertView == null) {
//                // inflate the view for row from xml file
//                // keep a reference to each widget on the row.
//                // here I only care about the button
//                item = new RunningAlarmListItemView(getApplication());
//                Alarm alarm = alarmList.get(position);
//                item.initData(position, alarm.getName(), alarm.getStatus());
//                convertView.setTag(item);
//            } else {
//                item = (RunningAlarmListItemView) convertView.getTag();
//            }
            // redefine the action for the button corresponding to the row
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(AlarmListActivity.this, AlarmDetailActivity.class);
                    startActivity(i);
                }
            });
            return item;
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
}