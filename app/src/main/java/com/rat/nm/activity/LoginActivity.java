package com.rat.nm.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

public class LoginActivity extends BaseActivity {


    @ViewInject(R.id.signInBtn)
    private Button signInBtn;
    @ViewInject(R.id.userNameET)
    private EditText userNameET;
    @ViewInject(R.id.passwordET)
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件

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
//        if ("".equals(userNameET.getText().toString())) {
//            Toast.makeText(getApplicationContext(), getString(R.string.user_name_null), Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if ("".equals(passwordET.getText().toString())) {
//            Toast.makeText(getApplicationContext(), getString(R.string.password_null), Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        Toast.makeText(getApplicationContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();
//        Intent i = new Intent(LoginActivity.this, MenuActivity.class);
//        startActivity(i);
    }
}