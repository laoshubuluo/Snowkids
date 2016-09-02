package com.rat.snowkids.view.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.snowkids.snowkids.R;


/**
 * @author shisheng.zhao
 * @Description: 自定义ProgressDialog
 * @date 2015-09-06 下午17:57:26
 */
public class CustomProgressDialog extends ProgressDialog {
    private Context context;
    private String text;

    public CustomProgressDialog(Context context, String text) {
        super(context);
        this.context = context;
        this.text = text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.color.translate);//背景
        setContentView(R.layout.loading);
//        loadingText = (TextView) findViewById(R.id.tv_loading);
//        loadingText.setText(text);
    }
}
