package com.llc.framework.ui.base;

import android.os.Bundle;
import android.view.Window;

/**
 * @author liliuchen
 * @package com.crm.framework.ui.base
 * @fileName BaseNoTitleBarActivity
 * @date 2018/2/12
 * @emial 871898381@qq.com
 * @describe 无title
 * @company 棋至文化广播有限公司
 */
public abstract class BaseNoTitleBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
