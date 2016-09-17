package com.rat.snowkids.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.snowkids.service.MonitorService;
import com.snowkids.snowkids.R;
import com.umeng.analytics.MobclickAgent;

public class WelcomeActivity extends Activity {
    private final int waitTime = 4000;
    @ViewInject(R.id.logo)
    private ImageView logo;
    @ViewInject(R.id.text)
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件

        initService();
        initView();
        initData();




    }

    public void initView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.from_y2_to_y1);
        text.startAnimation(animation);
    }

    public void initData() {
        // 定时跳转
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WelcomeActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, waitTime);
    }

    public void initService() {
        Intent intent = new Intent(WelcomeActivity.this, MonitorService.class);
        startService(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}