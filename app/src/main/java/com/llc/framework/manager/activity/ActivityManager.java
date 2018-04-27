package com.llc.framework.manager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.llc.framework.ui.activity.main.LoginActivity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *@package  com.crm.framework.manager.activity
 *@fileName ActivityManager
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe Activity管理器 当需要检测登录只有才跳转
 *@company  棋至文化广播有限公司
 */


public class ActivityManager {
    private List<Activity> activityList = new LinkedList<>();
    private static ActivityManager instance;

    private ActivityManager() {
    }

    // 单例模式中获取唯一的ExitApplication实例
    public static ActivityManager getInstance() {
        if (null == instance) {
            instance = new ActivityManager();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public void exit() {
        for (Activity activity : activityList) {
            activity.finish();
        }
        System.exit(0);
    }

    /**
     * 移除Activity从容器中
     */
    public void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    /**
     * 获取操作ID
     *
     * @return 返回唯一字符串（时间戳）
     */
    private String getActionId() {
        return System.currentTimeMillis() + "";
    }

    /**
     * 登陆后跳转相应的界面
     *
     * @param context
     * @param intent
     */
    public void afterLoginShip(Context context, Intent intent) {
        String actionId = getActionId();
        intentMap.put(actionId, intent);
        LoginActivity.show(context, actionId);
    }

    /**
     * 检测用户登陆状态 需要用户 单独实现
     *
     * @return
     */
    private boolean isLogin() {
        // TODO: 2017/2/14  检测是否登陆
//        new IllegalArgumentException("未完成登陆检测");
        return false;

    }

    //保存需要登录成功之后跳转的intent
    private static Map<String, Intent> intentMap = new HashMap<>();

    /**
     * 登陆成功调用此方法进行跳转
     *
     * @param context
     * @param actionId
     */
    public boolean loginSuccessNotify(Context context, String actionId) {
        Intent intent = intentMap.get(actionId);
        if (intent == null) {//说明并没保存后续操作，返回false让登陆界面自行处理
            return false;
        }
        context.startActivity(intent);
        intentMap.remove(actionId);
        return true;
    }


}
