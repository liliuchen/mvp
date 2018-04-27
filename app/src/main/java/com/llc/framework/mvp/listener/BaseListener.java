package com.llc.framework.mvp.listener;

/**
 * @author Administrator
 * @package com.crm.framework.mvp.listener
 * @fileName BaseListener
 * @date 2018/4/24.
 * @emial 871898381@qq.com
 * @describe TODO
 * @company 棋至文化广播有限责任公司
 */

public interface BaseListener {
    void onError(int requestCode,Throwable throwable);
}
