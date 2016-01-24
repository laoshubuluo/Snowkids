package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

public class AlarmFilterActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.queryBtn)
    private Button queryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_filter);

        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
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
        queryBtn.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
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
//                Intent i = new Intent(LoginActivity.this, MainActivity.class);
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
            case R.id.queryBtn:
                Intent i = new Intent(AlarmFilterActivity.this, AlarmListActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}