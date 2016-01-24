package com.rat.nm.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rat.networkmanager.R;

/**
 * author : L.jinzhu
 * date : 2016/1/24
 * introduce : 滑动菜单界面
 */
public class MenuRightFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_right, container, false);
    }

}