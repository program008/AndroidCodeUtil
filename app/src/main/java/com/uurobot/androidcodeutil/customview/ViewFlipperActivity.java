package com.uurobot.androidcodeutil.customview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.uurobot.androidcodeutil.R;

public class ViewFlipperActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_flipper);
		ViewFlipper mViewFlipper = findViewById(R.id.view_flipper);
		for(int i=0;i<5;i++){
			View view = getLayoutInflater().inflate(R.layout.view_flipper_content,null);
			TextView textView = view.findViewById(R.id.tv_title);
			textView.setText("AndroidCodeUtil-0"+i);
			mViewFlipper.addView(view);
		}
		mViewFlipper.setFlipInterval(2000);
		mViewFlipper.startFlipping();
	}
}
