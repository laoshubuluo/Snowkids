package com.rat.snowkids.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rat.snowkids.activity.base.WebActivity;
import com.rat.snowkids.common.Actions;
import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.LogUtil;
import com.snowkids.snowkids.R;

import java.io.File;

/**
 * author : L.jinzhu
 * date : 2016/1/24
 * introduce : 滑动菜单界面
 */
public class MenuRightFragment extends Fragment implements View.OnClickListener {
    private ImageView iconIV;
    private ImageView powerFullRemindIV;
    private ImageView theftProofRemindIV;
    private ImageView lightModelIV;
    private RelativeLayout shareAppRL;
    private TextView marketJDTV;
    private TextView marketTBTV;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 防止onCreateView被多次调用
        if (null != view) {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (null != parent)
                parent.removeView(view);
        } else {
            view = inflater.inflate(R.layout.fragment_menu_right, null);
            initView(view);
            initData();
            initBroadcastReceiver();
        }
        return view;
    }


    /**
     * 初始化界面
     */
    private void initView(View view) {
        iconIV = (ImageView) view.findViewById(R.id.iconIV);
        powerFullRemindIV = (ImageView) view.findViewById(R.id.powerFullRemindIV);
        theftProofRemindIV = (ImageView) view.findViewById(R.id.theftProofRemindIV);
        lightModelIV = (ImageView) view.findViewById(R.id.nightModelIV);
        shareAppRL = (RelativeLayout) view.findViewById(R.id.shareAppRL);
        marketJDTV = (TextView) view.findViewById(R.id.marketJDTV);
        marketTBTV = (TextView) view.findViewById(R.id.marketTBTV);
        powerFullRemindIV.setOnClickListener(this);
        theftProofRemindIV.setOnClickListener(this);
        lightModelIV.setOnClickListener(this);
        shareAppRL.setOnClickListener(this);
        marketJDTV.setOnClickListener(this);
        marketTBTV.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    /**
     * 初始化广播
     */
    public void initBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Actions.POWER_FULL_REMIND_STATUS_CHANGE);
        filter.addAction(Actions.THEFT_PROOF_REMIND_STATUS_CHANGE);
        getActivity().registerReceiver(receiver, filter);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.powerFullRemindIV:
                // 是否满电提醒
                if (AppUtils.getInstance().isPowerFullRemind()) {
                    AppUtils.getInstance().updateIsPowerFullRemind(false);
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsPowerFullRemind(true);
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                }
                getActivity().sendBroadcast(new Intent(Actions.POWER_FULL_REMIND_STATUS_CHANGE));
                break;
            case R.id.theftProofRemindIV:
                // 是否防盗提醒
                if (AppUtils.getInstance().isTheftProofRemind()) {
                    AppUtils.getInstance().updateIsTheftProofRemind(false);
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsTheftProofRemind(true);
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                }
                getActivity().sendBroadcast(new Intent(Actions.THEFT_PROOF_REMIND_STATUS_CHANGE));
                break;
            case R.id.nightModelIV:
                // 是否开启夜间模式
                if (AppUtils.getInstance().isNightMode()) {
                    AppUtils.getInstance().updateIsNightMode(false);
                    lightModelIV.setBackgroundResource(R.mipmap.settings_turn_off);
                } else {
                    AppUtils.getInstance().updateIsNightMode(true);
                    lightModelIV.setBackgroundResource(R.mipmap.settings_turn_on);
                }
                getActivity().sendBroadcast(new Intent(Actions.NIGHT_MODEL_STATUS_CHANGE));
                break;

            case R.id.marketJDTV:
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra(WebActivity.INTENT_MARKET_TYPE, WebActivity.INTENT_MARKET_TYPE_JD);
                startActivity(intent);
                break;
            case R.id.marketTBTV:
                intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra(WebActivity.INTENT_MARKET_TYPE, WebActivity.INTENT_MARKET_TYPE_TB);
                startActivity(intent);
                break;
            case R.id.shareAppRL:
                // TODO by L.jinzhu for
                shareMsg("这里是页面title", "分享信息title", "分享信息内容", "");
                break;
            default:
                break;
        }
    }

    /**
     * 分享功能
     *
     * @param activityTitle Activity的名字
     * @param msgTitle      消息标题
     * @param msgText       消息内容
     * @param imgPath       图片路径，不分享图片则传null
     */
    public void shareMsg(String activityTitle, String msgTitle, String msgText, String imgPath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (imgPath == null || imgPath.equals("")) {
            intent.setType("text/plain"); // 纯文本
        } else {
            File f = new File(imgPath);
            if (f != null && f.exists() && f.isFile()) {
                intent.setType("image/jpg");
                Uri u = Uri.fromFile(f);
                intent.putExtra(Intent.EXTRA_STREAM, u);
            }
        }
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, activityTitle));
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            LogUtil.i("receive broadcast,action:" + intent.getAction());
            // 满电提醒
            if (Actions.POWER_FULL_REMIND_STATUS_CHANGE.equals(intent.getAction())) {
                if (AppUtils.getInstance().isPowerFullRemind()) {
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                } else {
                    powerFullRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                }
            }
            // 防盗提醒
            else if (Actions.THEFT_PROOF_REMIND_STATUS_CHANGE.equals(intent.getAction())) {
                if (AppUtils.getInstance().isTheftProofRemind()) {
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_on);
                } else {
                    theftProofRemindIV.setBackgroundResource(R.mipmap.settings_turn_off);
                }
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(receiver);
    }
}