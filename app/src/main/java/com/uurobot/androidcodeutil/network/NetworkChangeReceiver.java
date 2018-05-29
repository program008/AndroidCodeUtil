package com.uurobot.androidcodeutil.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * @author: tao.liu
 * @version: v1.0
 * @package: com.uurobot.androidcodeutil.network
 * @fileName: NetworkChangeReceiver
 * @description: 监听网络改变
 * @date: 2018/5/29
 * @email: tao.liu@uurobot.cn
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
	//android.permission.ACCESS_NETWORK_STATE 需要添加权限
	//"android.net.conn.CONNECTIVITY_CHANGE"
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
			switch (activeNetworkInfo.getType()) {
				case ConnectivityManager.TYPE_MOBILE:
					Toast.makeText(context, "正在使用2G/3G/4G网络", Toast.LENGTH_SHORT).show();
					break;
				case ConnectivityManager.TYPE_WIFI:
					Toast.makeText(context, "正在使用wifi上网", Toast.LENGTH_SHORT).show();
					break;
				default:
					break;
			}
		}
		else {
			Toast.makeText(context, "当前无网络连接", Toast.LENGTH_SHORT).show();
		}
	}
}
