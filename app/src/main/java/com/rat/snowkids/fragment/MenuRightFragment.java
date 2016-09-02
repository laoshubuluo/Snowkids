package com.rat.snowkids.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.snowkids.snowkids.R;
import com.rat.snowkids.util.AppUtils;
import com.rat.snowkids.util.UserUtils;

/**
 * author : L.jinzhu
 * date : 2016/1/24
 * introduce : 滑动菜单界面
 */
public class MenuRightFragment extends Fragment {
    private LinearLayout logoutLL;
    private TextView userNameTV;
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
        userNameTV = (TextView) view.findViewById(R.id.userNameTV);
        logoutLL = (LinearLayout) view.findViewById(R.id.logoutLL);
        logoutLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserUtils.getInstance(getActivity()).logout();
                getActivity().finish();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        userNameTV.setText(AppUtils.getInstance().getUserName());
    }
}