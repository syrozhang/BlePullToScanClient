package com.syro.blepulltoscan.activity;

import android.os.Bundle;
import android.os.Handler;

import com.syro.blepulltoscan.R;

/**
 * Created by Syro on 2016-03-08.
 */
public class SplashActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logo);//把布局文件实例化为View并填充到Activity中
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				SplashActivity.this.toActivity(ScanBleDeviceActivity.class);
				SplashActivity.this.finish();
			}
		}, 888);
	}
}
