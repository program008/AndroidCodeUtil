package com.uurobot.androidcodeutil;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

	private static final String TAG = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		String localMacAddress = getLocalMacAddress();
		Log.d(TAG, "当前mac-> " + localMacAddress);

	}

	@SuppressLint("HardwareIds")
	public String getLocalMacAddress() {

		WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

		if (wifi != null) {

			WifiInfo info = wifi.getConnectionInfo();
			return info.getMacAddress();
		}
		return null;
	}
}
