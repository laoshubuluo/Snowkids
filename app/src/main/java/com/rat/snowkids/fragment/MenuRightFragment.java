package com.rat.snowkids.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rat.snowkids.util.AppUtils;
import com.snowkids.snowkids.R;

/**
 * author : L.jinzhu
 * date : 2016/1/24
 * introduce : 滑动菜单界面
 */
public class MenuRightFragment extends Fragment {
    private ImageView iconIV;
    private TextView userNameTV;
    private ImageView powerFullRemindIV;
    private ImageView theftProofRemindIV;
    private TextView shareAppTV;
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
        }
        return view;
    }


    /**
     * 初始化界面
     */
    private void initView(View view) {
        iconIV = (ImageView) view.findViewById(R.id.iconIV);
        userNameTV = (TextView) view.findViewById(R.id.userNameTV);
        powerFullRemindIV = (ImageView) view.findViewById(R.id.powerFullRemindIV);
        theftProofRemindIV = (ImageView) view.findViewById(R.id.theftProofRemindIV);
        shareAppTV = (TextView) view.findViewById(R.id.shareAppTV);
        marketJDTV = (TextView) view.findViewById(R.id.marketJDTV);
        marketTBTV = (TextView) view.findViewById(R.id.marketTBTV);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        userNameTV.setText(AppUtils.getInstance().getUserName());
    }
}