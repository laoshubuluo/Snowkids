package com.rat.snowkids.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rat.snowkids.activity.base.BaseActivity;
import com.rat.snowkids.activity.base.WebActivity;
import com.rat.snowkids.common.Actions;
import com.rat.snowkids.common.Constant;
import com.rat.snowkids.common.MessageSignConstant;
import com.rat.snowkids.entity.model.Power;
import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.LogUtil;
import com.rat.snowkids.util.MediaUtil;
import com.rat.snowkids.util.PowerUtil;
import com.snowkids.snowkids.R;

public class MainActivity extends BaseActivity implements Handler.Callback {
    private TextView topLeftView;
    private TextView topRightView;

    private RelativeLayout powerFullRemindRL;
    private RelativeLayout theftProofRemindRL;
    private ImageView powerFullRemindIV;
    private ImageView theftProofRemindIV;
    private ImageView marketJDIV;
    private ImageView marketTBIV;
    private TextView powerStatusTV1;
    private TextView powerStatusTV2;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initBroadcastReceiver();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        handler = new Handler(this);
        Constant.handlerInMainActivity = handler;
        topLeftView = (TextView) findViewById(R.id.top_left);
        topRightView = (TextView) findViewById(R.id.top_right);
        powerFullRemindRL = (RelativeLayout) findViewById(R.id.powerFullRemindRL);
        theftProofRemindRL = (RelativeLayout) findViewById(R.id.theftProofRemindRL);
        powerFullRemindIV = (ImageView) findViewById(R.id.powerFullRemindIV);
        theftProofRemindIV = (ImageView) findViewById(R.id.theftProofRemindIV);
        marketJDIV = (ImageView) findViewById(R.id.marketJDIV);
        marketTBIV = (ImageView) findViewById(R.id.marketTBIV);
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
        // 白天模式
        powerStatusTV1.setText(getString(R.string.power_time_out));
        powerStatusTV2.setText(power.getTimeLeft());
        // 夜间模式
    }

    /**
     * 初始化广播
     */
    public void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Actions.POWER_FULL_REMIND_STATUS_CHANGE);
        filter.addAction(Actions.THEFT_PROOF_REMIND_STATUS_CHANGE);
        filter.addAction(Actions.NIGHT_MODEL_STATUS_CHANGE);
        registerReceiver(receiver, filter);
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
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsPowerFullRemind(true);
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                }
                sendBroadcast(new Intent(Actions.POWER_FULL_REMIND_STATUS_CHANGE));
                break;
            case R.id.theftProofRemindRL:
                // 是否防盗提醒
                if (AppUtils.getInstance().isTheftProofRemind()) {
                    AppUtils.getInstance().updateIsTheftProofRemind(false);
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsTheftProofRemind(true);
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
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
                if (AppUtils.getInstance().isPowerFullRemind()) {
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                } else {
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                }
            }
            // 防盗提醒
            else if (Actions.THEFT_PROOF_REMIND_STATUS_CHANGE.equals(intent.getAction())) {
                if (AppUtils.getInstance().isTheftProofRemind()) {
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                } else {
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                }
            }
            // 夜间模式
            else if (Actions.NIGHT_MODEL_STATUS_CHANGE.equals(intent.getAction())) {
                if (AppUtils.getInstance().isNightMode()) {
                    // TODO by L.jinzhu
                } else {
                    // TODO by L.jinzhu
                }
            }
        }
    };

    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case MessageSignConstant.POWER_CONNECTION_STATUS:
                Power power = (Power) msg.getData().getSerializable("power");
                // 开始充电
                if (power.isCharging()) {
                    Toast.makeText(getApplicationContext(), "开始充电，修改上面的进度", Toast.LENGTH_LONG).show();
                }
                // 拔掉电源
                else {
                    Toast.makeText(getApplicationContext(), "拔掉电源，修改上面的进度", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        MediaUtil.getInstance(getApplication()).stop();
    }
}