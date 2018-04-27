package com.llc.framework.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llc.framework.R;
import com.llc.framework.mvp.presenter.BasePresenter;

/**
 *@package  com.crm.framework.ui.fragment
 *@fileName WorksFragment
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 工作台/首页
 *@company  棋至文化广播有限公司
 */
public class WorksFragment extends BaseFragment{


    public WorksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_works, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();



    }

    private void initView() {

    }

    private void initListener() {

    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
}
