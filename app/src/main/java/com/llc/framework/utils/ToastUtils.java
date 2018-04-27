package com.llc.framework.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by on 2016/11/25.
 * <p>
 * Title: maljob.com
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company:上海广亿信息技术有限公司
 * </p>
 *
 * @author：李刘忱
 * @version:
 * @since：2016/11/25
 */
public class ToastUtils {
    private static Toast mToast = null;
    public static int LENGTH_SHORT = Toast.LENGTH_SHORT;
    public static int LENGTH_LONG = Toast.LENGTH_LONG;

    /**
     * 防止多次显示提示时间过长工具类
     * Toast请使用这个替代
     *
     * @param context
     * @param text
     * @param duration
     */
    public static void showToast(Context context, String text, int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text, duration);
        } else {
            mToast.setText(text);
            mToast.setDuration(duration);
        }
        mToast.show();
    }
    public static void showToast(Context context, String text) {
        if (mToast == null) {
            mToast = Toast.makeText(context, text,LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(LENGTH_SHORT);
        }
        mToast.show();
    }

}
