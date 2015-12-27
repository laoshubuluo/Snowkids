package com.rat.nm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2015/8/16
 * introduce : 参数信息
 */
public class AlarmInfoView extends LinearLayout {

    private Context context;

    @ViewInject(R.id.iconIV)
    private ImageView iconIV;
    @ViewInject(R.id.contentTV)
    private TextView contentTV;
    @ViewInject(R.id.timeTV)
    private TextView timeTV;

    public AlarmInfoView(Context c) {
        super(c);
        this.context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_alarm_info, this);

        //基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
    }


    /**
     * 初始化界面
     */
    public void initView() {
    }

    /**
     * 初始化数据
     */
    public void initData(String content, int status, String time) {
        int resourceId = 0;
        if (RunningAlarmListItemView.LEVEL_1 == status)
            resourceId = R.mipmap.alarm_detail_warn1;
        else if (RunningAlarmListItemView.LEVEL_2 == status)
            resourceId = R.mipmap.alarm_detail_warn2;
        else if (RunningAlarmListItemView.LEVEL_3 == status)
            resourceId = R.mipmap.alarm_detail_warn3;
        else if (RunningAlarmListItemView.LEVEL_4 == status)
            resourceId = R.mipmap.alarm_detail_warn4;

        contentTV.setText(content);
        timeTV.setText(time);
        iconIV.setBackgroundResource(resourceId);
    }

    /**
     * 焦点事件
     */
    OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
//                if (i != null)
//                    context.startActivity(i);
//            } else
//                itemFL.setBackgroundResource(R.mipmap.menu_circle_border);
        }
    };
}