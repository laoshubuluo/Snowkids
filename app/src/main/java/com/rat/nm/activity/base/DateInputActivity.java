package com.rat.nm.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.util.DateUtil;
import com.rat.nm.util.StringUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * author : L.jinzhu
 * date : 2015/8/17
 * introduce : 日期输入界面
 */
public class DateInputActivity extends BaseActivity {
    @ViewInject(R.id.top_name)
    private TextView topTitleView;
    @ViewInject(R.id.top_left)
    private TextView topLeftView;

    @ViewInject(R.id.dateTV)
    private TextView dateTV;
    @ViewInject(R.id.datePicker)
    private DatePicker datePicker;

    private String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_input);
        date = getIntent().getStringExtra("date");
        // 基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
        initView();
        initData();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        topTitleView.setText(R.string.date);
        topLeftView.setVisibility(View.VISIBLE);
        topLeftView.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        int yearInt;
        int monthInt;
        int dayInt;
        // 回显
        dateTV.setText(date);
        Calendar c = Calendar.getInstance();
        // 日期为空，回显当前时间
        if (StringUtils.isNullOrBlank(date)) {
            c.setTime(new Date());
        }
        // 日期不为空，回显正常时间
        else {
            Date d = DateUtil.stringToDate(date, "yyyy-MM-dd HH:mm:ss");
            c.setTime(d);
        }
        yearInt = c.get(Calendar.YEAR);
        monthInt = c.get(Calendar.MONTH);
        dayInt = c.get(Calendar.DAY_OF_MONTH);
        datePicker.init(yearInt, monthInt, dayInt, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 获取一个日历对象，并初始化为当前选中的时间
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                date = DateUtil.dateToString(calendar.getTime());
                dateTV.setText(date);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_left:
                comitChange();
                break;
            default:
                break;
        }
    }

    private void comitChange() {
        Intent i = new Intent();
        i.putExtra("date", date);
        setResult(0, i);
        finish();
    }
}