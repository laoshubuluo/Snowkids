//package com.rat.nm.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.lidroid.xutils.ViewUtils;
//import com.lidroid.xutils.view.annotation.ViewInject;
//import com.rat.networkmanager.R;
//import com.rat.nm.activity.base.BaseActivity;
//import com.rat.nm.adapter.ParameterListAdapter;
//import com.rat.nm.common.MessageSignConstant;
//import com.rat.nm.controller.OperateLogController;
//import com.rat.nm.entity.model.OperateLog;
//import com.rat.nm.entity.model.Parameter;
//import com.rat.nm.view.dialog.CustomProgressDialog;
//import com.rat.nm.view.dialog.PromptDialog;
//import com.rat.nm.view.pull2refresh.XListView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OperateLogDetailActivity extends BaseActivity implements AdapterView.OnItemClickListener, XListView.IXListViewListener {
//    @ViewInject(R.id.top_name)
//    private TextView topTitleView;
//    @ViewInject(R.id.top_left)
//    private TextView topLeftView;
//
//    @ViewInject(R.id.parameterListLV)
//    private XListView parameterListLV;
//    @ViewInject(R.id.empty)
//    private LinearLayout empty;
//
//    @ViewInject(R.id.operateLogName)
//    private TextView operateLogName;
//    @ViewInject(R.id.operateLogModel)
//    private TextView operateLogModel;
//    @ViewInject(R.id.operateLogType)
//    private TextView operateLogType;
//    @ViewInject(R.id.operateLogDescribe)
//    private TextView operateLogDescribe;
//
//    private List<Parameter> parameterList = new ArrayList<Parameter>();
//    private ParameterListAdapter adapter;
//    private OperateLog operateLog;
//    private OperateLogController controller;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_operateLog_detail);
//        // 基础框架初始化
//        ViewUtils.inject(this);//xUtils框架注解注入view和事件
//        operateLog = (OperateLog) getIntent().getSerializableExtra("operateLog");
//        controller = new OperateLogController(getApplication(), handler);
//        initView();
//        initData();
//    }
//
//    /**
//     * 初始化界面
//     */
//    public void initView() {
//        topTitleView.setText(R.string.operateLog);
//        topLeftView.setVisibility(View.VISIBLE);
//        topLeftView.setOnClickListener(this);
//
//        parameterListLV.setOnItemClickListener(this);
//        parameterListLV.setPullRefreshEnable(false);
//        parameterListLV.setPullLoadEnable(false);
//        parameterListLV.setAutoLoadEnable(false);
//        parameterListLV.setXListViewListener(this);
//        parameterListLV.setRefreshTime();
//    }
//
//    /**
//     * 初始化数据
//     */
//    public void initData() {
//        if (null == operateLog)
//            return;
//        parameterList = operateLog.getParameterList();
//        if (null == parameterList)
//            parameterList = new ArrayList<Parameter>();
//        operateLogName.setText(operateLog.getName4Show());
//        operateLogType.setText(operateLog.getType());
//        operateLogModel.setText(operateLog.getModel());
//        operateLogDescribe.setText(operateLog.getDescribe());
//        adapter = new ParameterListAdapter(getApplicationContext(), parameterList);
//        parameterListLV.setAdapter(adapter);
//        //customProgressDialog = new CustomProgressDialog(this, getString(R.string.loading));
//        //customProgressDialog.show();
//        //controller.get(operateLog.getId());
//    }
//
//    /**
//     * 更新数据
//     */
//    private void updateData() {
//        controller.get(operateLog.getId());
//    }
//
//    @Override
//    public void onRefresh() {
//        updateData();
//    }
//
//    @Override
//    public void onLoadMore() {
//        updateData();
//    }
//
//    private void onLoad() {
//        parameterListLV.stopRefresh();
//        parameterListLV.stopLoadMore();
//        parameterListLV.setRefreshTime();
//    }
//
//    @Override
//    public void onClick(View v) {
//        Intent intent;
//        switch (v.getId()) {
//            case R.id.top_left:
//                finish();
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//    }
//
//    /**
//     * Handler发送message的逻辑处理方法
//     *
//     * @param msg
//     * @return
//     */
//    @Override
//    public boolean handleMessage(Message msg) {
//        if (promptDialog == null || promptDialog.isShowing())
//            promptDialog = new PromptDialog(OperateLogDetailActivity.this);
//        if (null != customProgressDialog) {
//            customProgressDialog.dismiss();
//        }
//        Intent intent = null;
//        int code;
//        String message;
//        switch (msg.what) {
//            case MessageSignConstant.OPERATE_LOG_GET_SUCCESS:
//                operateLog = (OperateLog) msg.getData().getSerializable("operateLog");
//                adapter.modifyData(operateLog.getParameterList(), true);
//                // 是否存在数据
//                if (0 == operateLog.getParameterList().size()) {
//                    empty.setVisibility(View.VISIBLE);
//                    empty.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            onRefresh();
//                        }
//                    });
//                } else
//                    empty.setVisibility(View.GONE);
//                break;
//            case MessageSignConstant.OPERATE_LOG_GET_FAILURE:
//                code = msg.getData().getInt("code");
//                message = msg.getData().getString("message");
//                promptDialog.initData("", message);
//                promptDialog.show();
//                break;
//            case MessageSignConstant.SERVER_OR_NETWORK_ERROR:
//                promptDialog.initData("", msg.getData().getString("message"));
//                promptDialog.show();
//                break;
//            case MessageSignConstant.UNKNOWN_ERROR:
//                Toast.makeText(getApplicationContext(), getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
//                break;
//        }
//        // 加载效果取消
//        onLoad();
//        return false;
//    }
//}