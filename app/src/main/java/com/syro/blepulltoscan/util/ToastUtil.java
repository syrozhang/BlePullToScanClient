package com.syro.blepulltoscan.util;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
	private static Toast mToast;

	public static void show(Context context, CharSequence text, int duration) {
		if(mToast == null) {
			mToast = Toast.makeText(context, text, duration);
		} else {
			mToast.setText(text);
			mToast.setDuration(duration);
		}
		mToast.show();
	}
}
