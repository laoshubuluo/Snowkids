package com.rat.snowkids.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.snowkids.snowkids.R;
import com.rat.snowkids.activity.base.BaseActivity;
import com.rat.snowkids.entity.model.Environment;
import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.LogUtil;
import com.rat.snowkids.util.UserUtils;
import com.rat.snowkids.view.WheelView;

import java.util.Arrays;

public class SettingsActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.remeberMeIV)
    private ImageView remeberMeIV;
    @ViewInject(R.id.receivePushMessageIV)
    private ImageView receivePushMessageIV;
    @ViewInject(R.id.operateEnvironment)
    private WheelView operateEnvironment;
    @ViewInject(R.id.serverIpET)
    private EditText serverIpET;
    @ViewInject(R.id.logoutBtn)
    private Button logoutBtn;

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
        logoutBtn.setOnClickListener(this);
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
        // 操作环境切换
        final Environment environment = AppUtils.getInstance().getUserEnvironment();
        String[] messageList = environment.getList();
        operateEnvironment.setOffset(1);
        operateEnvironment.setItems(Arrays.asList(messageList));
        for (int i = 0; i < messageList.length; i++) {
            String str = messageList[i];
            if (environment.getCurrent().equals(str)) {
                operateEnvironment.setSeletion(i);
                break;
            }
        }
        operateEnvironment.setOnWheelViewListener(new WheelView.OnWheelViewListener() {
            @Override
            public void onSelected(int selectedIndex, String item) {
                LogUtil.i("selectedIndex: " + selectedIndex + ", item: " + item);
                environment.setCurrent(item);
                AppUtils.getInstance().setUserEnvironment4Update(environment);
            }
        });
        // 服务器IP
        serverIpET.setText(AppUtils.getInstance().getServerIp());
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
                AppUtils.getInstance().setServerIp(serverIpET.getText().toString());
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
                // 是否接收push消息
                if (AppUtils.getInstance().isReceivePushMessage())
                    PushManager.getInstance().turnOnPush(getApplicationContext());
                else
                    PushManager.getInstance().turnOffPush(getApplicationContext());
                Toast.makeText(getApplicationContext(), getString(R.string.setting_success), Toast.LENGTH_SHORT).show();
                break;
            case R.id.logoutBtn:
                UserUtils.getInstance(SettingsActivity.this).logout();
                finish();
                break;
            default:
                break;
        }
    }
}