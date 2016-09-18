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

import com.ethanco.circleprogresslibrary.CircleProgress;
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
import com.snowkids.snowkids.R;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

public class MainActivity extends BaseActivity {
    private TextView topLeftView;
    private RelativeLayout topLayout;
    private LinearLayout remindLL;
    private LinearLayout powerDataLL;
    private RelativeLayout powerFullRemindRL;
    private RelativeLayout theftProofRemindRL;
    private ImageView powerFullRemindIV;
    private ImageView theftProofRemindIV;
    private LinearLayout marketLL;
    private ImageView marketJDIV;
    private ImageView marketTBIV;
    private View marketLine;


    private CircleProgress circleProgress;
    private TextView powerPercentTV;
    private TextView powerStatusTV;
    private TextView powerStatusTV1;
    private TextView powerStatusTV2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化友盟推送
        PushAgent.getInstance(this).onAppStart();

        initView();
        initBroadcastReceiver();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topLeftView = (TextView) findViewById(R.id.top_left);
        topLayout = (RelativeLayout) findViewById(R.id.top_layout);
        powerDataLL = (LinearLayout) findViewById(R.id.powerDataLL);
        remindLL = (LinearLayout) findViewById(R.id.remindLL);
        powerFullRemindRL = (RelativeLayout) findViewById(R.id.powerFullRemindRL);
        theftProofRemindRL = (RelativeLayout) findViewById(R.id.theftProofRemindRL);
        powerFullRemindIV = (ImageView) findViewById(R.id.powerFullRemindIV);
        theftProofRemindIV = (ImageView) findViewById(R.id.theftProofRemindIV);
        marketLL = (LinearLayout) findViewById(R.id.marketLL);
        marketJDIV = (ImageView) findViewById(R.id.marketJDIV);
        marketTBIV = (ImageView) findViewById(R.id.marketTBIV);
        marketLine = findViewById(R.id.marketLine);
        circleProgress = (CircleProgress) findViewById(R.id.circleProgress);
        powerPercentTV = (TextView) findViewById(R.id.powerPercentTV);
        powerStatusTV = (TextView) findViewById(R.id.powerStatusTV);
        powerStatusTV1 = (TextView) findViewById(R.id.powerStatusTV1);
        powerStatusTV2 = (TextView) findViewById(R.id.powerStatusTV2);

        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
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
        // 夜晚模式
        if (AppUtils.getInstance().isNightMode() && DateUtil.isOnNight()) {
            initData4Night();
        }
        // 白天模式
        else {
            initData4Day(power);
        }

        circleProgress.setProgress(power.getBatteryPct());
        powerPercentTV.setText(power.getBatteryPct() + "%");
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
     * 初始化数据-白天
     */
    public void initData4Day(Power power) {
        // 内容不同
        powerStatusTV1.setText(getString(R.string.power_time_out));
        powerStatusTV2.setText(power.getTimeLeft());
        // 背景色不同
        topLayout.setBackgroundColor(getResources().getColor(R.color.white_f8));
        powerDataLL.setBackgroundColor(getResources().getColor(R.color.white_f2));
        remindLL.setBackgroundColor(getResources().getColor(R.color.white_ed));
        marketLL.setBackgroundColor(getResources().getColor(R.color.gray_c3));
        marketJDIV.setImageResource(R.mipmap.jingdong_logo);
        marketTBIV.setImageResource(R.mipmap.taobao_logo);
        marketLine.setBackgroundColor(getResources().getColor(R.color.gray_8f));
    }

    /**
     * 初始化数据-夜晚
     */
    public void initData4Night() {
        // 内容不同
        powerStatusTV1.setText(getString(R.string.night_model_time_setting));
        powerStatusTV2.setText(getString(R.string.night_model_from_10_to_7));
        // 背景色不同
        topLayout.setBackgroundColor(getResources().getColor(R.color.gray_61));
        powerDataLL.setBackgroundColor(getResources().getColor(R.color.gray_3b));
        remindLL.setBackgroundColor(getResources().getColor(R.color.gray_46));
        marketLL.setBackgroundColor(getResources().getColor(R.color.gray_52));
        marketJDIV.setImageResource(R.mipmap.jingdong_logo_night);
        marketTBIV.setImageResource(R.mipmap.taobao_logo_night);
        marketLine.setBackgroundColor(getResources().getColor(R.color.gray_8f));
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
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
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
            Power power = PowerUtil.getPowerData(getApplicationContext());
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
                    initData4Day(power);
                } else if (DateUtil.isOnNight()) {
                    initData4Night();
                }
                initRemindStatus4PowerFull();
                initRemindStatus4TheftProof();
            }
            // 连接状态变更
            else if (Actions.POWER_CONNECT_STATUS_CHANGE.equals(intent.getAction())) {
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
                circleProgress.setProgress(100);
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