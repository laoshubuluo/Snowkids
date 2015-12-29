package com.rat.nm.common;

/**
 * @author L.jinzhu
 * @Description: 定义广播Action
 * @date 2015-09-11 下午15:29:33
 */
public class Actions {
    /**
     * 单聊消息发生变动时更新窗口的广播消息
     */
    public static final String SINGLEMESSAGE_ADD_ACTION = "rat.nm.action.singlemessage_add_action";
    /**
     * 群聊消息发生变动时更新窗口的广播消息
     */
    public static final String GROUPMESSAGE_ADD_ACTION = "rat.nm.action.groupmessage_add_action";
    /**
     * 发送单聊消息广播
     */
    public static final String ACTION_SNED_SINGLE_MESSAGE = "rat.nm.send_single_message";
    /**
     * 发送群组消息广播
     */
    public static final String ACTION_SNED_GROUP_MESSAGE = "rat.nm.send_group_message";
    /**
     * 更新消息状态广播
     */
    public static final String ACTION_UPDATE_MESSAGE_STATUE = "rat.nm.update_message_status";
    /**
     * 更新图片消息上传进度广播
     */
    public static final String ACTION_UPDATE_IMGMESSAGE_PROCESS = "rat.nm.update_imgmessage_process";
    /**
     * 删除消息广播
     */
    public static final String ACTION_DELETE_MESSAGE_BYMESSAGEID = "rat.nm.delete_message_bymessageid";
    /**
     * 接收单聊消息广播
     */
    public static final String ACTION_RECEIVER_SINGLE_MESSAGE = "rat.nm.receiver_single_message";
    /**
     * 接收群聊消息广播
     */
    public static final String ACTION_RECEIVER_GROUP_MESSAGE = "rat.nm.receiver_group_message";
    /**
     * 群组消息发送融云返回状态码
     */
    public static final String ACTION_GROUP_MESSAGE_RCODE = "rat.nm.group_message_rcode";
    /**
     * Session 广播
     */
    public static final String ACTION_SESSION = "rat.nm.session";
    /**
     * 消息的已读未读状态变化
     */
    public static final String MESSAGE_READ_CHANGE = "rat.nm.action.meesage_read_change";
    /**
     * 融云链接状态监听
     */
    public static final String CONNECTION_SUCCESS = "rat.nm.action.CONNECTION_SUCCESS"; // 链接成功
    public static final String CONNECTION_FAILED = "rat.nm.action.CONNECTION_FAILED"; // 断开链接
    public static final String CONNECTION_LINKING = "rat.nm.action.CONNECTION_LINGING"; // 连接中...
    public static final String CONNECTION_UNAVAILABLE = "rat.nm.action.CONNECTION_UNAVAILABLE"; // 网络不可用
    /**
     * 好友关系 广播
     */
    public static final String ACTION_FRIEND_IS_FRIEND = "rat.nm.action.friend_is_friend";//成为好友
    public static final String ACTION_FRIEND_BE_REQUEST = "rat.nm.action.friend_be_request";//被对方申请添加为好友
    public static final String ACTION_FRIEND_DELETE = "rat.nm.action.friend_delete";//删除好友
    public static final String ACTION_FRIEND_UPDATE = "rat.nm.action.friend_update";//好友状态更新
    public static final String ACTION_CLEAN_USER_SESSION = "rat.nm.action.clean_user_session";//删除好友刷新会话列表
    public static final String ACTION_BLACKLIST_ADD = "rat.nm.action.blacklist_add";//加入黑名单
    public static final String ACTION_BLACKLIST_DELETE = "rat.nm.action.blacklist_delete";//移除黑名单

    /**
     * 用户邀请、添加（通讯录好友推荐界面）广播
     */
    public static final String ACTION_USER_ADD = "rat.nm.action.user_add";//用户添加
    public static final String ACTION_USER_INVATE = "rat.nm.action.user_invate";//用户邀请
    /**
     * 任务的已读未读状态变化
     */
    public static final String ACTION_TASK_READ_CHANGE = "rat.nm.action.task_read_change";
    /**
     * 查经讨论：已读未读状态变化
     */
    public static final String ACTION_BIBLE_DISCUSS_READ_CHANGE = "rat.nm.action.bible_discuss_read_change";
    /**
     * 软件更新广播
     */
    public static final String ACTION_APP_UPDATE = "rat.nm.action.app_update";//软件更新
}
