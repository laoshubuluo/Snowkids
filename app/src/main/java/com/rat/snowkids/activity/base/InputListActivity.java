//package com.rat.nm.activity.base;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//
//import com.snowkids.snowkids.R;
//import com.rat.snowkids.common.ActivityResultConstant;
//import com.rat.snowkids.common.MessageSignConstant;
//import com.rat.snowkids.util.LogUtil;
//import com.rat.snowkids.view.dialog.CustomProgressDialog;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * author : L.jinzhu
// * date : 2015/8/17
// * introduce : 列表选择输入界面
// */
//public class InputListActivity extends BaseActivity {
//    public final static int SEX_INPUT = 1;//性别输入
//    private int activityType;
//    private List<String> list = new ArrayList<String>();
//    private ViewHolder viewHolder;
//    private User user;
//    private String sexStr;
//    private boolean autoCommit;//是否自动提交服务器
//
//    @(R.id.top_left)
//    private TextView leftView;
//    @ControlInjection(R.id.top_name)
//    private TextView titleView;
//    @ControlInjection(R.id.listView)
//    private ListView listView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_input_list);
//        activityType = getIntent().getIntExtra("activityType", 0);
//        user = (User) getIntent().getSerializableExtra("user");
//        initView();
//    }
//
//    private void initView() {
//        leftView.setOnClickListener(this);
//        leftView.setVisibility(View.VISIBLE);
//
//        if (SEX_INPUT == activityType) {
//            autoCommit = getIntent().getBooleanExtra("autoCommit", false);
//            titleView.setText(getString(R.string.sex_label));
//            list.add(UserSex.SEX_BOY.getResultMsg());
//            list.add(UserSex.SEX_GIRL.getResultMsg());
//            BaseAdapter baseAdapter = new BaseAdapter() {
//                @Override
//                public int getCount() {
//                    return list.size();
//                }
//
//                @Override
//                public Object getItem(int i) {
//                    return list.get(i);
//                }
//
//                @Override
//                public long getItemId(int i) {
//                    return i;
//                }
//
//                @Override
//                public View getView(int position, View convertView, ViewGroup viewGroup) {
//                    convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.view_list_item_simple, null);
//                    viewHolder = new ViewHolder();
//                    viewHolder.contentTV = (TextView) convertView.findViewById(R.id.contentTV);
//                    viewHolder.checkIV = (ImageView) convertView.findViewById(R.id.checkIV);
//                    convertView.setTag(viewHolder);
//                    //显示内容
//                    String value = list.get(position);
//                    viewHolder.contentTV.setText(value);
//                    viewHolder.contentTV.setTextSize(17);
//                    if (value.equals(user.getSex())) {
//                        viewHolder.checkIV.setVisibility(View.VISIBLE);
//                        viewHolder.contentTV.setTextColor(getResources().getColor(R.color.font_green));
//                    }
//                    return convertView;
//                }
//            };
//            listView.setAdapter(baseAdapter);
//            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                    sexStr = list.get(position);
//                    // 自动提交（点击返回即提交服务器）
//                    if (autoCommit) {
//                        user.setSex(UserSex.parseByMsg(sexStr).getResultCode());
//                        customProgressDialog = new CustomProgressDialog(InputListActivity.this, getString(R.string.loading));
//                        customProgressDialog.show();
//                        customProgressDialog.setCancelable(false);
//                        UserInfoController userInfoController = new UserInfoController(getApplication(), handler);
//                        userInfoController.userInfoModify(user);
//                    }
//                    // 返回内容至上一级界面
//                    else {
//                        Intent i = new Intent();
//                        i.putExtra("sexStr", sexStr);
//                        setResult(ActivityResultConstant.SEX_INPUT, i);
//                        finish();
//                    }
//                }
//            });
//        } else {
//            LogUtil.e("input list activity open error: activity type error", null);
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.top_left:
//                finish();
//                break;
//            default:
//                break;
//        }
//    }
//
//
//    @Override
//    public boolean handleMessage(Message msg) {
//        if (customProgressDialog != null)
//            customProgressDialog.dismiss();
//        switch (msg.what) {
//            case MessageSignConstant.USER_INFO_MODIFY_SUCCESS:
//                // 返回内容至上一级界面
//                Intent i = new Intent();
//                i.putExtra("sexStr", sexStr);
//                setResult(ActivityResultConstant.SEX_INPUT, i);
//                finish();
//                break;
//            case MessageSignConstant.USER_INFO_MODIFY_FAILURE:
//                Toast.makeText(getApplicationContext(), getString(R.string.user_info_modify_failure), Toast.LENGTH_LONG).show();
//                finish();
//                break;
//            case MessageSignConstant.SERVER_OR_NETWORK_ERROR:
//                Toast.makeText(getApplicationContext(), msg.getData().getString("message"), Toast.LENGTH_LONG).show();
//                finish();
//                break;
//            case MessageSignConstant.UNKNOWN_ERROR:
//                Toast.makeText(getApplicationContext(), getString(R.string.user_info_modify_error), Toast.LENGTH_LONG).show();
//                finish();
//                break;
//        }
//        return false;
//    }
//
//    private class ViewHolder {
//        private TextView contentTV;
//        private ImageView checkIV;
//    }
//}