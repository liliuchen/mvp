package com.llc.framework.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import com.llc.framework.R;


/**
 * @author liliuchen
 * @package com.crm.framework.utils
 * @fileName DialogUtils
 * @date 2018/3/16
 * @emial 871898381@qq.com
 * @describe 弹出模糊加载框
 * @company
 */
public class DialogUtils {
    Context context;
    private TextView textView;

    public DialogUtils(Context context) {
        this.context = context;
    }

    Handler handler = new Handler();
    Dialog mLoadDialog = null;

    public void showLoadProgress(String msg) {
        if (mLoadDialog == null) {
            mLoadDialog = new Dialog(context, R.style.NoTitleDialog);
            mLoadDialog.setCanceledOnTouchOutside(false);
            mLoadDialog.setContentView(R.layout.loading_view_bg_dark);
            textView = mLoadDialog.findViewById(R.id.loading_view_tv_text);
        }
        textView.setText(msg);
        mLoadDialog.show();
    }

    public void dismissLoadProgress() {
        if (mLoadDialog != null && mLoadDialog.isShowing()) {
            mLoadDialog.dismiss();
        }

    }

}
