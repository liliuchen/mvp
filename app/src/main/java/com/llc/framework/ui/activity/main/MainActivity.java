package com.llc.framework.ui.activity.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.llc.framework.R;
import com.llc.framework.manager.activity.ActivityManager;
import com.llc.framework.ui.fragment.CustomerListFragment;
import com.llc.framework.ui.fragment.MineFragment;
import com.llc.framework.ui.fragment.ProductFragment;
import com.llc.framework.ui.fragment.ScheduleFragment;
import com.llc.framework.ui.fragment.WorksFragment;
import com.llc.framework.utils.ToastUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
/**
 *@package  com.crm.framework.ui.activity.main
 *@fileName MainActivity
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 主界面
 *@company  棋至文化广播有限公司
 */

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_flayout)
    FrameLayout mFlayout;
    @BindView(R.id.activity_main_rg_group)
    RadioGroup mRgTabGroup;
    @BindViews({R.id.activity_main_rb_works, R.id.activity_main_rb_schedule, R.id.activity_main_rb_client_list,R.id.activity_main_rb_product,R.id.activity_main_rb_mine})
    List<RadioButton> mRBList;
    Fragment mWorkbenchFragment;
    Fragment mScheduleFragment;
    Fragment mCustomerFragment;
    Fragment mProductFragment;
    Fragment mMineFragment;
    String[] mTabTags = {"首页", "日程", "客户列表", "产品", "我的"};
    @BindView(R.id.title_layout_tv_title)
    TextView mTvTitle;
    private FragmentManager mFragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
        setListener();
        cutTab(0);

    }

    private void setListener() {
        mRgTabGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.activity_main_rb_works:
                        cutTab(0);
                        break;
                    case R.id.activity_main_rb_schedule:
                        cutTab(1);
                        break;
                    case R.id.activity_main_rb_client_list:
                        cutTab(2);
                        break;
                    case R.id.activity_main_rb_product:
                        cutTab(3);
                        break;
                    case R.id.activity_main_rb_mine:
                        cutTab(4);
                        break;
                }
            }
        });
    }

    private void initData() {
        mFragmentManager = getSupportFragmentManager();
    }

    public static void show(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void cutTab(int index) {
        mTvTitle.setText(mTabTags[index]);
        hideAllFragment();
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        mRBList.get(index).setChecked(true);
        switch (index) {
            case 0:
                if (mWorkbenchFragment == null) {
                    mWorkbenchFragment = new WorksFragment();
                    ft.add(R.id.activity_main_flayout, mWorkbenchFragment, mTabTags[index]);
                } else {
                    ft.show(mWorkbenchFragment);
                }
                break;
            case 1:
                if (mScheduleFragment == null) {
                    mScheduleFragment = new ScheduleFragment();
                    ft.add(R.id.activity_main_flayout, mScheduleFragment, mTabTags[index]);
                } else {
                    ft.show(mScheduleFragment);
                }
                break;
            case 2:
                if (mCustomerFragment == null) {
                    mCustomerFragment = new CustomerListFragment();
                    ft.add(R.id.activity_main_flayout, mCustomerFragment, mTabTags[index]);
                } else {
                    ft.show(mCustomerFragment);
                }
                break;
            case 3:
                if (mProductFragment == null) {
                    mProductFragment = new ProductFragment();
                    ft.add(R.id.activity_main_flayout, mProductFragment, mTabTags[index]);
                } else {
                    ft.show(mProductFragment);
                }
                break;
            case 4:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    ft.add(R.id.activity_main_flayout, mMineFragment, mTabTags[index]);
                } else {
                    ft.show(mMineFragment);
                }
                break;
        }
        ft.commit();

    }

    private void hideAllFragment() {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        if (mWorkbenchFragment != null) {
            ft.hide(mWorkbenchFragment);
        }
        if (mScheduleFragment != null) {
            ft.hide(mScheduleFragment);
        }
        if (mCustomerFragment != null) {
            ft.hide(mCustomerFragment);
        }
        if (mProductFragment != null) {
            ft.hide(mProductFragment);
        }
        if (mMineFragment != null) {
            ft.hide(mMineFragment);
        }
        ft.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {

        }
        return super.onKeyDown(keyCode, event);
    }

    Long mExitTime = 0L;

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - mExitTime > 2000) {
                mExitTime = System.currentTimeMillis();
                ToastUtils.showToast(this, "再按一次返回键退出");
                return true;
            } else {
                ActivityManager.getInstance().exit();
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
