package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.activity.base.DateInputActivity;
import com.rat.nm.common.ActivityResultConstant;
import com.rat.nm.entity.enums.OperateLogType;
import com.rat.nm.util.LogUtil;
import com.rat.nm.view.WheelView;

import java.util.Arrays;

public class OperateLogFilterActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.operateLogUser)
    private EditText operateLogUser;
    @ViewInject(R.id.operateLogType)
    private WheelView operateLogType;
    @ViewInject(R.id.timeStart)
    private TextView timeStart;
    @ViewInject(R.id.timeEnd)
    private TextView timeEnd;

    @ViewInject(R.id.queryBtn)
    private Button queryBtn;

    private static final String[] messageList = new String[]{"", OperateLogType.AUTO.getMessage(), OperateLogType.MANUAL.getMessage()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operate_log_filter);

        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.operate_log_filter);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        timeStart.setOnClickListener(this);
        timeEnd.setOnClickListener(this);
        queryBtn.setOnClickListener(this);

        operateLogType.setOffset(1);
        operateLogType.setItems(Arrays.asList(messageList));
        operateLogType.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
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
    }

    @Override
    public void onClick(View v) {
        Intent i;
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
            case R.id.timeStart:
                i = new Intent(OperateLogFilterActivity.this, DateInputActivity.class);
                startActivityForResult(i, ActivityResultConstant.REQUEST_CODE_DATE_START);
                break;
            case R.id.timeEnd:
                i = new Intent(OperateLogFilterActivity.this, DateInputActivity.class);
                startActivityForResult(i, ActivityResultConstant.REQUEST_CODE_DATE_END);
                break;
            case R.id.queryBtn:
                i = new Intent(OperateLogFilterActivity.this, OperateLogListActivity.class);
                i.putExtra("operateUser", operateLogUser.getText().toString().trim());
                i.putExtra("operateType", operateLogType.getSeletedItem().toString().trim());
                i.putExtra("timeStart", timeStart.getText().toString().trim());
                i.putExtra("timeEnd", timeEnd.getText().toString().trim());
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
}