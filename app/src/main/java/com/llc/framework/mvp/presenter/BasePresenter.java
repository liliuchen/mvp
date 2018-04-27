package com.llc.framework.mvp.presenter;

import java.lang.ref.WeakReference;

/**
 *@package  com.crm.framework.mvp.presenter
 *@fileName BasePresenter
 *@date     2018/2/12
 *@author   liliuchen
 *@emial    871898381@qq.com
 *@describe 抽象类presenter
 *@company  棋至文化广播有限公司
 */

public class BasePresenter<T> {
    //view 层
    WeakReference<T> mViewRe;

    public void attatch(T view) {
        mViewRe = new WeakReference<T>(view);
    }

    public void detach() {
        mViewRe.clear();
    }
}
