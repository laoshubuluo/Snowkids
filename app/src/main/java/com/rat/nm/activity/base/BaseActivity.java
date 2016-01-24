package com.rat.nm.activity.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rat.networkmanager.R;
import com.rat.nm.fragment.MenuLeftFragment;
import com.rat.nm.fragment.MenuRightFragment;
import com.rat.nm.view.dialog.CustomProgressDialog;
import com.rat.nm.view.dialog.PromptDialog;

/**
 * author : L.jinzhu
 * date : 2015/8/28
 * introduce : 基础界面
 */
public class BaseActivity extends SlidingFragmentActivity implements Handler.Callback, View.OnClickListener {
    public Handler handler;
    public CustomProgressDialog customProgressDialog;
    public PromptDialog promptDialog;
    public ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 统一去掉标题栏
        super.onCreate(savedInstanceState);
        handler = new Handler(this);
        promptDialog = new PromptDialog(BaseActivity.this);
        imageLoader = ImageLoader.getInstance();

        // 加载滑动菜单
        setBehindContentView(R.layout.layout_default_menu_left);
        SlidingMenu menu = getSlidingMenu();
        menu.setMode(SlidingMenu.LEFT_RIGHT);// 左右滑动菜单激活
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        menu.setShadowWidthRes(R.dimen.shadow_width);
        menu.setShadowDrawable(R.drawable.menu_shadow_left);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.setFadeDegree(0.35f);
        menu.setSecondaryShadowDrawable(R.drawable.menu_shadow_right);
        menu.setSecondaryMenu(R.layout.layout_default_menu_right);
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_default_menu_left, new MenuLeftFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_default_menu_right, new MenuRightFragment()).commit();
    }

    /**
     * 设置竖屏
     */
    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

    @Override
    public void onClick(View v) {
    }

    @Override
    protected void onDestroy() {
        if (null != customProgressDialog) {
            customProgressDialog.dismiss();
        }
        if (null != promptDialog) {
            promptDialog.dismiss();
        }
        super.onDestroy();
    }


}