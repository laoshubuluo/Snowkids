package com.rat.nm.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.rat.networkmanager.R;
import com.rat.nm.util.StringUtils;

/**
 * author : L.jinzhu
 * date : 2015/8/17
 * introduce : 提示框
 */
public class PromptDialog extends Dialog {

    private TextView titleTV;
    private TextView contentTV;
    private TextView sureTV;

    public PromptDialog(Context context) {
        super(context, R.style.promptDialog);
        setContentView(R.layout.view_dialog_prompt);
        titleTV = (TextView) findViewById(R.id.titleTV);
        contentTV = (TextView) findViewById(R.id.contentTV);
        sureTV = (TextView) findViewById(R.id.sureTV);
        sureTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptDialog.this.dismiss();
            }
        });
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void initData(String title, String content) {
        if (!StringUtils.isNullOrBlank(title)) {
            titleTV.setText(title);
            titleTV.setVisibility(View.VISIBLE);
        }
        contentTV.setText(content);
        contentTV.setVisibility(View.VISIBLE);
    }
}
