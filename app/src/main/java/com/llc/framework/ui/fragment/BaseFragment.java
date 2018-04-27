package com.llc.framework.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.llc.framework.mvp.presenter.BasePresenter;

/**
 *@package  com.crm.framework.ui.fragment
 *@fileName BaseFragment
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 实现mvp的fragment
 *@company  棋至文化广播有限公司
 */
public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    public T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        try {
            presenter.attatch((V) this);
        } catch (NullPointerException e) {

        }

    }

    @Override
    public void onDestroy() {
        try {
            presenter.detach();
        } catch (NullPointerException e) {

        }
        super.onDestroy();
    }

    protected abstract T createPresenter();
}
