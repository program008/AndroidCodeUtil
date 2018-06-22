package com.uurobot.androidcodeutil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uurobot.androidcodeutil.customview.ViewFlipperActivity;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void btnViewFlipper(View view) {
		startActivity(new Intent(this, ViewFlipperActivity.class));
	}
}
