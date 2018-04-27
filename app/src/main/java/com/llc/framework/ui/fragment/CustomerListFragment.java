package com.llc.framework.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llc.framework.R;
import com.llc.framework.mvp.presenter.BasePresenter;

/**
 *@package  com.crm.framework.ui.fragment
 *@fileName CustomerListFragment
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 客户列表模块
 *@company  棋至文化广播有限公司
 */
public class CustomerListFragment extends BaseFragment {


    public CustomerListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_list, container, false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
