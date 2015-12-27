package com.rat.nm.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2015/8/17
 * introduce : 等待提示框
 */
public class CustomProgressDialog extends ProgressDialog {
    public CustomProgressDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.translate);//背景
        setContentView(R.layout.loading);
    }
}
