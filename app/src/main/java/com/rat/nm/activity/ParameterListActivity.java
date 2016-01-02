package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.adapter.ParameterListAdapter;
import com.rat.nm.entity.Parameter;

import java.util.ArrayList;
import java.util.List;

public class ParameterListActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.parameterListLV)
    protected ListView parameterListLV;

    private List<Parameter> parameterList = new ArrayList<Parameter>();
    private ParameterListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_list);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.parameter_list);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        for (int i = 100; i < 20000; i++)
            parameterList.add(new Parameter(i, "The name of no " + i + " parameter", "this is a description for parameter " + i, "http://www.baidu.com/wewe?name=1&&to=222" + i));
        adapter = new ParameterListAdapter(getApplicationContext(), parameterList);
        parameterListLV.setAdapter(adapter);
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
}