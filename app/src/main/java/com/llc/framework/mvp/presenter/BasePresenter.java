package com.llc.framework.mvp.presenter;

import java.lang.ref.WeakReference;

/**
 * @author liliuchen
 * @package com.crm.framework.mvp.presenter
 * @fileName BasePresenter
 * @date 2018/2/12
 * @emial 871898381@qq.com
 * @describe 抽象类presenter
 * @company
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

    public T getView() {
        if (mViewRe != null && mViewRe.get() != null){
            return mViewRe.get();
        }
        return null;
    }
}
