package com.llc.framework.mvp.view;

/**
 * @author liliuchen
 * @package com.crm.framework.mvp.view
 * @fileName BaseView
 * @date 2018/2/12
 * @emial 871898381@qq.com
 * @describe View基类
 * @company 棋至文化广播有限公司
 */
public interface BaseView {
    void showLoading(int requestCode);

    void hideLoading(int requestCode);

    void onError(int requestCode, Throwable throwable);
}
