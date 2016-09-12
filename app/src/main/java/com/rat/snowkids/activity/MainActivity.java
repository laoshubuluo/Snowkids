package com.rat.snowkids.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rat.snowkids.activity.base.BaseActivity;
import com.rat.snowkids.activity.base.WebActivity;
import com.rat.snowkids.common.Actions;
import com.rat.snowkids.entity.model.Power;
import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.DateUtil;
import com.rat.snowkids.util.LogUtil;
import com.rat.snowkids.util.MediaUtil;
import com.rat.snowkids.util.PowerUtil;
import com.rat.snowkids.util.ResourceUtil;
import com.rat.snowkids.view.CustomView;
import com.snowkids.snowkids.R;

public class MainActivity extends BaseActivity {
    private TextView topLeftView;
    private TextView topRightView;
    private RelativeLayout topLayout;
    private LinearLayout mainLL;

    private RelativeLayout powerFullRemindRL;
    private RelativeLayout theftProofRemindRL;
    private ImageView powerFullRemindIV;
    private ImageView theftProofRemindIV;
    private LinearLayout marketLL;
    private ImageView marketJDIV;
    private ImageView marketTBIV;
    private View marketLine;


    private CustomView customView;
    private TextView powerPercentTV;
    private TextView powerStatusTV;
    private TextView powerStatusTV1;
    private TextView powerStatusTV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initBroadcastReceiver();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topLeftView = (TextView) findViewById(R.id.top_left);
        topRightView = (TextView) findViewById(R.id.top_right);
        topLayout = (RelativeLayout) findViewById(R.id.top_layout);
        mainLL = (LinearLayout) findViewById(R.id.mainLL);
        powerFullRemindRL = (RelativeLayout) findViewById(R.id.powerFullRemindRL);
        theftProofRemindRL = (RelativeLayout) findViewById(R.id.theftProofRemindRL);
        powerFullRemindIV = (ImageView) findViewById(R.id.powerFullRemindIV);
        theftProofRemindIV = (ImageView) findViewById(R.id.theftProofRemindIV);
        marketLL = (LinearLayout) findViewById(R.id.marketLL);
        marketJDIV = (ImageView) findViewById(R.id.marketJDIV);
        marketTBIV = (ImageView) findViewById(R.id.marketTBIV);
        marketLine = findViewById(R.id.marketLine);
        customView = (CustomView) findViewById(R.id.customView);
        powerPercentTV = (TextView) findViewById(R.id.powerPercentTV);
        powerStatusTV = (TextView) findViewById(R.id.powerStatusTV);
        powerStatusTV1 = (TextView) findViewById(R.id.powerStatusTV1);
        powerStatusTV2 = (TextView) findViewById(R.id.powerStatusTV2);

        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        topRightView.setText("Beta版本");
        powerFullRemindRL.setOnClickListener(this);
        theftProofRemindRL.setOnClickListener(this);
        marketJDIV.setOnClickListener(this);
        marketTBIV.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
        Power power = PowerUtil.getPowerData(getApplicationContext());
        // 夜间模式
        if (AppUtils.getInstance().isNightMode() && DateUtil.isOnNight()) {
            powerStatusTV1.setText(getString(R.string.night_model_time_setting));
            powerStatusTV2.setText(getString(R.string.night_model_from_10_to_7));

            mainLL.setBackgroundColor(getResources().getColor(R.color.gray_3b));
            topLayout.setBackgroundColor(getResources().getColor(R.color.gray_61));
            powerFullRemindRL.setBackgroundColor(getResources().getColor(R.color.gray_61));
            theftProofRemindRL.setBackgroundColor(getResources().getColor(R.color.gray_61));
            marketLL.setBackgroundColor(getResources().getColor(R.color.gray_52));
            marketJDIV.setImageResource(R.mipmap.jingdong_logo_night);
            marketTBIV.setImageResource(R.mipmap.taobao_logo_night);
            marketLine.setBackgroundColor(getResources().getColor(R.color.gray_8d));
        }
        // 白天模式
        else {
            powerStatusTV1.setText(getString(R.string.power_time_out));
            powerStatusTV2.setText(power.getTimeLeft());
        }

        // TODO by L.jinzhu for。。。
//        customView.re
        powerPercentTV.setText(power.getBatteryPct());
        if (power.isCharging())
            powerStatusTV.setText(getApplication().getString(R.string.power_ing));
        else if (power.isPowerFull())
            powerStatusTV.setText(getApplication().getString(R.string.power_full));
        else
            powerStatusTV.setText(getApplication().getString(R.string.power_not));

