package com.llc.framework.ui.activity.main;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.llc.framework.R;
import com.llc.framework.manager.activity.ActivityManager;
import com.llc.framework.utils.SharedPreferencesHelper;

/**
 *@package  com.crm.framework.ui.activity.main
 *@fileName SplashActivity
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 启动界面
 *@company
 */
public class SplashActivity extends AppCompatActivity {

    private int requestCode = 0x0001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 23) {
            getPermissions();
        } else {
            goToMain();
        }
    }

    private void getPermissions() {
        String[] mPermissionList = new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE};

        ActivityCompat.requestPermissions(this, mPermissionList, requestCode);
    }

    private void goToMain() {

        boolean firstStart = SharedPreferencesHelper.isFirstStart(this);
        if (firstStart) {
            WelcomeActivity.show(SplashActivity.this, true);
        } else {
            MainActivity.show(SplashActivity.this);
        }
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == this.requestCode) {
            if (grantResults.length > 0) {
                for (int isGrant : grantResults) {
                    if (isGrant != PackageManager.PERMISSION_GRANTED) {
                        showTipForPermissions();
                        break;
                    }
                }
                goToMain();
            }

        }
    }

    private void showTipForPermissions() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setMessage("请允许相关权限,否则无法正常运行")
                .setNegativeButton("退出", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).setPositiveButton("允许", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPermissions();
                    }
                }).setCancelable(false).create();
        alertDialog.show();
    }
}
