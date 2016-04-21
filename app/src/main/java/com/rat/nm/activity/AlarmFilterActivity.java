package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.activity.base.DateInputActivity;
import com.rat.nm.common.ActivityResultConstant;
import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.controller.DeviceController;
import com.rat.nm.entity.enums.AlarmType;
import com.rat.nm.entity.enums.DataGetType;
import com.rat.nm.entity.model.Device;
import com.rat.nm.entity.model.DeviceType;
import com.rat.nm.util.LogUtil;
import com.rat.nm.util.UserUtils;
import com.rat.nm.view.WheelView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    private List<DeviceType> deviceTypeList;
    private List<Device> deviceList;
    private DeviceController controller;

    private String[] alarmTypeMsgList = new String[]{"All", AlarmType.INFO.getMessage(), AlarmType.ALARM.getMessage(), AlarmType.FAULT.getMessage()};
    private List<String> deviceTypeMsgList;
    private List<String> deviceNameMsgList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_filter);

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
    }

    /**
     * 初始化数据
     */
    public void initData() {
        initDeviceTypeData();
        initDeviceNameData();
        controller.getTypeList();
    }

    /**
     * 初始化设备类型数据
     */
    public void initDeviceTypeData() {
        deviceTypeMsgList = new ArrayList<String>();
        deviceTypeMsgList.add("All");
        if (null != deviceTypeList && deviceTypeList.size() > 0) {
            for (DeviceType deviceType : deviceTypeList) {
                deviceTypeMsgList.add(deviceType.getName());
            }
        }
        deviceType.setOffset(1);
        deviceType.setItems(deviceTypeMsgList);
        deviceType.setSeletion(0);
        deviceType.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                LogUtil.i("selectedIndex: " + selectedIndex + ", item: " + item);
                if (1 != selectedIndex) { // 非选中All
                    controller.getList(0, 0, DataGetType.UPDATE, item, "");
                }
            }
        });
    }

    /**
     * 初始化设备数据
     */
    public void initDeviceNameData() {
        deviceNameMsgList = new ArrayList<String>();
        deviceNameMsgList.add("All");
        if (null != deviceList && deviceList.size() > 0) {
            for (Device device : deviceList) {
                deviceNameMsgList.add(device.getNameInEN());
            }
        }
        deviceName.setOffset(1);
        deviceName.setItems(deviceNameMsgList);
        deviceName.setSeletion(0);
        deviceName.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                LogUtil.i("selectedIndex: " + selectedIndex + ", item: " + item);
            }
        });
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
                int selectedIndex = deviceName.getSeletedIndex();
                if (0 != selectedIndex) { // 非选中All
                    Device device = deviceList.get(selectedIndex - 1);
                    i.putExtra("deviceId", device.getId());
                } else {
                    i.putExtra("deviceId", "");
                }
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


    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        int code;
        String message;
        switch (msg.what) {
            case MessageSignConstant.DEVICE_TYPE_LIST_GET_SUCCESS:
                deviceTypeList = (List<DeviceType>) msg.getData().getSerializable("deviceTypeList");
                initDeviceTypeData();
                break;
            case MessageSignConstant.DEVICE_TYPE_LIST_GET_FAILURE:
                code = msg.getData().getInt("code");
                message = msg.getData().getString("message");
                // 检查token是否失效
                if (UserUtils.getInstance(AlarmFilterActivity.this).isTokenError(code, message))
                    break;
                promptDialog.initData("", message);
                promptDialog.show();
                break;
            case MessageSignConstant.DEVICE_LIST_GET_SUCCESS:
                deviceList = (List<Device>) msg.getData().getSerializable("deviceList");
                initDeviceNameData();
                break;
            case MessageSignConstant.DEVICE_LIST_GET_FAILURE:
                code = msg.getData().getInt("code");
                message = msg.getData().getString("message");
                // 检查token是否失效
                if (UserUtils.getInstance(AlarmFilterActivity.this).isTokenError(code, message))
                    break;
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
        return false;
    }
}