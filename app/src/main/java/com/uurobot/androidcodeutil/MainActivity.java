package com.uurobot.androidcodeutil;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.uurobot.androidcodeutil.customview.ViewFlipperActivity;
import com.uurobot.androidcodeutil.util.CheckNetUtil;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void btnViewFlipper(View view) {
		startActivity(new Intent(this, ViewFlipperActivity.class));
	}

	public void checkNet(View view) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				String ping = CheckNetUtil.Ping("www.baidu.com");
				Log.e("MainActivity", ping);
			}
		});

		thread.start();

	}
}
