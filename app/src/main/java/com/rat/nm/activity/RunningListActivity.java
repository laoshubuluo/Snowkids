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
import com.rat.nm.adapter.RunningListAdapter;
import com.rat.nm.entity.Device;

import java.util.ArrayList;
import java.util.List;

public class RunningListActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.deviceListLV)
    protected ListView deviceListLV;

    private List<Device> deviceList = new ArrayList<Device>();
    private RunningListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_list);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.device_list);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        deviceListLV.setOnItemClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        for (int i = 100; i < 200; i++) {
            int status;
            if (i % 4 == 0)
                status = Device.STOP;
            else if (i % 3 == 0)
                status = Device.ERROR;
            else
                status = Device.NORMAL;
            deviceList.add(new Device(i, "第" + i + "号设备", status));
        }
        adapter = new RunningListAdapter(getApplicationContext(), deviceList);
        deviceListLV.setAdapter(adapter);
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
        Intent i = new Intent(RunningListActivity.this, RunningDetailActivity.class);
        startActivity(i);
    }
}