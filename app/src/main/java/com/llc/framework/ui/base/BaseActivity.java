package com.llc.framework.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.llc.framework.mvp.presenter.BasePresenter;

/**
 * @author liliuchen
 * @package com.crm.framework.ui.base
 * @fileName BaseActivity
 * @date 2018/2/12
 * @emial 871898381@qq.com
 * @describe 实现mvp的activity
 * @company
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    public T presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        try {
            presenter.attatch((V) this);
        } catch (NullPointerException e) {

        }

    }

    protected abstract T createPresenter();

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();


    }

}
