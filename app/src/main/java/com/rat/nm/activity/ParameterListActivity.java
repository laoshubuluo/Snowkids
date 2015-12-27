package com.rat.nm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.rat.networkmanager.R;
import com.rat.nm.activity.base.BaseActivity;

import com.rat.nm.entity.Parameter;
import com.rat.nm.view.ParameterListItemView;

import java.util.ArrayList;
import java.util.List;

public class ParameterListActivity extends BaseActivity {


    @ViewInject(R.id.parameterListLV)
    protected ListView parameterListLV;

    private List<Parameter> parameterList = new ArrayList<Parameter>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter_list);

        //基础框架初始化
        ViewUtils.inject(this);//xUtils框架注解注入view和事件


        initView();
        initData();
    }


    /**
     * 初始化界面
     */
    public void initView() {
    }

    /**
     * 初始化数据
     */
    public void initData() {
        for (int i = 100; i < 200; i++)
            parameterList.add(new Parameter(i, "The name of no " + i + " parameter", "this is a description" + i, "http://www.baidu.com/wewe?name=1&&to=222" + i));
        parameterListLV.setAdapter(baseAdapter);
    }

    BaseAdapter baseAdapter = new BaseAdapter() {
        @Override
        public int getCount() {
            return parameterList.size();
        }

        @Override
        public Object getItem(int position) {
            return parameterList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ParameterListItemView item = new ParameterListItemView(getApplication());
            Parameter parameter = parameterList.get(position);
            item.initData(position, parameter.getKey(), parameter.getDes(), parameter.getValue());
            return item;
        }
    };


    /**
     * Handler发送message的逻辑处理方法
     *
     * @param msg
     * @return
     */
    @Override
    public boolean handleMessage(Message msg) {
//        switch (msg.what) {
//            case MessageSignConstant.DEMO:
//                Demo demo = (Demo) msg.getData().getSerializable("demo");
////                tv.setText(demo.getName());
//        }
        return false;
    }

    /**
     * 点击事件
     */
    @OnClick(R.id.signInBtn)
    public void signInBtnOnClick(View v) {
//        DemoService ds = new DemoService(getApplicationContext(), handler);
//        ds.testQuery();
//
//        DBDemo dbDemo = new DBDemo(getApplication());
//        dbDemo.save();
//        List<Demo> demoList = dbDemo.getAll();
//        for (Demo d : demoList)
//            LogUtil.e(d.getName() + "||" + d.getPassword());
        Intent i = new Intent(ParameterListActivity.this, ParameterListActivity.class);
        startActivity(i);
    }


}