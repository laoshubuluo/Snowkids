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
import com.rat.nm.adapter.AlarmListAdapter;
import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.controller.AlarmController;
import com.rat.nm.entity.enums.DataGetType;
import com.rat.nm.entity.model.Alarm;
import com.rat.nm.util.UserUtils;
import com.rat.nm.view.CountView;
import com.rat.nm.view.dialog.CustomProgressDialog;
import com.rat.nm.view.dialog.PromptDialog;
import com.rat.nm.view.pull2refresh.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AlarmListActivity extends BaseActivity implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.alarmListLV)
    private XListView alarmListLV;
    @ViewInject(R.id.empty)
    private LinearLayout empty;
    @ViewInject(R.id.countLL)
    private LinearLayout countLL;


    private List<Alarm> alarmList = new ArrayList<Alarm>();
    private int totalPage = 0;
    private int currentPage = 0;
    private AlarmListAdapter adapter;
    private AlarmController controller;

    private String alarmType;// 检索参数
    private String timeStart;// 检索参数
    private String timeEnd;// 检索参数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_list);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        controller = new AlarmController(getApplication(), handler);

        alarmType = getIntent().getStringExtra("alarmType");
        timeStart = getIntent().getStringExtra("timeStart");
        timeEnd = getIntent().getStringExtra("timeEnd");
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
        alarmListLV.setPullRefreshEnable(true);
        alarmListLV.setPullLoadEnable(true);
        alarmListLV.setAutoLoadEnable(false);
        alarmListLV.setXListViewListener(this);
        alarmListLV.setRefreshTime();
    }

    /**
     * 初始化数据
     */
    public void initData() {
        adapter = new AlarmListAdapter(getApplicationContext(), alarmList);
        alarmListLV.setAdapter(adapter);

        customProgressDialog = new CustomProgressDialog(this, getString(R.string.loading));
        customProgressDialog.show();
        updateData(DataGetType.UPDATE);
    }

    /**
     * 更新数据
     */
    private void updateData(DataGetType dataGetType) {
        controller.getList(totalPage, currentPage, dataGetType, alarmType, timeStart, timeEnd);
    }

    @Override
    public void onRefresh() {
        currentPage = 0;
        updateData(DataGetType.UPDATE);
    }

    @Override
    public void onLoadMore() {
        currentPage++;
        updateData(DataGetType.PAGE_DOWN);
    }

    private void onLoad() {
        alarmListLV.stopRefresh();
        alarmListLV.stopLoadMore();
        alarmListLV.setRefreshTime();
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
        Alarm alarm = (Alarm) parent.getAdapter().getItem(position);
        Intent i = new Intent(AlarmListActivity.this, AlarmDetailActivity.class);
        i.putExtra("alarm", alarm);
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
            promptDialog = new PromptDialog(AlarmListActivity.this);
        if (null != customProgressDialog) {
            customProgressDialog.dismiss();
        }
        String dataGetType;
        Intent intent = null;
        int code;
        String message;
        switch (msg.what) {
            case MessageSignConstant.ALARM_LIST_GET_SUCCESS:
                totalPage = msg.getData().getInt("totalPage");
                currentPage = msg.getData().getInt("currentPage");
                alarmList = (List<Alarm>) msg.getData().getSerializable("alarmList");
                if (null == alarmList)
                    alarmList = new ArrayList<Alarm>();
                dataGetType = msg.getData().getString("dataGetType");
                // 刷新列表
                if (dataGetType.equals(DataGetType.UPDATE.getType())) {
                    adapter.modifyData(alarmList, true);
                    initCountView(adapter.getList());
                } else if (dataGetType.equals(DataGetType.PAGE_DOWN.getType())) {
                    adapter.modifyData(alarmList, false);
                    initCountView(adapter.getList());
                }

                // 判断数据获取状态（无数据或无更多数据）
                // 无数据
                if (totalPage == 0) {
                    alarmListLV.setPullLoadEnable(false);
                }
                // 无更多数据
                else if (totalPage == currentPage) {
                    alarmListLV.setPullLoadEnable(false);
                } else {
                    alarmListLV.setPullLoadEnable(true);
                }
                // 是否存在数据
                if (alarmList.isEmpty() && dataGetType.equals(DataGetType.UPDATE.getType())) {
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
            case MessageSignConstant.ALARM_LIST_GET_FAILURE:
                code = msg.getData().getInt("code");
                message = msg.getData().getString("message");
                // 检查token是否失效
                if (UserUtils.getInstance(AlarmListActivity.this).isTokenError(code, message))
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
        // 加载效果取消
        onLoad();
        return false;
    }

    private void initCountView(List<Alarm> list) {
        countLL.removeAllViews();
        // 计算数量
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (Alarm item : list) {
            String key = item.getType().toUpperCase();
            int count = 0;
            if (map.containsKey(key))
                count = map.get(key);
            count++;
            map.put(key, count);
        }
        // 绘制布局
        CountView countView;
        countView = new CountView(getApplicationContext(), "ALL", String.valueOf(list.size()));
        countLL.addView(countView);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            countView = new CountView(getApplicationContext(), entry.getKey(), String.valueOf(String.valueOf(entry.getValue())));
            countLL.addView(countView);
        }
    }
}