package com.rat.nm.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.rat.networkmanager.R;
import com.rat.nm.activity.AlarmFilterActivity;
import com.rat.nm.activity.OperationLogFilterActivity;
import com.rat.nm.activity.ParameterListActivity;
import com.rat.nm.activity.RunningListActivity;
import com.rat.nm.activity.SettingsActivity;

/**
 * author : L.jinzhu
 * date : 2015/8/16
 * introduce : 菜单条目
 */
public class MenuItemView extends FrameLayout {

    public static final int PROFILE = 1;
    public static final int PARAMETER = 2;
    public static final int ALARM_INFO = 3;
    public static final int OPERTION_LOG = 4;
    public static final int RUNNING_STATE = 5;
    public static final int SETTINGS = 6;

    private Context context;
    private int itemType;
    @ViewInject(R.id.itemFL)
    private FrameLayout itemFL;
    @ViewInject(R.id.iconIV)
    private ImageView iconIV;
    @ViewInject(R.id.messageBtn)
    private Button messageBtn;
    @ViewInject(R.id.titleTV)
    private TextView titleTV;

    public MenuItemView(Context c, AttributeSet attrs) {
        super(c, attrs);
        this.context = c;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.view_menu_item, this);

        //基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件
    }


    /**
     * 初始化界面
     */
    public void initView(int type) {
        itemType = type;
        String title = "";
        int resouceId = 0;
        switch (type) {
            case PROFILE:
                title = "Profile";
                resouceId = R.mipmap.menu_user;
                break;
            case PARAMETER:
                title = "Parameter";
                resouceId = R.mipmap.menu_articles;
                break;
            case ALARM_INFO:
                title = "Alarm Info";
                resouceId = R.mipmap.menu_mail;
                break;
            case OPERTION_LOG:
                title = "Operation Log";
                resouceId = R.mipmap.menu_chat;
                break;
            case RUNNING_STATE:
                title = "Running State";
                resouceId = R.mipmap.menu_events;
                break;
            case SETTINGS:
                title = "Settings";
                resouceId = R.mipmap.menu_equalizer;
                break;
        }
        iconIV.setBackgroundResource(resouceId);
        titleTV.setText(title);
        itemFL.setOnFocusChangeListener(focusChangeListener);
    }

    /**
     * 初始化数据
     */
    public void initData(int messageNumber) {
        if (messageNumber > 0) {
            messageBtn.setText(messageNumber + "");
            messageBtn.setVisibility(VISIBLE);
        } else {
            messageBtn.setVisibility(INVISIBLE);
        }
    }

    /**
     * 焦点事件
     */
    OnFocusChangeListener focusChangeListener = new OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus) {
                itemFL.setBackgroundResource(R.mipmap.menu_circle_blue);
                Intent i = null;
                switch (itemType) {
                    case PROFILE:
                        Toast.makeText(context, context.getString(R.string.function_unavailable), Toast.LENGTH_SHORT).show();
                        //i = new Intent(context, Pr.class);
                        break;
                    case PARAMETER:
                        i = new Intent(context, ParameterListActivity.class);
                        break;
                    case ALARM_INFO:
                        i = new Intent(context, AlarmFilterActivity.class);
                        break;
                    case OPERTION_LOG:
                        i = new Intent(context, OperationLogFilterActivity.class);
                        break;
                    case RUNNING_STATE:
                        i = new Intent(context, RunningListActivity.class);
                        break;
                    case SETTINGS:
                        i = new Intent(context, SettingsActivity.class);
                        break;
                }
                if (i != null)
                    context.startActivity(i);
            } else
                itemFL.setBackgroundResource(R.mipmap.menu_circle_border);
        }
    };
}