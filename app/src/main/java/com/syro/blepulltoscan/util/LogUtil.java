package com.syro.blepulltoscan.util;

import android.util.Log;

/**
 * Created by Syro on 2016-01-30.
 */
public class LogUtil {
	private static final String TAG = "SyroZhang";

	public static void show(String str) {
		show(TAG, str, Log.VERBOSE);
	}

	public static void show(String tag, String msg, int level) {
		switch (level) {
			case Log.VERBOSE:
				Log.v(tag, msg);
				break;
			case Log.DEBUG:
				Log.d(tag, msg);
				break;
			case Log.INFO:
				Log.i(tag, msg);
				break;
			case Log.WARN:
				Log.w(tag, msg);
				break;
			case Log.ERROR:
				Log.e(tag, msg);
				break;
			default:
				Log.i(tag, msg);
				break;
		}
	}
}
