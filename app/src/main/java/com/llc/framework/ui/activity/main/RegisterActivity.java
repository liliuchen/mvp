package com.llc.framework.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.llc.framework.R;
import com.llc.framework.manager.activity.ActivityManager;
import com.llc.framework.mvp.presenter.BasePresenter;
import com.llc.framework.ui.base.BaseActionBarActivity;
import com.llc.framework.utils.StringUtils;
import com.llc.framework.utils.ToastUtils;
import com.llc.framework.widget.CountdownButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 *@package  com.crm.framework.ui.activity.main
 *@fileName RegisterActivity
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 注册界面
 *@company  棋至文化广播有限公司
 */
public class RegisterActivity extends BaseActionBarActivity {

    @BindView(R.id.activity_register_et_phone)
    EditText mEtPhone;
    @BindView(R.id.activity_register_et_code)
    EditText mEtCode;
    @BindView(R.id.activity_register_et_passwd)
    EditText mEtPasswd;
    @BindView(R.id.activity_register_et_confirm)
    EditText mEtConfirm;
    @BindView(R.id.activity_register_btn_ok)
    Button mBtnOk;
    @BindView(R.id.activity_register_cbtn_get_code)
    CountdownButton mCbtnGetCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @OnClick({R.id.activity_register_btn_ok, R.id.activity_register_cbtn_get_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_register_btn_ok:
                registUser();
                break;
            case R.id.activity_register_cbtn_get_code:
                getVerifactionNumber();
                break;
        }
    }

    private void getVerifactionNumber() {
        String phoneNum = mEtPhone.getText().toString();
        if (!StringUtils.isMobileNO(phoneNum)) {
            ToastUtils.showToast(this, "手机号不合法，请重新输入");
            mEtPhone.requestFocus();
            mEtPhone.setSelection(phoneNum.length());
            return;
        }
        getVeri(phoneNum);


    }

    private void getVeri(String phoneNum) {


    }

    private void registUser() {

    }

    public static void show(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }
}
