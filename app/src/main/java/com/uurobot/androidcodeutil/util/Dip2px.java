package com.uurobot.androidcodeutil.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by tao.liu on 2018/7/3.
 * dip 转 px
 */
public class Dip2px {

	private int dip2px(Context context, float dpValue) {
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		int px = (int) (dpValue * (metrics.densityDpi / 160f));
		return px;
	}
}
