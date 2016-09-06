package com.rat.snowkids.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.igexin.sdk.PushManager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.snowkids.snowkids.R;

public class WelcomeActivity extends Activity {
    private final int waitTime = 1000;
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
        // 个推push服务初始化
        PushManager.getInstance().initialize(this.getApplicationContext());

        initView();
        initData();
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

    public void initView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.from_y2_to_y1);
        text.startAnimation(animation);
    }

    public void initData() {
    }
}