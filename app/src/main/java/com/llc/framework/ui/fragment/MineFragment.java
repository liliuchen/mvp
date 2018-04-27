package com.llc.framework.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llc.framework.R;
import com.llc.framework.mvp.presenter.BasePresenter;

/**
 *@package  com.crm.framework.ui.fragment
 *@fileName MineFragment
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 我的
 *@company  棋至文化广播有限公司
 */
public class MineFragment extends BaseFragment {


    public MineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mine, container, false);
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
