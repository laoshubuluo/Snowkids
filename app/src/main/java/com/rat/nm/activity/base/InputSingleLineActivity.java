//package com.rat.nm.activity.base;
//
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.text.InputFilter;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.wmgj.amen.R;
//import com.wmgj.amen.activity.BaseActivity;
//import com.wmgj.amen.common.ActivityResultConstant;
//import com.wmgj.amen.common.MessageSignConstant;
//import com.wmgj.amen.controller.FriendController;
//import com.wmgj.amen.controller.GroupController;
//import com.wmgj.amen.controller.UserInfoController;
//import com.wmgj.amen.dao.impl.UserDaoImpl;
//import com.wmgj.amen.entity.user.User;
//import com.wmgj.amen.injection.ControlInjection;
//import com.wmgj.amen.util.AppUtils;
//import com.wmgj.amen.util.LogUtil;
//import com.wmgj.amen.util.StringUtils;
//import com.wmgj.amen.view.dialog.CustomProgressDialog;
//import com.wmgj.amen.view.dialog.SendSuccessDialog;
//
///**
// * author : L.jinzhu
// * date : 2015/8/17
// * introduce : 单行输入界面
// */
//public class InputSingleLineActivity extends BaseActivity {
//    public final static int FRIEND_ADD_MESSAGE_INPUT = 1;//好友添加：信息输入
//    public final static int FRIEND_SAY_HELLO_INPUT = 2;//好友打招呼：信息输入
//    public final static int USER_INFO_NAME_INPUT = 3;//用户信息：姓名输入(当前用户)
//    public final static int CHANGE_GROUP_NAME_INPUT = 4;//修改群组名称
//    public final static int USER_INFO_NOTE_NAME_INPUT = 5;//用户信息：备注姓名输入
//
//    private int activityType;
//    private long userId;
//    private String groupId;
//    private User user;
//    private boolean autoCommit;//是否自动提交服务器
//
//    @ControlInjection(R.id.top_left)
//    private TextView leftView;
//    @ControlInjection(R.id.top_name)
//    private TextView titleView;
//    @ControlInjection(R.id.top_right)
//    private TextView rightView;
//    @ControlInjection(R.id.editText)
//    private EditText editText;
//    CustomProgressDialog customProgressDialog;
//    InputMethodManager imm = null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_base_input_single_line);
//        activityType = getIntent().getIntExtra("activityType", 0);
//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//        initView();
//    }
//
//    private void initView() {
//        leftView.setOnClickListener(this);
//        titleView.setOnClickListener(this);
//        rightView.setOnClickListener(this);
//        User my;
//        switch (activityType) {
//            case FRIEND_ADD_MESSAGE_INPUT:
//                userId = getIntent().getLongExtra("userId", 0);
//                leftView.setVisibility(View.VISIBLE);
//                rightView.setVisibility(View.VISIBLE);
//                titleView.setText(getString(R.string.verification_message));
//                rightView.setText(getString(R.string.submit));
//                my = new UserDaoImpl().getUserByUid(AppUtils.getInstance().getUserId());
//                editText.setText(String.format(getString(R.string.addfriend_message), my.getNickname()));
//                InputFilter[] fMessage = {new InputFilter.LengthFilter(50)};
//                editText.setFilters(fMessage);
//                break;
//            case FRIEND_SAY_HELLO_INPUT:
//                userId = getIntent().getLongExtra("userId", 0);
//                leftView.setVisibility(View.VISIBLE);
//                rightView.setVisibility(View.VISIBLE);
//                titleView.setText(getString(R.string.say_hello));
//                rightView.setText(getString(R.string.send));
//                my = new UserDaoImpl().getUserByUid(AppUtils.getInstance().getUserId());
//                editText.setText(String.format(getString(R.string.addfriend_message), my.getNickname()));
//                InputFilter[] hMessage = {new InputFilter.LengthFilter(50)};
//                editText.setFilters(hMessage);
//                break;
//            case USER_INFO_NAME_INPUT:
//                user = (User) getIntent().getSerializableExtra("user");
//                autoCommit = getIntent().getBooleanExtra("autoCommit", false);
//                leftView.setVisibility(View.VISIBLE);
//                titleView.setText(getString(R.string.name_label));
//                editText.setText(user.getNickname());
//                InputFilter[] fName = {new InputFilter.LengthFilter(20)};
//                editText.setFilters(fName);
//                break;
//            case CHANGE_GROUP_NAME_INPUT:
//                groupId = getIntent().getStringExtra("groupId");
//                String groupName = getIntent().getStringExtra("groupName");
//                leftView.setVisibility(View.VISIBLE);
//                rightView.setVisibility(View.VISIBLE);
//                titleView.setText(getString(R.string.change_group_name));
//                rightView.setText(getString(R.string.submit));
//                editText.setText(groupName);
//                InputFilter[] fGroupName = {new InputFilter.LengthFilter(20)};
//                editText.setFilters(fGroupName);
//                // 获取编辑框焦点
//                editText.setFocusable(true);
//                //打开软键盘
//                imm.showSoftInput(editText, InputMethodManager.RESULT_SHOWN);
//                imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
//                break;
//            case USER_INFO_NOTE_NAME_INPUT:
//                user = (User) getIntent().getSerializableExtra("user");
//                leftView.setVisibility(View.VISIBLE);
//                titleView.setText(getString(R.string.note_name_label));
//                editText.setText(user.getNoteName());
//                InputFilter[] fNoteName = {new InputFilter.LengthFilter(20)};
//                editText.setFilters(fNoteName);
//                break;
//            default:
//                LogUtil.e("input single line activity open error: activity type error", null);
//                break;
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        boolean isOpen = imm.isActive();//isOpen若返回true，则表示输入法打开
//        switch (v.getId()) {
//            case R.id.top_left:
//                // 隐藏键盘
//                if (isOpen) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
//                }
//                // 修改姓名
//                if (activityType == USER_INFO_NAME_INPUT) {
//                    String message = editText.getText().toString().trim();
//                    if (StringUtils.isNullOrBlank(message)) {
//                        Toast.makeText(getApplicationContext(), getString(R.string.user_name_is_null), Toast.LENGTH_LONG).show();
//                        return;
//                    }
//                    // 自动提交（点击返回即提交服务器）
//                    if (autoCommit) {
//                        user.setNickname(message);
//                        customProgressDialog = new CustomProgressDialog(this, getString(R.string.loading));
//                        customProgressDialog.show();
//                        customProgressDialog.setCancelable(false);
//                        UserInfoController userInfoController = new UserInfoController(getApplication(), handler);
//                        userInfoController.userInfoModify(user);
//                    }
//                    // 返回内容至上一级界面
//                    else {
//                        Intent i = new Intent();
//                        i.putExtra("name", message);
//                        setResult(ActivityResultConstant.NAME_INPUT, i);
//                        finish();
//                    }
//                }
//                // 修改备注
//                else if (activityType == USER_INFO_NOTE_NAME_INPUT) {
//                    String message = editText.getText().toString().trim();
//                    // 自动提交（点击返回即提交服务器）
//                    user.setNoteName(message);
//                    customProgressDialog = new CustomProgressDialog(this, getString(R.string.loading));
//                    customProgressDialog.show();
//                    customProgressDialog.setCancelable(false);
//                    UserInfoController controller = new UserInfoController(getApplication(), handler);
//                    controller.userNoteNameModify(user.getUid(), message);
//                }
//                // 退出
//                else {
//                    finish();
//                }
//                break;
//            case R.id.top_right:
//                // 隐藏键盘
//                if (isOpen) {
//                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
//                }
//                String message = editText.getText().toString().trim();
//                if (StringUtils.isNullOrBlank(message)) {
//                    Toast.makeText(getApplicationContext(), getString(R.string.message_is_null), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (activityType == FRIEND_ADD_MESSAGE_INPUT || activityType == FRIEND_SAY_HELLO_INPUT) {
//                    customProgressDialog = new CustomProgressDialog(InputSingleLineActivity.this, getString(R.string.loading));
//                    customProgressDialog.show();
//                    FriendController controller = new FriendController(getApplication(), handler);
//                    controller.addRequest(userId, message);
//                } else if (activityType == CHANGE_GROUP_NAME_INPUT) {
//                    customProgressDialog = new CustomProgressDialog(InputSingleLineActivity.this, "正在创建群组...");
//                    customProgressDialog.show();
//                    customProgressDialog.setCancelable(false);
//                    GroupController groupController = new GroupController(getApplication(), handler);
//                    groupController.updateGroupName(groupId, message);
//                }
//                break;
//            default:
//                break;
//        }
//    }
//
//    @Override
//    public boolean handleMessage(Message msg) {
//        if (customProgressDialog != null)
//            customProgressDialog.dismiss();
//        Intent i;
//        switch (msg.what) {
//            case MessageSignConstant.USER_INFO_MODIFY_SUCCESS:
//                // 返回内容至上一级界面
//                i = new Intent();
//                i.putExtra("name", user.getNickname());
//                setResult(ActivityResultConstant.NAME_INPUT, i);
//                Toast.makeText(getApplicationContext(), getString(R.string.user_info_modify_success), Toast.LENGTH_LONG).show();
//                finish();
//                break;
//            case MessageSignConstant.USER_INFO_MODIFY_FAILURE:
//                Toast.makeText(getApplicationContext(), getString(R.string.user_info_modify_failure), Toast.LENGTH_LONG).show();
//                finish();
//                break;
//            case MessageSignConstant.USER_NOTE_NAME_MODIFY_SUCCESS:
//                // 返回内容至上一级界面
//                i = new Intent();
//                i.putExtra("noteName", user.getNoteName());
//                setResult(ActivityResultConstant.NOTE_NAME_INPUT, i);
//                Toast.makeText(getApplicationContext(), getString(R.string.user_note_name_modify_sucess), Toast.LENGTH_LONG).show();
//                finish();
//                break;
//            case MessageSignConstant.USER_NOTE_NAME_MODIFY_FAILURE:
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
//            case MessageSignConstant.FRIEND_ADD_REQ_SUCCESS:
//                SendSuccessDialog dialog = new SendSuccessDialog(InputSingleLineActivity.this);
//                dialog.show();
//                dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        finish();
//                    }
//                });
//                break;
//            case MessageSignConstant.UPDATE_GROUPNAME_SUCCESS:
//                i = new Intent();
//                i.putExtra("groupname", editText.getText().toString().trim());
//                setResult(ActivityResultConstant.GROUPNAME_INPUT, i);
//                finish();
//                break;
//            case MessageSignConstant.UPDATE_GROUPNAME_FAILURE:
//                int code = msg.getData().getInt("code");
//                String message = msg.getData().getString("message");
//                Toast.makeText(InputSingleLineActivity.this, "群组名称更新失败", Toast.LENGTH_LONG).show();
//                break;
//        }
//        return false;
//    }
//}