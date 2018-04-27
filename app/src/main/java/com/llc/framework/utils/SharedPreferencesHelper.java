package com.llc.framework.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by bluce lee on 2016/10/17.
 */
public class SharedPreferencesHelper {

    public static String IS_FIRST_START = "IS_FIRST_START";
    public static String LOGIN_USER_NAME_FLAG = "LOGIN_USER_NAME_FLAG";
    public static String IS_REMEMBER_PASSWD_FLAG = "IS_REMEMBER_USERNAME_FLAG";

    /**
     * 设置是否展示欢迎界面
     *
     * @param context 上下文
     * @param bool    是否是第一次启动
     */
    public static void setFirstStart(Context context, Boolean bool) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_FIRST_START, bool);
        edit.apply();
    }

    /**
     * 获取是否是第一次启动
     *
     * @param context
     * @return true 是第一次启动
     */
    public static boolean isFirstStart(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(IS_FIRST_START, true);
    }

    /**
     * 设置保存的用户名
     *
     * @param context
     * @param username
     */
    public static void setLocalUsername(Context context, String username) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(LOGIN_USER_NAME_FLAG, username);
        edit.apply();


    }

    /**
     * 获取保存的用户名
     *
     * @param context
     * @return
     */
    public static String getLocalUsername(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(LOGIN_USER_NAME_FLAG, "");
    }

    /**
     * 获取登录界面是否记住密码checkbox
     *
     * @param context
     * @return 是否记住密码
     */
    public static boolean getRememberUserName(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(IS_REMEMBER_PASSWD_FLAG, false);
    }

    /**
     * 获取登录界面的是否记录密码
     * @param context
     * @param isRemember
     */
    public static void setRememberUserName(Context context, boolean isRemember) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(IS_REMEMBER_PASSWD_FLAG, isRemember);
        edit.apply();
    }

}
