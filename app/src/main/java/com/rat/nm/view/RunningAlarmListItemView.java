package com.rat.nm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2015/8/16
 * introduce : 运行设备、告警条目
 */
public class RunningAlarmListItemView extends RelativeLayout {
    //Running
    public static final int NORMAL = 5;
    public static final int STOP = 6;
    public static final int ERROR = 7;
    //Alarm
    public static final int LEVEL_1 = 1;
    public static final int LEVEL_2 = 2;
    public static final int LEVEL_3 = 3;
    public static final int LEVEL_4 = 4;


    private Context context;
    @ViewInject(R.id.indexBtn)
    private Button indexBtn;
    @ViewInject(R.id.nameTV)
    private TextView nameTV;
    @ViewInject(R.id.statusTV)
    private TextView statusTV;

    public RunningAlarmListItemView(Context c) {
        super(c);
        this.context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_running_alarm_list_item, this);

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
    public void initData(int index, String name, int status) {
        String statusStr = "";
        int color = 0;
        if (NORMAL == status) {
            statusStr = "NORMAL";
            color = R.color.blue;
        } else if (STOP == status) {
            statusStr = "STOP";
            color = R.color.gray;
        } else if (ERROR == status) {
            statusStr = "ERROR";
            color = R.color.red;
        } else if (LEVEL_1 == status) {
            statusStr = "LEVEL_1";
            color = R.color.red;
        } else if (LEVEL_2 == status) {
            statusStr = "LEVEL_2";
            color = R.color.blue;
        } else if (LEVEL_3 == status) {
            statusStr = "LEVEL_3";
            color = R.color.gray;
        } else if (LEVEL_4 == status) {
            statusStr = "LEVEL_4";
            color = R.color.white;
        }

        indexBtn.setText(index + "");
        nameTV.setText(name);
        statusTV.setText(statusStr);
        statusTV.setTextColor(context.getResources().getColor(color));
    }
}