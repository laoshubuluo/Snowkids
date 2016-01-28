package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.adapter.DeviceTypeListAdapter;
import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.controller.DeviceController;
import com.rat.nm.entity.model.DeviceType;
import com.rat.nm.view.dialog.CustomProgressDialog;
import com.rat.nm.view.dialog.PromptDialog;
import com.rat.nm.view.pull2refresh.XListView;

import java.util.ArrayList;
import java.util.List;

public class DeviceTypeListActivity extends BaseActivity implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.deviceTypeListLV)
    private XListView deviceTypeListLV;
    @ViewInject(R.id.empty)
    private LinearLayout empty;

    private List<DeviceType> deviceTypeList = new ArrayList<DeviceType>();
    private DeviceTypeListAdapter adapter;
    private DeviceController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_type_list);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        controller = new DeviceController(getApplication(), handler);
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.device_type);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);

        deviceTypeListLV.setOnItemClickListener(this);
        deviceTypeListLV.setPullRefreshEnable(true);
        deviceTypeListLV.setPullLoadEnable(false);
        deviceTypeListLV.setAutoLoadEnable(false);
        deviceTypeListLV.setXListViewListener(this);
        deviceTypeListLV.setRefreshTime();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        adapter = new DeviceTypeListAdapter(getApplicationContext(), deviceTypeList);
        deviceTypeListLV.setAdapter(adapter);

        customProgressDialog = new CustomProgressDialog(this, getString(R.string.loading));
        customProgressDialog.show();
        updateData();
    }

    /**
     * 更新数据
     */
    private void updateData() {
        controller.getTypeList();
    }

    @Override
    public void onRefresh() {
        updateData();
    }

    @Override
    public void onLoadMore() {
        updateData();
    }

    private void onLoad() {
        deviceTypeListLV.stopRefresh();
        deviceTypeListLV.stopLoadMore();
        deviceTypeListLV.setRefreshTime();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        DeviceType deviceType = deviceTypeList.get(position);
        Intent i = new Intent(DeviceTypeListActivity.this, DeviceListActivity.class);
        i.putExtra("deviceType", deviceType.getName());
        startActivity(i);
    }

    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        if (promptDialog == null || promptDialog.isShowing())
            promptDialog = new PromptDialog(DeviceTypeListActivity.this);
        if (null != customProgressDialog) {
            customProgressDialog.dismiss();
        }
        Intent intent = null;
        int code;
        String message;
        switch (msg.what) {
            case MessageSignConstant.DEVICE_TYPE_LIST_GET_SUCCESS:
                deviceTypeList = (List<DeviceType>) msg.getData().getSerializable("deviceTypeList");
                if (null == deviceTypeList)
                    deviceTypeList = new ArrayList<DeviceType>();
                adapter.modifyData(deviceTypeList, true);
                // 是否存在数据
                if (deviceTypeList.isEmpty()) {
                    empty.setVisibility(View.VISIBLE);
                    empty.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            onRefresh();
                        }
                    });
                } else
                    empty.setVisibility(View.GONE);
                break;
            case MessageSignConstant.DEVICE_TYPE_LIST_GET_FAILURE:
                code = msg.getData().getInt("code");
                message = msg.getData().getString("message");
                promptDialog.initData("", message);
                promptDialog.show();
                break;
            case MessageSignConstant.SERVER_OR_NETWORK_ERROR:
                promptDialog.initData("", msg.getData().getString("message"));
                promptDialog.show();
                break;
            case MessageSignConstant.UNKNOWN_ERROR:
                Toast.makeText(getApplication(), getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                break;
        }
        // 加载效果取消
        onLoad();
        return false;
    }
}