        initRemindStatus4PowerFull();
        initRemindStatus4TheftProof();
    }

    /**
     * 初始化提醒状态
     */
    public void initRemindStatus4PowerFull() {
        // 满电提醒
        if (AppUtils.getInstance().isPowerFullRemind()) {
            powerFullRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_on));
        } else {
            powerFullRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_off));
        }
    }

    /**
     * 初始化提醒状态
     */
    public void initRemindStatus4TheftProof() {
        // 防盗提醒
        if (AppUtils.getInstance().isTheftProofRemind()) {
            theftProofRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_on));
        } else {
            theftProofRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_off));
        }
    }

    /**
     * 初始化广播
     */
    public void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Actions.POWER_FULL_REMIND_STATUS_CHANGE);
        filter.addAction(Actions.THEFT_PROOF_REMIND_STATUS_CHANGE);
        filter.addAction(Actions.NIGHT_MODEL_STATUS_CHANGE);
        filter.addAction(Actions.POWER_CONNECT_STATUS_CHANGE);
        filter.addAction(Actions.POWER_LEVEL_OK);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onResume:" + this.getClass().getSimpleName());
        initData();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.top_left:
                menu.showMenu(true);
                break;
            case R.id.powerFullRemindRL:
                // 是否满电提醒
                if (AppUtils.getInstance().isPowerFullRemind()) {
                    AppUtils.getInstance().updateIsPowerFullRemind(false);
                    powerFullRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_off));
                    MediaUtil.getInstance(getApplicationContext()).pausePF();
                } else {
                    AppUtils.getInstance().updateIsPowerFullRemind(true);
                    powerFullRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_on));
                }
                sendBroadcast(new Intent(Actions.POWER_FULL_REMIND_STATUS_CHANGE));
                break;
            case R.id.theftProofRemindRL:
                // 是否防盗提醒
                if (AppUtils.getInstance().isTheftProofRemind()) {
                    AppUtils.getInstance().updateIsTheftProofRemind(false);
                    theftProofRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_off));
                    MediaUtil.getInstance(getApplicationContext()).pauseTP();
                } else {
                    AppUtils.getInstance().updateIsTheftProofRemind(true);
                    theftProofRemindIV.setBackgroundResource(ResourceUtil.getResource(R.mipmap.settings_turn_on));
                }
                sendBroadcast(new Intent(Actions.THEFT_PROOF_REMIND_STATUS_CHANGE));
                break;
            case R.id.marketJDIV:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.INTENT_MARKET_TYPE, WebActivity.INTENT_MARKET_TYPE_JD);
                startActivity(intent);
                break;
            case R.id.marketTBIV:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.INTENT_MARKET_TYPE, WebActivity.INTENT_MARKET_TYPE_TB);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.i("receive broadcast,action:" + intent.getAction());
            // 满电提醒
            if (Actions.POWER_FULL_REMIND_STATUS_CHANGE.equals(intent.getAction())) {
                initRemindStatus4PowerFull();
            }
            // 防盗提醒
            else if (Actions.THEFT_PROOF_REMIND_STATUS_CHANGE.equals(intent.getAction())) {
                initRemindStatus4TheftProof();
            }
            // 夜间模式
            else if (Actions.NIGHT_MODEL_STATUS_CHANGE.equals(intent.getAction())) {
                // 当前是夜间 && 非夜间模式
                if (DateUtil.isOnNight() && !AppUtils.getInstance().isNightMode()) {
                    // TODO by L.jinzhu for 取消夜间模式
                    Toast.makeText(getApplicationContext(), "取消夜间模式功能，待开发", Toast.LENGTH_SHORT).show();
                }
            }
            // 连接状态变更
            else if (Actions.POWER_CONNECT_STATUS_CHANGE.equals(intent.getAction())) {
                Power power = PowerUtil.getPowerData(getApplicationContext());
                // 开始充电
                if (power.isCharging()) {
                    powerStatusTV.setText(getApplication().getString(R.string.power_ing));
                }
                // 拔掉电源
                else {
                    powerStatusTV.setText(getApplication().getString(R.string.power_not));
                }
            }
            // 电池等级变更-OK
            else if (Actions.POWER_LEVEL_OK.equals(intent.getAction())) {
                // TODO by L.jinzhu for纠正100%数据
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        MediaUtil.getInstance(getApplication()).stop();
    }
}