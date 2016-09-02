package com.rat.snowkids.activity;

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
import com.snowkids.snowkids.R;
import com.rat.snowkids.activity.base.BaseActivity;
import com.rat.snowkids.adapter.OperateLogListAdapter;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.controller.OperateLogController;
import com.rat.snowkids.entity.enums.DataGetType;
import com.rat.snowkids.entity.model.OperateLog;
import com.rat.snowkids.util.UserUtils;
import com.rat.snowkids.view.CountView;
import com.rat.snowkids.view.dialog.CustomProgressDialog;
import com.rat.snowkids.view.dialog.PromptDialog;
import com.rat.snowkids.view.pull2refresh.XListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperateLogListActivity extends BaseActivity implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.operateLogListLV)
    private XListView operateLogListLV;
    @ViewInject(R.id.empty)
    private LinearLayout empty;
    @ViewInject(R.id.countLL)
    private LinearLayout countLL;

    private List<OperateLog> operateLogList = new ArrayList<OperateLog>();
    private int totalPage = 0;
    private int currentPage = 0;
    private OperateLogListAdapter adapter;
    private OperateLogController controller;

    private String operateUser;// 检索参数
    private String operateType;// 检索参数
    private String timeStart;// 检索参数
    private String timeEnd;// 检索参数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate_log_list);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        controller = new OperateLogController(getApplication(), handler);

        operateUser = getIntent().getStringExtra("operateUser");
        operateType = getIntent().getStringExtra("operateType");
        timeStart = getIntent().getStringExtra("timeStart");
        timeEnd = getIntent().getStringExtra("timeEnd");
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.operate_log_list);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);

        operateLogListLV.setOnItemClickListener(this);
        operateLogListLV.setPullRefreshEnable(true);
        operateLogListLV.setPullLoadEnable(true);
        operateLogListLV.setAutoLoadEnable(false);
        operateLogListLV.setXListViewListener(this);
        operateLogListLV.setRefreshTime();
        operateLogListLV.setOnItemClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        adapter = new OperateLogListAdapter(getApplicationContext(), operateLogList);
        operateLogListLV.setAdapter(adapter);

        customProgressDialog = new CustomProgressDialog(this, getString(R.string.loading));
        customProgressDialog.show();
        updateData(DataGetType.UPDATE);
    }

    /**
     * 更新数据
     */
    private void updateData(DataGetType dataGetType) {
        controller.getList(totalPage, currentPage, dataGetType, operateUser, operateType, timeStart, timeEnd);
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
        operateLogListLV.stopRefresh();
        operateLogListLV.stopLoadMore();
        operateLogListLV.setRefreshTime();
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
        OperateLog operateLog = (OperateLog) parent.getAdapter().getItem(position);
        Intent i = new Intent(OperateLogListActivity.this, OperateLogDetailActivity.class);
        i.putExtra("operateLog", operateLog);
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
            promptDialog = new PromptDialog(OperateLogListActivity.this);
        if (null != customProgressDialog) {
            customProgressDialog.dismiss();
        }
        String dataGetType;
        Intent intent = null;
        int code;
        String message;
        switch (msg.what) {
            case MessageSignConstant.OPERATE_LOG_LIST_GET_SUCCESS:
                totalPage = msg.getData().getInt("totalPage");
                currentPage = msg.getData().getInt("currentPage");
                operateLogList = (List<OperateLog>) msg.getData().getSerializable("operateLogList");
                if (null == operateLogList)
                    operateLogList = new ArrayList<OperateLog>();
                dataGetType = msg.getData().getString("dataGetType");
                // 刷新列表
                if (dataGetType.equals(DataGetType.UPDATE.getType())) {
                    adapter.modifyData(operateLogList, true);
                    initCountView(adapter.getList());
                } else if (dataGetType.equals(DataGetType.PAGE_DOWN.getType())) {
                    adapter.modifyData(operateLogList, false);
                    initCountView(adapter.getList());
                }

                // 判断数据获取状态（无数据或无更多数据）
                // 无数据
                if (totalPage == 0) {
                    operateLogListLV.setPullLoadEnable(false);
                }
                // 无更多数据
                else if (totalPage == currentPage) {
                    operateLogListLV.setPullLoadEnable(false);
                } else {
                    operateLogListLV.setPullLoadEnable(true);
                }
                // 是否存在数据
                if (operateLogList.isEmpty() && dataGetType.equals(DataGetType.UPDATE.getType())) {
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
            case MessageSignConstant.OPERATE_LOG_LIST_GET_FAILURE:
                code = msg.getData().getInt("code");
                message = msg.getData().getString("message");
                // 检查token是否失效
                if (UserUtils.getInstance(OperateLogListActivity.this).isTokenError(code, message))
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

    private void initCountView(List<OperateLog> list) {
        countLL.removeAllViews();
        // 计算数量
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (OperateLog item : list) {
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