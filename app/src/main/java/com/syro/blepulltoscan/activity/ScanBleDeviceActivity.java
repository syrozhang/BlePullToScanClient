package com.syro.blepulltoscan.activity;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.syro.blepulltoscan.adapter.DeviceListAdapter;
import com.syro.blepulltoscan.R;
import com.syro.blepulltoscan.util.TitleBuilder;
import com.syro.blepulltoscan.util.ToastUtil;


public class ScanBleDeviceActivity extends BaseActivity {
	private static final boolean DEBUG = true;
	private PullToRefreshListView mPtrListView;
	private DeviceListAdapter mDeviceListAdapter;
	private int mBackKeyCnt = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_device);//把布局文件实例化为View并填充到Activity中
		if (DEBUG) showLog("ScanBleDeviceActivity.onCreate()");

		initView();
		mBleUtil.initBle();
	}

	@Override
	protected void onStart() {
		super.onStart();
		if (DEBUG) showLog("ScanBleDeviceActivity.onStart()");
		mBleUtil.enableBle();
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (DEBUG) showLog("ScanBleDeviceActivity.onResume()");
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (DEBUG) showLog("ScanBleDeviceActivity.onPause()");
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (DEBUG) showLog("ScanBleDeviceActivity.onStop()");
		mBleUtil.stopBleScan();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (DEBUG) showLog("ScanBleDeviceActivity.onDestroy()");
		mBleUtil.disableBle();
	}

	private void initView() {
		new TitleBuilder(this)
				.setTitleText(getString(R.string.home_title))
				.setLeftImage(R.drawable.app_logo);

		mPtrListView = (PullToRefreshListView) findViewById(R.id.ptr_lv);
		mDeviceListAdapter = new DeviceListAdapter(this, R.layout.lv_item_device_found);
		mPtrListView.setAdapter(mDeviceListAdapter);
		mPtrListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				new AsyncTask<Void, Void, Void>() {
					@Override // invoked on background thread
					protected Void doInBackground(Void... params) {
						mBleUtil.startBleScan();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						return null;
					}

					@Override // invoked on UI thread
					protected void onPostExecute(Void aVoid) {
						super.onPostExecute(aVoid);
						mBleUtil.stopBleScan();
						mDeviceListAdapter.clearItems();
						mDeviceListAdapter.addItems(mBleUtil.getResponseData().getObjList());
						mPtrListView.onRefreshComplete();
					}
				}.execute();

			}
		});
		mPtrListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				BluetoothDevice bluetoothDevice =
						(BluetoothDevice) mDeviceListAdapter.getItem(position - 1);
				if (bluetoothDevice != null) {
					Intent intent = new Intent(ScanBleDeviceActivity.this, DisplayServiceActivity.class);
					intent.putExtra("name", bluetoothDevice.getName());
					intent.putExtra("addr", bluetoothDevice.getAddress());
					startActivity(intent);
				}
			}
		});
	}

	@Override
	public void finish() {
		if (mBackKeyCnt == 2) {
			super.finish();
		} else {
			mBackKeyCnt++;
			ToastUtil.show(this, "再按一次退出程序", Toast.LENGTH_SHORT);
		}
	}
}
