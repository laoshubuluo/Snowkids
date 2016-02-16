package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.igexin.sdk.PushManager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;
import com.rat.nm.util.StringUtils;
import com.rat.nm.view.MenuItemView;

public class MainActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.searchET)
    private TextView searchET;
    @ViewInject(R.id.profileMIV)
    private MenuItemView profileMIV;
    @ViewInject(R.id.deviceTypeMIV)
    private MenuItemView deviceTypeMIV;
    @ViewInject(R.id.deviceMIV)
    private MenuItemView deviceMIV;
    @ViewInject(R.id.alarmMIV)
    private MenuItemView alarmMIV;
    @ViewInject(R.id.operateLogMIV)
    private MenuItemView operateLogMIV;
    @ViewInject(R.id.settingsMIV)
    private MenuItemView settingsMIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        PushManager.getInstance().initialize(this.getApplicationContext());//个推push服务初始化
        Toast.makeText(getApplicationContext(), "push service init: " + PushManager.getInstance().getClientid(this.getApplicationContext()), Toast.LENGTH_LONG).show();
        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView.setText(R.string.menu);

        profileMIV.initView(MenuItemView.PROFILE);
        //profileMIV.initData(0);
        profileMIV.initClickView(menu);
        deviceTypeMIV.initView(MenuItemView.DEVICE_TYPE);
        //deviceTypeMIV.initData(1);
        deviceMIV.initView(MenuItemView.DEVICE);
        //deviceMIV.initData(3);
        alarmMIV.initView(MenuItemView.ALARM);
        //alarmMIV.initData(1);
        operateLogMIV.initView(MenuItemView.OPERATE_LOG);
        //operateLogMIV.initData(1);
        settingsMIV.initView(MenuItemView.SETTINGS);
        //settingsMIV.initData(99);

        searchET.setOnEditorActionListener(actionListener);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    private TextView.OnEditorActionListener actionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_SEARCH || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String key = searchET.getText().toString().trim();
                if (StringUtils.isNullOrBlank(key)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.device_name_is_null), Toast.LENGTH_SHORT).show();
                    return true;
                }
                Intent i = new Intent(MainActivity.this, DeviceListActivity.class);
                i.putExtra("deviceName", key);
                startActivity(i);
                return true;
            }
            return false;
        }
    };
}