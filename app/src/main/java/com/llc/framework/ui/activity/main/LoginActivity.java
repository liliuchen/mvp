package com.llc.framework.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.llc.framework.R;
import com.llc.framework.manager.activity.ActivityManager;
import com.llc.framework.mvp.presenter.BasePresenter;
import com.llc.framework.ui.base.BaseActivity;
import com.llc.framework.utils.SharedPreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 *@package  com.crm.framework.ui.activity.main
 *@fileName LoginActivity
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 登录界面
 *@company  棋至文化广播有限公司
 */
public class LoginActivity extends BaseActivity{
    private static String ACTION_ID;
    @BindView(R.id.activity_login_cb_remember_name)
    CheckBox mCbRememberUsername;
    @BindView(R.id.activity_login_tv_forget_passwd)
    TextView mTvForgetPw;
    @BindView(R.id.activity_login_et_username)
    EditText mEtUsername;
    @BindView(R.id.activity_login_et_passwd)
    EditText mEtPasswd;
    @BindView(R.id.activity_login_btn_login)
    Button mBtnLogin;
    @BindView(R.id.activity_login_btn_register)
    Button mBtnRegister;
    SharedPreferencesHelper mSharedPreferencesHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
        setListener();


    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }


    private void setListener() {

    }


    private void initData() {
        boolean isRemember = SharedPreferencesHelper.getRememberUserName(this);
        mCbRememberUsername.setChecked(isRemember);

        if (isRemember) {
            String localUsername = SharedPreferencesHelper.getLocalUsername(this);
            mEtUsername.setText(localUsername);
            mEtPasswd.requestFocus();
        } else {
            mEtUsername.setText("");
            mEtUsername.requestFocus();
        }

    }

    public static void show(Context context, String actionId) {
        ACTION_ID = actionId;
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
