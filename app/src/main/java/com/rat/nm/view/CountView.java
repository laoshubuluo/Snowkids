package com.rat.nm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2015/8/16
 * introduce : 数量视图
 */
public class CountView extends LinearLayout {
    private Context context;
    private String title;
    private String count;
    private TextView titleTV;
    private TextView countTV;

    public CountView(final Context context, String title, String count) {
        super(context);
        this.context = context;
        this.title = title;
        this.count = count;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_count, this);
        titleTV = (TextView) findViewById(R.id.titleTV);
        countTV = (TextView) findViewById(R.id.countTV);
        titleTV.setText(title);
        countTV.setText(count);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setBackgroundColor(context.getResources().getColor(R.color.white));
                        break;
                    case MotionEvent.ACTION_MOVE:
                        setBackgroundColor(context.getResources().getColor(R.color.white));
                        break;
                    case MotionEvent.ACTION_UP:
                        setBackgroundColor(0);
                        break;
                    default:
                        setBackgroundColor(0);
                        break;
                }
                return false;
            }
        });
    }
}
