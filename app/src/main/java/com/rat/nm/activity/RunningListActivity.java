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
import com.rat.nm.entity.Device;
import com.rat.nm.view.RunningAlarmListItemView;

import java.util.ArrayList;
import java.util.List;

public class RunningListActivity extends BaseActivity {

    @ViewInject(R.id.deviceListLV)
    protected ListView deviceListLV;

    private List<Device> deviceList = new ArrayList<Device>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_list);

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
            int status;
            if (i % 4 == 0)
                status = RunningAlarmListItemView.STOP;
            else if (i % 3 == 0)
                status = RunningAlarmListItemView.ERROR;
            else
                status = RunningAlarmListItemView.NORMAL;
            deviceList.add(new Device(i, "第" + i + "号设备", status));
        }
        deviceListLV.setAdapter(baseAdapter);
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return deviceList.size();
        }

        @Override
        public Object getItem(int position) {
            return deviceList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            RunningAlarmListItemView item = new RunningAlarmListItemView(getApplication());
            Device device = deviceList.get(position);
            item.initData(position, device.getName(), device.getStatus());
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(RunningListActivity.this, RunningDetailActivity.class);
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