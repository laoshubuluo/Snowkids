package com.rat.nm.activity.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;

import com.lidroid.xutils.ViewUtils;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rat.nm.view.dialog.CustomProgressDialog;
import com.rat.nm.view.dialog.PromptDialog;

/**
 * author : L.jinzhu
 * date : 2015/8/28
 * introduce : 基础界面
 */
public class BaseActivity extends Activity implements Handler.Callback, View.OnClickListener {
    public Handler handler;
    public CustomProgressDialog customProgressDialog;
    public PromptDialog promptDialog;
    public ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 统一去掉标题栏
        super.onCreate(savedInstanceState);
        handler = new Handler(this);
        promptDialog = new PromptDialog(BaseActivity.this);
        imageLoader = ImageLoader.getInstance();
    }

    /**
     * 设置竖屏
     */
    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        if (null != customProgressDialog) {
            customProgressDialog.dismiss();
        }
        if (null != promptDialog) {
            promptDialog.dismiss();
        }
        super.onDestroy();
    }


}