package com.syro.blepulltoscan.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.syro.blepulltoscan.util.BleUtil;
import com.syro.blepulltoscan.util.LogUtil;
import com.syro.blepulltoscan.util.ToastUtil;

/**
 * Created by Syro on 2016-02-29.
 */
public class BaseActivity extends Activity {
	private static final boolean DEBUG = false;
	protected Context mContext;
	protected static BleUtil mBleUtil;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) showLog("BaseActivity.onCreate()");
		if (DEBUG) showLog("sub-class of abstract class Window = "
				+ String.valueOf(getWindow()));//查找抽象类Window的子类是谁
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//强制竖屏
		mContext = getApplicationContext();
		mBleUtil = BleUtil.getInstance(this);
	}

	protected void toActivity(Class<? extends Activity> tarActivity) {
		Intent intent = new Intent(this, tarActivity);
		startActivity(intent);
	}

	protected void showLog(String msg) {
		LogUtil.show(msg);
	}

	protected void showToast(String msg) {
		ToastUtil.show(this, msg, Toast.LENGTH_SHORT);
	}
}
