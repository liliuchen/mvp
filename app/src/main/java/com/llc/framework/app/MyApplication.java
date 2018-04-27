package com.llc.framework.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;

import org.xutils.BuildConfig;
import org.xutils.x;

/**
 *@package  com.crm.framework.app
 *@fileName MyApplication
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe app入口 初始化一些全局变量
 *@company  棋至文化广播有限公司
 */

public class MyApplication extends MultiDexApplication {
    private String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG); // 是否输出debug日志, 开启debug会影响性能.

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
