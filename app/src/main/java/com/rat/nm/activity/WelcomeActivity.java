package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

public class WelcomeActivity extends BaseActivity {

    @ViewInject(R.id.signInBtn)
    private Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件

//        DBBase.getInstance(getApplication()).dbCheck();// 数据库检查

        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
//        String url = "http://image.photophoto.cn/nm-6/018/030/0180300244.jpg";
//        imageLoader.displayImage(url, iv, ImageUtil.getImageOptions());
    }

    /**
     * 初始化数据
     */
    public void initData() {
    }

    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
//        switch (msg.what) {
//            case MessageSignConstant.DEMO:
//                Demo demo = (Demo) msg.getData().getSerializable("demo");
////                tv.setText(demo.getName());
//        }
        return false;
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.signInBtn)
    public void signInBtnOnClick(View v) {
//        DemoService ds = new DemoService(getApplicationContext(), handler);
//        ds.testQuery();
//
//        DBDemo dbDemo = new DBDemo(getApplication());
//        dbDemo.save();
//        List<Demo> demoList = dbDemo.getAll();
//        for (Demo d : demoList)
//            LogUtil.e(d.getName() + "||" + d.getPassword());
        Intent i = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(i);
    }

//    /**
//     * 点击事件
//     */
//    @OnClick(R.id.registerBtn)
//    public void registerBtnOnClick(View v) {
//        Toast.makeText(getApplicationContext(), getString(R.string.function_unavailable), Toast.LENGTH_SHORT).show();
//    }
}