//package com.rat.nm.database;
//
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.database.sqlite.SQLiteStatement;
//
//import com.rat.networkmanager.R;
//import com.rat.nm.common.Constant;
//import com.rat.nm.util.AppUtils;
//import com.rat.nm.util.LogUtil;
//
//
///**
// * 数据库操作工具类
// *
// * @author shisheng.zhao
// * @date 2015-09-07 18:51:22
// */
//public class DbOpenHelper extends SQLiteOpenHelper {
//    // 用户表
//    private final static String sqlCreateUserInfo = "create table userinfo(_id integer primary key autoincrement,uid,nickname,noteName,amenId,sex default '0',province,city,country,headUrl,phoneNum,level default '1',signa,countryCode,status default '0',km,timeAgo,dateTime,timestamp default '0',uTime default '0',dnd default '0',remark)";
//    private final static String sqlDropUserInfo = "drop table if exists userinfo";
//    // 群组表
//    private final static String sqlCreateGroupInfo = "create table groupinfo(_id integer primary key autoincrement,groupId,groupName,uid,maxList,count,icon,addList default '0',dnd default '0',showName default '0')";
//    // 群组成员关系表
//    private final static String sqlCreateGroupMember = "create table groupmember(_id integer primary key autoincrement,groupId,uid)";
//    // 邻舍信息表
//    private final static String sqlCreateNearbyInfo = "create table nearbyinfo(_id integer primary key autoincrement,uid,remark)";
//    // 消息表
//    private final static String sqlCreateMessageInfo = "create table messageinfo(_id integer primary key autoincrement,messageId,userFrom,userTo,content,timestamp,groupId,isRead,isVisible,isDelay,state,type,audiolength,lng,lat,address,volumeId,chapterId,sectionId)";
//    // 会话表
//    private final static String sqlCreateSessionInfo = "create table sessioninfo(_id integer primary key autoincrement,targetId NOT NULL unique,sessionType,lastMessageId,priority,sessionIcon,messageType,sessionName,sessionContent,sessionIsRead)";
//    // 任务表
//    private final static String sqlCreateTaskInfo = "create table taskinfo(_id integer primary key autoincrement,taskId default '0',userFrom default '0',userTo default '0',status default '0',applySta default '0',message,time,isRead)";
//    // 系统新闻表
//    private final static String sqlCreateNewsInfo = "create table newsinfo(_id integer primary key autoincrement,newsId,newsTitle,newsTotle,newsImgUrl,newsSummary,newsUrl,newsSource,timeLong)";
//
//    private static DbOpenHelper helper;
//    private static Context context;
//
//    private DbOpenHelper(Context context) {
//        super(context, AppUtils.getInstance().getUserId(), null, Constant.DB_COMMON_VERSION);
//        DbOpenHelper.context = context;
//    }
//
//    private DbOpenHelper() {
//        super(context, AppUtils.getInstance().getUserId(), null, Constant.DB_COMMON_VERSION);
//    }
//
//    public static DbOpenHelper getInstance(Context context) {
//        if (helper == null) {
//            synchronized (DbOpenHelper.class) {
//                helper = new DbOpenHelper(context);
//            }
//        }
//        return helper;
//    }
//
//    public static void reload() {
//        if (helper != null) {
//            try {
//                helper.close();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        helper = new DbOpenHelper();
//    }
//
//    public static void reset() {
//        if (helper != null) {
//            helper.close();
//        }
//        helper = null;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//        db.execSQL(sqlCreateUserInfo);// 用户表
//        db.execSQL(sqlCreateGroupInfo);// 群组表
//        db.execSQL(sqlCreateGroupMember);// 群组成员关系表
//        db.execSQL(sqlCreateNearbyInfo);// 邻舍信息表
//        db.execSQL(sqlCreateMessageInfo);// 消息表
//        db.execSQL(sqlCreateSessionInfo);// 会话表
//        db.execSQL(sqlCreateTaskInfo);// 任务表
//        db.execSQL(sqlCreateNewsInfo);// 系统文章表
//        // 初始化数据
//        initData(db);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        LogUtil.i("db update begin:userId:" + AppUtils.getInstance().getUserId() + ",oldVersion:" + oldVersion + ",newVersion:" + newVersion);
//        // ************** 更新用户表数据结构 start **************
//        try {
//            String selectSql = "select * from userinfo";
//            Cursor cursor;
//            List<User> userList = new ArrayList<User>();
//            try {
//                cursor = db.rawQuery(selectSql, null);
//                for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext())
//                    userList.add(parseUserFromCursor4Version_1(cursor));
//                LogUtil.i("db update:backup old data from userInfo success:" + userList.size());
//            } catch (Exception ex) {
//                LogUtil.e("db update:backup old data from userInfo error:", ex);
//            }
//            db.execSQL(sqlDropUserInfo);
//            LogUtil.i("db update:drop table userInfo success");
//            db.execSQL(sqlCreateUserInfo);
//            LogUtil.i("db update:create table userInfo success");
//            try {
//                SQLiteStatement stat = db.compileStatement(UserDaoImpl.sqlInsertUserInfo);
//                for (User user : userList) {
//                    UserDaoImpl.bindData(stat, user);
//                    stat.executeInsert();
//                }
//                LogUtil.i("db update:restore old data to userInfo success:" + userList.size());
//            } catch (Exception ex) {
//                LogUtil.e("db update:restore old data to userInfo error:", ex);
//            }
//            // ************** 更新用户表数据结构 end **************
//            db.setVersion(newVersion);
//            LogUtil.i("db update success");
//        } catch (Throwable e) {
//            db.setVersion(oldVersion);
//            LogUtil.e("db update error", e);
//        }
//    }
//
//    public void closeDb() {
//        this.close();
//    }
//
//    /**
//     * 初始化数据
//     */
//    public void initData(SQLiteDatabase db) {
//        String sql;
//        // 初始化客服信息
//        sql = "insert into userinfo(uid,nickname,headUrl,status) values('" + Constant.UID_SERVICE_START + "','" + context.getString(R.string.service) + "','" + Constant.UID_SERVICE_ICON + "','" + User.STATUS_SERVICE + "')";
//        db.execSQL(sql);
//    }
//
//
//    /**
//     * 从cursor中获取用户信息(用于dbVersion=1)
//     *
//     * @param cursor
//     */
//    public User parseUserFromCursor4Version_1(Cursor cursor) {
//        User user = new User();
//        user.setUid(cursor.getLong(cursor.getColumnIndexOrThrow("uid")));
//        user.setNickname(cursor.getString(cursor.getColumnIndexOrThrow("nickname")));
//        user.setSex(cursor.getString(cursor.getColumnIndexOrThrow("sex")));
//        user.setProvince(cursor.getString(cursor.getColumnIndexOrThrow("province")));
//        user.setCity(cursor.getString(cursor.getColumnIndexOrThrow("city")));
//        user.setCountry(cursor.getString(cursor.getColumnIndexOrThrow("country")));
//        user.setHeadUrl(cursor.getString(cursor.getColumnIndexOrThrow("headUrl")));
//        user.setPhoneNum(cursor.getString(cursor.getColumnIndexOrThrow("phoneNum")));
//        user.setLevel(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("level"))));
//        user.setSigna(cursor.getString(cursor.getColumnIndexOrThrow("signa")));
//        user.setCountryCode(cursor.getString(cursor.getColumnIndexOrThrow("countryCode")));
//        user.setStatus(cursor.getString(cursor.getColumnIndexOrThrow("status")));
//        user.setKm(cursor.getString(cursor.getColumnIndexOrThrow("km")));
//        user.setTimeAgo(cursor.getString(cursor.getColumnIndexOrThrow("timeAgo")));
//        user.setDateTime(cursor.getString(cursor.getColumnIndexOrThrow("dateTime")));
//        user.setTimestamp(Long.parseLong(cursor.getString(cursor.getColumnIndexOrThrow("timestamp"))));
//        user.setDnd(cursor.getString(cursor.getColumnIndexOrThrow("dnd")));
//        user.setRemark(cursor.getString(cursor.getColumnIndexOrThrow("remark")));
//        return user;
//    }
//}
