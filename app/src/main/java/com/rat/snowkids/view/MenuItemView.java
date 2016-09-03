package com.rat.snowkids.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.snowkids.snowkids.R;
import com.rat.snowkids.activity.AlarmFilterActivity;
import com.rat.snowkids.activity.DeviceListActivity;
import com.rat.snowkids.activity.DeviceTypeListActivity;
import com.rat.snowkids.activity.OperateLogFilterActivity;
import com.rat.snowkids.activity.SettingsActivity;

/**
 * author : L.jinzhu
 * date : 2015/8/16
 * introduce : 菜单条目
 */
public class MenuItemView extends FrameLayout {
    public static final int PROFILE = 1;
    public static final int DEVICE_TYPE = 2;
    public static final int DEVICE = 3;
    public static final int ALARM = 4;
    public static final int OPERATE_LOG = 5;
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

    private SlidingMenu menu;

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
                title = context.getString(R.string.profile);
                resouceId = R.mipmap.menu_user;
                break;
            case DEVICE_TYPE:
                title = context.getString(R.string.device);
                resouceId = R.mipmap.menu_articles;
                break;
            case DEVICE:
                title = context.getString(R.string.device);
                resouceId = R.mipmap.menu_device;
                break;
            case OPERATE_LOG:
                title = context.getString(R.string.operate_log);
                resouceId = R.mipmap.menu_events;
                break;
            case ALARM:
                title = context.getString(R.string.alarm);
                resouceId = R.mipmap.menu_chat;
                break;
            case SETTINGS:
                title = context.getString(R.string.settings);
                resouceId = R.mipmap.menu_equalizer;
                break;
        }
        iconIV.setBackgroundResource(resouceId);
        titleTV.setText(title);
        itemFL.setOnTouchListener(onTouchListener);
    }

    /**
     * 初始化数据
     */
    public void initData(int messageNumber) {
        if (messageNumber > 0) {
            messageBtn.setText(String.valueOf(messageNumber));
            messageBtn.setVisibility(VISIBLE);
        } else {
            messageBtn.setVisibility(INVISIBLE);
        }
    }

    /**
     * 初始化数据
     */
    public void initClickView(SlidingMenu menu) {
        this.menu = menu;
    }

    /**
     * 点击事件
     */
    OnTouchListener onTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                itemFL.setBackgroundResource(R.mipmap.menu_articles);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                itemFL.setBackgroundResource(R.mipmap.menu_circle_border);

                Intent i = null;
                switch (itemType) {
                    case PROFILE:
                        menu.showMenu();// 开启侧边栏
                        break;
                    case DEVICE_TYPE:
                        i = new Intent(context, DeviceTypeListActivity.class);
                        break;
                    case DEVICE:
                        i = new Intent(context, DeviceListActivity.class);
                        break;
                    case ALARM:
                        i = new Intent(context, AlarmFilterActivity.class);
                        break;
                    case OPERATE_LOG:
                        i = new Intent(context, OperateLogFilterActivity.class);
                        break;
                    case SETTINGS:
                        i = new Intent(context, SettingsActivity.class);
                        break;
                }
                if (i != null)
                    context.startActivity(i);
            }
            return false;
        }
    };
}