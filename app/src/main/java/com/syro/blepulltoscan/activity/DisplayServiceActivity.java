package com.syro.blepulltoscan.activity;

import android.animation.ObjectAnimator;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.syro.blepulltoscan.R;
import com.syro.blepulltoscan.util.BleUtil;
import com.syro.blepulltoscan.util.GattProfile;
import com.syro.blepulltoscan.util.TitleBuilder;
import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Syro on 2016-02-29.
 */
public class DisplayServiceActivity extends BaseActivity {
	private static final boolean DEBUG = true;
	private View mStatusLine;
	private ColorfulRingProgressView mRingProgress;
	private TextView mBattVolt;
	private TextView mBattPercent;
	private TextView mBattOutputCurtTitle;
	private TextView mBattOutputCurt;
	private View mView0;
	private TextView mBattOutputVoltTitle;
	private TextView mBattOutputVolt;
	private View mView1;
	private String mDvcName;
	private String mDvcAddr;
	private BluetoothGattCharacteristic mNotifiedCharacteristic;
	private BluetoothGattCharacteristic mTargetCharacteristic;
	private static Timer mTimerCnt;
	private boolean isConnected;
	private float mPercent;
	private double mBattV;
	private double mBattOutV;
	private double mBattOutC;
	public static final int REQUEST_CODE = 0x1;
	public static final int REFRESH_DATA = 0x2;


	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case REFRESH_DATA:
					mRingProgress.setPercent(Math.round(mPercent));
					DecimalFormat decimalFormat = new DecimalFormat("0.000");
					mBattPercent.setText(Math.round(mPercent) + "%");
					mBattVolt.setText(decimalFormat.format(mBattV) + "V");
					if (mBattOutV > 0) {
						mBattOutputVoltTitle.setVisibility(View.VISIBLE);
						mBattOutputVolt.setVisibility(View.VISIBLE);
						mView0.setVisibility(View.VISIBLE);
						mBattOutputVoltTitle.setText("输出电压:");
						mBattOutputVolt.setText(decimalFormat.format(mBattOutV) + "V");
						LinearLayout.LayoutParams llp =
							new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
						mView0.setLayoutParams(llp);
					} else {
						mBattOutputVoltTitle.setVisibility(View.INVISIBLE);
						mBattOutputVolt.setVisibility(View.INVISIBLE);
						mView0.setVisibility(View.INVISIBLE);
					}
					if (mBattOutC > 0) {
						mBattOutputCurtTitle.setVisibility(View.VISIBLE);
						mBattOutputCurt.setVisibility(View.VISIBLE);
						mView1.setVisibility(View.VISIBLE);
						mBattOutputCurtTitle.setText("输出电流:");
						mBattOutputCurt.setText(decimalFormat.format(mBattOutC) + "A");
						LinearLayout.LayoutParams llp =
							new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
						mView1.setLayoutParams(llp);
					} else {
						mBattOutputCurtTitle.setVisibility(View.INVISIBLE);
						mBattOutputCurt.setVisibility(View.INVISIBLE);
						mView1.setVisibility(View.INVISIBLE);
					}
					break;
			}
		}
	};

	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (BleUtil.GATT_CONNECTED.equals(action)) {
				isConnected = true;
				//show("连接成功");
				mStatusLine.setBackgroundColor(Color.GREEN);
			} else if (BleUtil.GATT_DISCONNECTED.equals(action)) {
				isConnected = false;
				//show("连接失败");
				mPercent = 0;
				mBattV = 0;
				mBattOutV = 0;
				mBattOutC = 0;
				mStatusLine.setBackgroundColor(Color.RED);
			} else if (BleUtil.GATT_SERVICE_DISCOVERED.equals(action)) {
				mTargetCharacteristic = searchCharacteristic(GattProfile.NORDIC_UART_RX);
				handleCharacteristic(mTargetCharacteristic);
			} else if (BleUtil.ACTION_CHARAC_VALUE_GET_SUCCESS.equals(action)) {
				byte[] uartData = intent.getByteArrayExtra(BleUtil.CHARACTERISTIC_VALUE);
				parseUartData(uartData);
				startTimer();
			} else if (BleUtil.ACTION_CHARAC_VALUE_WRITE_SUCCESS.equals(action)) {
				mBattVolt.setText("Data: write successfully");
			}
		}
	};

	private void parseUartData(byte[] uartData) {
		String uartDataStr = new String(uartData);
		uartDataStr = uartDataStr.replaceAll("\r|\n", "");
		String[] ppInfo = uartDataStr.split(",");
		int v0 = Integer.parseInt(ppInfo[0]);
		int a0 = Integer.parseInt(ppInfo[1]);
		int v1 = Integer.parseInt(ppInfo[2]);
		mBattV = v0 * 3.3 / 4096;
		mBattOutC = a0 * 3.3 / (4096 * 50 * 0.01);
		mBattOutV = v1 * 3.3 / 4096;
		mPercent = (float) ((mBattV - 3.0) / (4.1 - 3.0) * 100);
		if (mPercent < 0) {
			mPercent = 0;
		} else if (mPercent > 100) {
			mPercent = 100;
		}

	}

	private void handleCharacteristic(BluetoothGattCharacteristic targetCharac) {
		int characProper = targetCharac.getProperties();// 返回Characteristic的Properties值(read/write/notify)

		// Characteristic是Notify属性
		if ((characProper & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
			if (mNotifiedCharacteristic != null) {
				mBleUtil.setCharacteristicNotification(mNotifiedCharacteristic, false);
				mNotifiedCharacteristic = null;
			}
			mNotifiedCharacteristic = targetCharac;
			mBleUtil.setCharacteristicNotification(targetCharac, true);
			return;
		}

		// Characteristic是Read属性
		if ((characProper & BluetoothGattCharacteristic.PROPERTY_READ) > 0) {
			if (mNotifiedCharacteristic != null) {
				mBleUtil.setCharacteristicNotification(mNotifiedCharacteristic, false);
				mNotifiedCharacteristic = null;
			}
			mBleUtil.readCharacteristic(targetCharac);
			return;
		}

		// Characteristic是Write属性
		if ((characProper & BluetoothGattCharacteristic.PROPERTY_WRITE) > 0) {
			Intent intent = new Intent(DisplayServiceActivity.this, WriteCharacValueActivity.class);
			intent.putExtra("CharacName", GattProfile.getInfo(targetCharac.getUuid().toString(), "Unknown Characteristic"));
			startActivityForResult(intent, REQUEST_CODE);// 打开一个子Activity来接收输入
			return;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case REQUEST_CODE: // 请求码
				switch (resultCode) {
					case RESULT_OK: // 相应码

						break;
				}
				break;
		}
	}

	public BluetoothGattCharacteristic searchCharacteristic(String characUuid) {
		String uuid;
		BluetoothGattCharacteristic targetCharacteristic = null;
		List<BluetoothGattService> allGattServices = mBleUtil.getAllServices();
		for (BluetoothGattService gattService : allGattServices) {
			uuid = gattService.getUuid().toString();
			if (GattProfile.NORDIC_UART.equals(uuid)) {
				List<BluetoothGattCharacteristic> allCharacs = gattService.getCharacteristics(); // 获取Service的所有characteristics
				for (BluetoothGattCharacteristic charac : allCharacs) {
					uuid = charac.getUuid().toString();
					if (characUuid.equals(uuid)) {
						targetCharacteristic = charac;
					}
				}
			}
		}
		return targetCharacteristic;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disp_service);//把布局文件实例化为View并填充到Activity中
		if (DEBUG) showLog("DisplayServiceActivity.onCreate()");

		Intent intent = getIntent();
		mDvcName = intent.getStringExtra("name");
		mDvcAddr = intent.getStringExtra("addr");
		initView();
		mBleUtil.connectGatt(mDvcAddr);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (DEBUG) showLog("DisplayServiceActivity.onResume()");
		initBroadcastReceiver();
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (DEBUG) showLog("DisplayServiceActivity.onPause()");
		unregisterReceiver(mBroadcastReceiver);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (DEBUG) showLog("DisplayServiceActivity.onDestroy()");
		mBleUtil.disconnectGatt();
		mBleUtil.closeGatt();
		stopTimer();
	}

	private void initView() {
		new TitleBuilder(this)
			.setTitleText("电源包信息")
			.setLeftImage(R.drawable.app_logo)
			.setRightText("重连")
			.setRightOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mBleUtil.disconnectGatt();
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
					mBleUtil.connectGatt(mDvcAddr);
//                            }
//                        }, 1000);
				}
			});

		mStatusLine = findViewById(R.id.view_ble_con_status_line);
		mBattVolt = (TextView) findViewById(R.id.tv_batt_volt);
		mBattPercent = (TextView) findViewById(R.id.tv_batt_percent);
		mBattOutputVoltTitle = (TextView) findViewById(R.id.tv_output_volt_title);
		mBattOutputVolt = (TextView) findViewById(R.id.tv_output_volt);
		mView0 = findViewById(R.id.v0);
		mBattOutputCurtTitle = (TextView) findViewById(R.id.tv_output_curt_title);
		mBattOutputCurt = (TextView) findViewById(R.id.tv_output_curt);
		mView1 = findViewById(R.id.v1);
		mRingProgress = (ColorfulRingProgressView) findViewById(R.id.colorful_ring_progress);
		mRingProgress.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ObjectAnimator anim = ObjectAnimator.ofFloat(v, "Percent", 0, ((ColorfulRingProgressView) v).getPercent());
				anim.setInterpolator(new LinearInterpolator());
				anim.setDuration(1000);
				anim.start();
			}
		});
	}

	// 注册符合intentfilter的广播接收器
	private void initBroadcastReceiver() {
		IntentFilter inf = new IntentFilter();
		inf.addAction(BleUtil.GATT_CONNECTED);
		inf.addAction(BleUtil.GATT_DISCONNECTED);
		inf.addAction(BleUtil.GATT_SERVICE_DISCOVERED);
		inf.addAction(BleUtil.ACTION_CHARAC_VALUE_GET_SUCCESS);
		inf.addAction(BleUtil.ACTION_CHARAC_VALUE_WRITE_SUCCESS);
		registerReceiver(mBroadcastReceiver, inf);
	}

	private void startTimer() {
		if (mTimerCnt == null) {
			mTimerCnt = new Timer();
			mTimerCnt.schedule(new TimerTask() {
				@Override
				public void run() {
					mHandler.sendEmptyMessage(REFRESH_DATA);
				}
			}, 0, 1000);//每1000ms发送一次信号给handler
		}
	}

	private void stopTimer() {
		if (mTimerCnt != null) {
			mTimerCnt.cancel();
			mTimerCnt = null;
		}
	}
}
