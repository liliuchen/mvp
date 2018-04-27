package com.llc.framework.utils;

/**
 * Created by Administrator on 2016/8/24.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Caige
 * <p>
 * 2015-11-1
 * </p>
 * <p>
 * 应用商店评分工具类
 * </p>
 */
public class MarketUtils {
    /**
     * 获取已安装应用商店的包名列表
     *
     * @param context
     * @return
     */
    public static ArrayList<String> queryInstalledMarketPkgs(Context context) {
        ArrayList<String> pkgs = new ArrayList<String>();
        if (context == null)
            return pkgs;
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.APP_MARKET");
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> infos = pm.queryIntentActivities(intent, 0);
        if (infos == null || infos.size() == 0)
            return pkgs;
        int size = infos.size();
        for (int i = 0; i < size; i++) {
            String pkgName = "";
            try {
                ActivityInfo activityInfo = infos.get(i).activityInfo;
                pkgName = activityInfo.packageName;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!TextUtils.isEmpty(pkgName))
                pkgs.add(pkgName);

        }
        return pkgs;
    }

    /**
     * 过滤出已经安装的包名集合
     *
     * @param context
     * @param pkgs    待过滤包名集合
     * @return 已安装的包名集合
     */
    public static ArrayList<String> filterInstalledPkgs(Context context,
                                                        ArrayList<String> pkgs) {
        ArrayList<String> empty = new ArrayList<String>();
        if (context == null || pkgs == null || pkgs.size() == 0)
            return empty;
        PackageManager pm = context.getPackageManager();
        List<PackageInfo> installedPkgs = pm.getInstalledPackages(0);
        int li = installedPkgs.size();
        int lj = pkgs.size();
        for (int j = 0; j < lj; j++) {
            for (int i = 0; i < li; i++) {
                String installPkg = "";
                String checkPkg = pkgs.get(j);
                try {
                    installPkg = installedPkgs.get(i).applicationInfo.packageName;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (TextUtils.isEmpty(installPkg))
                    continue;
                if (installPkg.equals(checkPkg)) {
                    empty.add(installPkg);
                    break;
                }

            }
        }
        return empty;
    }

    /**
     * com.qihoo.appstore  360手机助手
     * com.taobao.appcenter    淘宝手机助手
     * com.tencent.android.qqdownloader    应用宝
     * com.hiapk.marketpho 安卓市场
     * cn.goapk.market 安智市场
     * <p>
     * <p>
     * 启动到app详情界面
     *
     * @param appPkg    App的包名
     * @param marketPkg 应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void launchAppDetail(Context context, String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.getApplicationContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean gotoEvaluate(Activity context, String appPkg) {
        ArrayList<String> pkgs = queryInstalledMarketPkgs(context);
        if (pkgs == null || pkgs.size() == 0) {
            return false;
        }

        ArrayList<String> pkgs1 = filterInstalledPkgs(context, pkgs);
        if (pkgs1 == null || pkgs1.size() == 0) {
            return false;
        }

//        ActivityInfo info = null;
//        try {
//            info = context.getPackageManager()
//                    .getActivityInfo(context.getComponentName(),
//                            PackageManager.GET_META_DATA);
//            String msg = info.metaData.getString("UMENG_CHANNEL");
//            String pkg = getPkg(msg);
//
//            if (pkg != null) {
//                for (int i = 0; i < pkgs1.size(); i++) {
//                    if (pkgs1.get(i).equals(pkg)) {
//                        launchAppDetail(context, appPkg, msg);
//                        return true;
//                    }
//                }
//            }
//
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        }


        launchAppDetail(context, appPkg, pkgs1.get(0));
        return true;
    }


    private static String getPkg(String key) {
        String pkg = null;
        if (key.equals("AppChina")) {

        } else if (key.equals("YingYongBao")) {
            pkg = "com.tencent.android.qqdownloader";
        } else if (key.equals("WanDouJia")) {
        } else if (key.equals("GooglePlay")) {
        } else if (key.equals("QihooAppStore")) {
            pkg = "com.qihoo.appstore";
        } else if (key.equals("Baidu")) {

        } else if (key.equals("XiaomiStore")) {

        } else if (key.equals("MeiZu")) {

        } else if (key.equals("HuaWeiStore")) {

        } else if (key.equals("Oppo")) {

        } else if (key.equals("Vivo")) {

        } else if (key.equals("Samsung")) {

        } else if (key.equals("Letv")) {

        }
        return pkg;
    }


}
