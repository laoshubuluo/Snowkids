//package com.rat.nm.activity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.lidroid.xutils.ViewUtils;
//import com.lidroid.xutils.view.annotation.ViewInject;
//import com.rat.networkmanager.R;
//import com.rat.nm.activity.base.BaseActivity;
//import com.rat.nm.adapter.OperateLogDetailAdapter;
//import com.rat.nm.entity.enums.OperateLogType;
//import com.rat.nm.entity.enums.DataGetType;
//import com.rat.nm.entity.model.OperateLog;
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
//    @ViewInject(R.id.operateLogDetailLV)
//    private XListView operateLogDetailLV;
//    @ViewInject(R.id.empty)
//    private LinearLayout empty;
//
//    private List<OperateLog> operateLogDetailList = new ArrayList<OperateLog>();
//    private int totalPage = 0;
//    private int currentPage = 0;
//    private OperateLogDetailAdapter adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_operateLog_detail);
//        // 基础框架初始化
//        ViewUtils.inject(this);//xUtils框架注解注入view和事件
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
//        operateLogDetailLV.setOnItemClickListener(this);
//        operateLogDetailLV.setPullRefreshEnable(true);
//        operateLogDetailLV.setPullLoadEnable(true);
//        operateLogDetailLV.setAutoLoadEnable(false);
//        operateLogDetailLV.setXListViewListener(this);
//        operateLogDetailLV.setRefreshTime();
//    }
//
//    /**
//     * 初始化数据
//     */
//    public void initData() {
//        for (int i = 100; i < 200; i++) {
//            int level;
//            String content;
//            if (i % 4 == 0) {
//                level = OperateLogType.INFO.getCode();
//                content = "com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: ";
//            } else if (i % 3 == 0) {
//                level = OperateLogType.INFO.getCode();
//                content = "g.gradle.process.internal.Exec ";
//            } else if (i % 2 == 0) {
//                level = OperateLogType.INFO.getCode();
//                content = "common.process.ProcessException: org.gradle.process.internal ";
//            } else {
//                level = OperateLogType.INFO.getCode();
//                content = "com.android.ide.common.process.ProcessException: org.gradle.process.internal.ExecException: ";
//            }
////            operateLogDetailList.add(new OperateLog(i, "告警", content,  OperateLogType.INFO.getCode(), "2015.08.18"));
//        }
//        adapter = new OperateLogDetailAdapter(getApplicationContext(), operateLogDetailList);
//        operateLogDetailLV.setAdapter(adapter);
//    }
//
//    /**
//     * 更新数据
//     */
//    private void updateData(final DataGetType dataGetType) {
////        controller.getFamily(totalPage, currentPage, dataGetType, getActivity().getApplication());
//    }
//
//    @Override
//    public void onRefresh() {
//        currentPage = 0;
//        updateData(DataGetType.UPDATE);
//    }
//
//    @Override
//    public void onLoadMore() {
//        updateData(DataGetType.PAGE_DOWN);
//    }
//
//    private void onLoad() {
//        operateLogDetailLV.stopRefresh();
//        operateLogDetailLV.stopLoadMore();
//        operateLogDetailLV.setRefreshTime();
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
////        User user = (User) parent.getAdapter().getItem(position);
////        if (null == user)
////            return;
////        Intent intent = new Intent(DeviceDetailActivity.this, UserInfoActivity.class);
////        startActivity(intent);
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
//        String dataGetType;
//        Intent intent = null;
//        int code;
//        String message;
//        switch (msg.what) {
////            case MessageSignConstant.NEARBY_GET_FAMILY_SUCCESS:
////                totalPage = msg.getData().getInt("totalPage");
////                currentPage = msg.getData().getInt("currentPage");
////                userList = (List<User>) msg.getData().getSerializable("userList");
////                dataGetType = msg.getData().getString("dataGetType");
////                // 刷新列表
////                if (dataGetType.equals(DataGetType.UPDATE.getType())) {
////                    familyAdapter.modifyData(userList, true);
////                } else if (dataGetType.equals(DataGetType.PAGE_DOWN.getType()))
////                    familyAdapter.modifyData(userList, false);
////
////                // 判断数据获取状态（无数据或无更多数据）
////                // 无数据
////                if (totalPage == 0) {
////                    operateLogDetailLV.setPullLoadEnable(false);
////                }
////                // 无更多数据
////                else if (totalPage == currentPage) {
////                    operateLogDetailLV.setPullLoadEnable(false);
////                } else {
////                    operateLogDetailLV.setPullLoadEnable(true);
////                }
////                // 是否存在数据
////                if (userList.isEmpty() && dataGetType.equals(DataGetType.UPDATE.getType())) {
////                    empty.setVisibility(View.VISIBLE);
////                    empty.setOnClickListener(new View.OnClickListener() {
////                        @Override
////                        public void onClick(View v) {
////                            onRefresh();
////                        }
////                    });
////                } else
////                    empty.setVisibility(View.GONE);
////                isFriendDataLoading = false;
////                break;
////            case MessageSignConstant.NEARBY_GET_FAMILY_FAILURE:
////                code = msg.getData().getInt("code");
////                message = msg.getData().getString("message");
////                promptDialog.initData(getString(R.string.nearby_family_get_failure), message);
////                promptDialog.show();
////                isFriendDataLoading = false;
////                break;
////            case MessageSignConstant.NEARBY_GET_MEETING_FAILURE:
////                code = msg.getData().getInt("code");
////                message = msg.getData().getString("message");
////                promptDialog.initData(getString(R.string.nearby_meeting_get_failure), message);
////                promptDialog.show();
////                isMeetingDataLoading = false;
////                break;
////            case MessageSignConstant.SERVER_OR_NETWORK_ERROR:
////                promptDialog.initData(getString(R.string.nearby_get_error), msg.getData().getString("message"));
////                promptDialog.show();
////                isFriendDataLoading = false;
////                isMeetingDataLoading = false;
////                break;
////            case MessageSignConstant.UNKNOWN_ERROR:
////                Toast.makeText(getActivity(), getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
////                isFriendDataLoading = false;
////                isMeetingDataLoading = false;
////                break;
//        }
//        // 加载效果取消
//        onLoad();
//        return false;
//    }
//}