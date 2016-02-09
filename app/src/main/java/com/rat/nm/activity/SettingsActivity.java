package com.rat.nm.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.util.AppUtils;

public class SettingsActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.remeberMeIV)
    private ImageView remeberMeIV;
    @ViewInject(R.id.receivePushMessageIV)
    private ImageView receivePushMessageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.settings);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        remeberMeIV.setOnClickListener(this);
        receivePushMessageIV.setOnClickListener(this);
        // 是否记住我
        if (AppUtils.getInstance().isRemeberMe())
            remeberMeIV.setBackgroundResource(R.mipmap.settings_turn_on);
        else
            remeberMeIV.setBackgroundResource(R.mipmap.settings_turn_off);
        // 是否接收push消息
        if (AppUtils.getInstance().isReceivePushMessage())
            receivePushMessageIV.setBackgroundResource(R.mipmap.settings_turn_on);
        else
            receivePushMessageIV.setBackgroundResource(R.mipmap.settings_turn_off);
    }

    /**
     * 初始化数据
     */

    public void initData() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left:
                finish();
                break;
            case R.id.remeberMeIV:
                // 是否记住我
                if (AppUtils.getInstance().isRemeberMe()) {
                    AppUtils.getInstance().updateIsRemeberMe(false);
                    remeberMeIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsRemeberMe(true);
                    remeberMeIV.setBackgroundResource(R.mipmap.settings_turn_on);
                }
                Toast.makeText(getApplicationContext(), getString(R.string.setting_success), Toast.LENGTH_SHORT).show();
                break;
            case R.id.receivePushMessageIV:
                // 是否接收push消息
                if (AppUtils.getInstance().isReceivePushMessage()) {
                    AppUtils.getInstance().updateIsReceivePushMessage(false);
                    receivePushMessageIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsReceivePushMessage(true);
                    receivePushMessageIV.setBackgroundResource(R.mipmap.settings_turn_on);
                }
                Toast.makeText(getApplicationContext(), getString(R.string.setting_success), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}