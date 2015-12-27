package com.rat.nm.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2015/8/16
 * introduce : 参数条目
 */
public class ParameterListItemView extends LinearLayout {

    private Context context;

    @ViewInject(R.id.keyTV)
    private TextView keyTV;
    @ViewInject(R.id.desTV)
    private TextView desTV;
    @ViewInject(R.id.valueTV)
    private TextView valueTV;

    public ParameterListItemView(Context c) {
        super(c);
        this.context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_parameter_list_item, this);

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
    public void initData(int index, String key, String des, String value) {
        keyTV.setText(key);
        desTV.setText(des);
        valueTV.setText(value);
    }

    /**
     * 焦点事件
     */
    OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
//            if (hasFocus) {
//                itemFL.setBackgroundResource(R.mipmap.menu_circle_blue);
//                Intent i = null;
//                switch (itemType) {
//                    case PROFILE:
//                        Toast.makeText(context, context.getString(R.string.function_unavailable), Toast.LENGTH_SHORT).show();
//                        //i = new Intent(context, Pr.class);
//                        break;
//                    case PARAMETER:
//                        i = new Intent(context, ParameterListActivity.class);
//                        break;
//                    case ALARM_INFO:
//                        i = new Intent(context, AlarmListActivity.class);
//                        break;
//                    case OPERTION_LOG:
//                        i = new Intent(context, OperationLogFilterActivity.class);
//                        break;
//                           case RUNNING_STATE:
//                        i = new Intent(context, RunningListActivity.class);
//                        break;
//                    case SETTINGS:
//                        i = new Intent(context, SettingsActivity.class);
//                        break;
//                }
//                if (i != null)
//                    context.startActivity(i);
//            } else
//                itemFL.setBackgroundResource(R.mipmap.menu_circle_border);
        }
    };
}