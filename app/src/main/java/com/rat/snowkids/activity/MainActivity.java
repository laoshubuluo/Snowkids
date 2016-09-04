package com.rat.snowkids.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.rat.snowkids.activity.base.BaseActivity;
import com.rat.snowkids.activity.base.WebActivity;
import com.rat.snowkids.util.AppUtils;
import com.snowkids.snowkids.R;

public class MainActivity extends BaseActivity {
    private TextView topTitleView;
    private TextView topLeftView;

    private RelativeLayout powerFullRemindRL;
    private RelativeLayout theftProofRemindRL;
    private ImageView powerFullRemindIV;
    private ImageView theftProofRemindIV;
    private TextView marketJDTV;
    private TextView marketTBTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    public void initView() {
        topTitleView = (TextView) findViewById(R.id.top_name);
        topLeftView = (TextView) findViewById(R.id.top_left);
        powerFullRemindRL = (RelativeLayout) findViewById(R.id.powerFullRemindRL);
        theftProofRemindRL = (RelativeLayout) findViewById(R.id.theftProofRemindRL);
        powerFullRemindIV = (ImageView) findViewById(R.id.powerFullRemindIV);
        theftProofRemindIV = (ImageView) findViewById(R.id.theftProofRemindIV);
        marketJDTV = (TextView) findViewById(R.id.marketJDTV);
        marketTBTV = (TextView) findViewById(R.id.marketTBTV);

        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
        powerFullRemindRL.setOnClickListener(this);
        theftProofRemindRL.setOnClickListener(this);
        marketJDTV.setOnClickListener(this);
        marketTBTV.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.top_left:
                Toast.makeText(getApplicationContext(), "打开侧边栏", Toast.LENGTH_LONG).show();
                break;
            case R.id.powerFullRemindRL:
                // 是否满电提醒
                if (AppUtils.getInstance().isPowerFullRemind()) {
                    AppUtils.getInstance().updateIsPowerFullRemind(false);
                    powerFullRemindIV.setBackgroundResource(R.mipmap.circle_red);
                } else {
                    AppUtils.getInstance().updateIsPowerFullRemind(true);
                    powerFullRemindIV.setBackgroundResource(R.mipmap.circle_green);
                }
                Toast.makeText(getApplicationContext(), getString(R.string.setting_success), Toast.LENGTH_SHORT).show();
                break;
            case R.id.theftProofRemindRL:
                // 是否防盗提醒
                if (AppUtils.getInstance().isTheftProofRemind()) {
                    AppUtils.getInstance().updateIsTheftProofRemind(false);
                    theftProofRemindIV.setBackgroundResource(R.mipmap.circle_red);
                } else {
                    AppUtils.getInstance().updateIsTheftProofRemind(true);
                    theftProofRemindIV.setBackgroundResource(R.mipmap.circle_green);
                }
                Toast.makeText(getApplicationContext(), getString(R.string.setting_success), Toast.LENGTH_SHORT).show();
                break;
            case R.id.marketJDTV:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.INTENT_MARKET_TYPE, WebActivity.INTENT_MARKET_TYPE_JD);
                startActivity(intent);
                break;
            case R.id.marketTBTV:
                intent = new Intent(MainActivity.this, WebActivity.class);
                intent.putExtra(WebActivity.INTENT_MARKET_TYPE, WebActivity.INTENT_MARKET_TYPE_TB);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}