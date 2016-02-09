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
import com.rat.nm.adapter.OperateLogListAdapter;
import com.rat.nm.common.MessageSignConstant;
import com.rat.nm.controller.OperateLogController;
import com.rat.nm.entity.enums.DataGetType;
import com.rat.nm.entity.enums.OperateLogType;
import com.rat.nm.entity.model.OperateLog;
import com.rat.nm.util.StringUtils;
import com.rat.nm.view.dialog.CustomProgressDialog;
import com.rat.nm.view.dialog.PromptDialog;
import com.rat.nm.view.pull2refresh.XListView;

import java.util.ArrayList;
import java.util.List;

public class OperateLogListActivity extends BaseActivity implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.operateLogListLV)
    private XListView operateLogListLV;
    @ViewInject(R.id.empty)
    private LinearLayout empty;

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
        OperateLog operateLog = operateLogList.get((int) id);
        Intent i = new Intent(OperateLogListActivity.this, OperateLogListActivity.class);
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

                //TODO -------------本地构造数据-------------------
                OperateLog o;
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "Down Conv -2", "liang", "2015-02-27 03:02:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "Down Conv -2", "zhang", "2015-03-27 12:02:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "AUPC", "liang", "2015-12-27 08:02:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "Down Conv -1", "zhang", "2015-12-27 12:02:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.AUTO.getMessage(), "LNB", "liang", "2015-12-27 08:22:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "AUPC", "zhang", "2015-12-27 08:22:34");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.AUTO.getMessage(), "Down Conv -2", "zhang", "2015-11-27 08:02:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.AUTO.getMessage(), "Down Conv -1", "zhang", "2015-12-27 08:02:43");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "AUPC", "liang", "2015-12-27 01:12:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "AUPC", "ruisanx", "2015-12-27 08:02:34");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "Up Conv", "liang", "2015-12-27 02:11:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.AUTO.getMessage(), "Up Conv", "ruisanx", "2015-12-27 08:02:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.MANUAL.getMessage(), "Up Conv", "ruisanx", "2015-02-27 03:22:12");
                operateLogList.add(o);
                o = new OperateLog(OperateLogType.AUTO.getMessage(), "Down Conv -1", "ruisanx", "2015-01-27 08:02:12");
                operateLogList.add(o);

                //TODO -------------本地构造数据-------------------


                //TODO -------------本地过滤数据-------------------

                if (StringUtils.isNotBlank(operateUser)) {
                    List<OperateLog> deleteList = new ArrayList<OperateLog>();
                    for (OperateLog operateLog : operateLogList) {
                        if (!operateLog.getUserName().toUpperCase().contains(operateUser.toUpperCase())) {//不包涵
                            deleteList.add(operateLog);
                        }
                    }
                    operateLogList.removeAll(deleteList);
                }

                if (StringUtils.isNotBlank(operateType)) {
                    List<OperateLog> deleteList = new ArrayList<OperateLog>();
                    for (OperateLog operateLog : operateLogList) {
                        if (!operateLog.getType().toUpperCase().contains(operateType.toUpperCase())) {//不包涵
                            deleteList.add(operateLog);
                        }
                    }
                    operateLogList.removeAll(deleteList);
                }
                //TODO -------------本地过滤数据-------------------


                dataGetType = msg.getData().getString("dataGetType");
                // 刷新列表
                if (dataGetType.equals(DataGetType.UPDATE.getType())) {
                    adapter.modifyData(operateLogList, true);
                } else if (dataGetType.equals(DataGetType.PAGE_DOWN.getType()))
                    adapter.modifyData(operateLogList, true);

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