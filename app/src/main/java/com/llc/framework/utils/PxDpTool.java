package com.llc.framework.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class PxDpTool {

	public static int dpToPx(Resources res, int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				res.getDisplayMetrics());
	}

	/**
	 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取屏幕分辨率 宽
	 *
	 * @param context
	 * @return
	 */
	public static int getPx(Context context) {
		// int dw =
		// ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
		return context.getResources().getDisplayMetrics().widthPixels;// 获得手机屏幕的宽度
	}

	/**
	 * 获取屏幕分辨率 高
	 *
	 * @param context
	 * @return
	 */
	public static int getPy(Context context) {
		// int dw =
		// ((Activity)context).getWindowManager().getDefaultDisplay().getHeight();
		return context.getResources().getDisplayMetrics().heightPixels;// 获得手机屏幕的宽度
	}

}
