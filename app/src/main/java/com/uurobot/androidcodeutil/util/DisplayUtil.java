package com.uurobot.androidcodeutil.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Created by tao.liu on 2018/7/19.
 * 屏幕适配工具
 */
public class DisplayUtil {

	private static float sNonCompatDensity;

	private static float sNonCompatScaledDensity;

	public static void setCustomDensity(Activity activity, final Application application,String orientation) {
		DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
		if (sNonCompatDensity == 0) {
			sNonCompatDensity = appDisplayMetrics.density;
			sNonCompatScaledDensity = appDisplayMetrics.scaledDensity;
			application.registerComponentCallbacks(new ComponentCallbacks() {
				@Override
				public void onConfigurationChanged(Configuration newConfig) {
					if (newConfig != null && newConfig.fontScale > 0) {
						sNonCompatScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
					}
				}

				@Override
				public void onLowMemory() {

				}
			});
		}
		//获取状态栏高度
		int barHeight = AppUtils.getStatusBarHeight(activity);
		//float targetDensity = appDisplayMetrics.widthPixels * 1.0f / 360;
		float targetDensity;
		if (orientation.equals("height")) {
			targetDensity = (appDisplayMetrics.heightPixels - barHeight) / 667f;
		}
		else {
			targetDensity = appDisplayMetrics.widthPixels / 360f;
		}

		float targetScaledDensity = targetDensity * (sNonCompatScaledDensity / sNonCompatDensity);
		int targetDensityDpi = (int) (targetDensity * 160);

		appDisplayMetrics.density = targetDensity;
		appDisplayMetrics.scaledDensity = targetScaledDensity;
		appDisplayMetrics.densityDpi = targetDensityDpi;

		DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
		activityDisplayMetrics.density = targetDensity;
		activityDisplayMetrics.scaledDensity = targetScaledDensity;
		activityDisplayMetrics.densityDpi = targetDensityDpi;
	}

	private static float appDensity;

	private static float appScaledDensity;

	private static DisplayMetrics appDisplayMetrics;

	public static void setDensity(final @NonNull Application application) {
		//获取application的DisplayMetrics
		appDisplayMetrics = application.getResources().getDisplayMetrics();

		if (appDensity == 0) {
			//初始化的时候赋值
			appDensity = appDisplayMetrics.density;
			appScaledDensity = appDisplayMetrics.scaledDensity;

			//添加字体变化的监听
			application.registerComponentCallbacks(new ComponentCallbacks() {
				@Override
				public void onConfigurationChanged(Configuration newConfig) {
					//字体改变后,将appScaledDensity重新赋值
					if (newConfig != null && newConfig.fontScale > 0) {
						appScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
					}
				}

				@Override
				public void onLowMemory() {
				}
			});
		}
	}

	//此方法在BaseActivity中做初始化(如果不封装BaseActivity的话,直接用下面那个方法就好了)
	public static void setDefault(Activity activity) {
		setAppOrientation(activity, AppUtils.WIDTH);
	}

	//此方法用于在某一个Activity里面更改适配的方向
	public static void setOrientation(Activity activity, String orientation) {
		setAppOrientation(activity, orientation);
	}

	/**
	 * targetDensity
	 * targetScaledDensity
	 * targetDensityDpi
	 * 这三个参数是统一修改过后的值
	 * <p>
	 * orientation:方向值,传入width或height
	 */
	private static void setAppOrientation(@Nullable Activity activity, String orientation) {

		float targetDensity;

		//获取状态栏高度
		int barHeight = AppUtils.getStatusBarHeight(activity);
		if (orientation.equals("height")) {
			targetDensity = (appDisplayMetrics.heightPixels - barHeight) / 667f;
		}
		else {
			targetDensity = appDisplayMetrics.widthPixels / 360f;
		}

		float targetScaledDensity = targetDensity * (appScaledDensity / appDensity);
		int targetDensityDpi = (int) (160 * targetDensity);

		/**
		 *
		 * 最后在这里将修改过后的值赋给系统参数
		 *
		 * 只修改Activity的density值
		 */

		DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
		activityDisplayMetrics.density = targetDensity;
		activityDisplayMetrics.scaledDensity = targetScaledDensity;
		activityDisplayMetrics.densityDpi = targetDensityDpi;
	}
}
