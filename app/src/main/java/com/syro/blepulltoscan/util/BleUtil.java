package com.syro.blepulltoscan.util;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;


/**
 * Created by Syro on 2016-01-30.
 */
public class BleUtil {
	private static final boolean DEBUG = true;
	private ResponseData<BluetoothDevice> mResponseData =
			new ResponseData<BluetoothDevice>();
	private String mTargetAddress;
	public boolean isScanning;
	private Context mContext;
	private BluetoothManager mBluetoothManager;
	private BluetoothAdapter mBluetoothAdapter;
	private BluetoothGatt mBluetoothGatt;
	private BluetoothLeScanner mBluetoothLeScanner;
	public static final String CHARACTERISTIC_VALUE =
			"com.syro.api.CHARACTERISTIC_VALUE";
	public static final String GATT_CONNECTED =
			"com.syro.api.GATT_CONNECTED";
	public static final String GATT_DISCONNECTED =
			"com.syro.api.GATT_DISCONNECTED";
	public static final String GATT_SERVICE_DISCOVERED =
			"com.syro.api.GATT_SERVICE_DISCOVERED";

	public static final UUID UUID_HEART_RATE_MEASUREMENT =
			UUID.fromString(GattProfile.HEART_RATE_MEASUREMENT);
	public static final UUID UUID_BODY_SENSOR_LOCATION =
			UUID.fromString(GattProfile.BODY_SENSOR_LOCATION);
	public static final UUID UUID_BATTERY_LEVEL =
			UUID.fromString(GattProfile.BATTERY_LEVEL);
	public static final UUID UUID_TEMPERATURE_TYPE =
			UUID.fromString(GattProfile.TEMPERATURE_TYPE);
	public static final UUID UUID_NORDIC_UART_RX =
			UUID.fromString(GattProfile.NORDIC_UART_RX);

	public static final String ACTION_CHARAC_VALUE_GET_SUCCESS =
			"com.syro.api.ACTION_CHARAC_VALUE_READ_SUCCESS";
	public static final String ACTION_CHARAC_VALUE_WRITE_SUCCESS =
			"com.syro.api.ACTION_CHARAC_VALUE_WRITE_SUCCESS";

	// Singleton mode
	private static BleUtil mInstance;

	private BleUtil(Context context) {
		this.mContext = context;
	}

	public static BleUtil getInstance(Context context) {
		if (mInstance == null) {
			mInstance = new BleUtil(context);
		}
		return mInstance;
	}

	private ScanCallback mScanCallback = new ScanCallback() {
		@Override
		public void onScanResult(int callbackType, ScanResult result) {
			super.onScanResult(callbackType, result);
//            if (DEBUG) MyLog.show("BLE device founded...");
//            if (DEBUG) MyLog.show("callbackType = " + callbackType + ", result = " + result);
			if (callbackType == ScanSettings.CALLBACK_TYPE_ALL_MATCHES) {
				BluetoothDevice bluetoothDevice = result.getDevice();
				if (!mResponseData.getObjList().contains(bluetoothDevice)) {
					mResponseData.getObjList().add(bluetoothDevice);
				}
			}
		}

		@Override
		public void onBatchScanResults(List<ScanResult> results) {
			super.onBatchScanResults(results);
			if (DEBUG) MyLog.show("Batch results are delivered...");
		}

		@Override
		public void onScanFailed(int errorCode) {
			super.onScanFailed(errorCode);
			if (DEBUG) MyLog.show("Scan could not be started...");
		}
	};

	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
		// 发现BLE设备后的回调函数
		@Override
		public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
			//MyLog.show("BLE device founded...");
			if (!mResponseData.getObjList().contains(device)) {
				mResponseData.getObjList().add(device);
			}
		}
	};

	private final BluetoothGattCallback mBluetoothGattCallback = new BluetoothGattCallback() {
		// GATT连接的回调函数
		@Override
		public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
			super.onConnectionStateChange(gatt, status, newState);
			if (DEBUG)
				MyLog.show("gatt = " + gatt + ", status = " + status + ", newState = " + newState);
			if (newState == BluetoothProfile.STATE_CONNECTED) {
				if (DEBUG) MyLog.show("Gatt server connected...");
				broadcast(GATT_CONNECTED);
				mBluetoothGatt.discoverServices();
			} else if (newState == BluetoothProfile.STATE_DISCONNECTED) {
				if (DEBUG) MyLog.show("Gatt server disconnected...");
				broadcast(GATT_DISCONNECTED);
			} else if (newState == BluetoothProfile.STATE_DISCONNECTING) {
				if (DEBUG) MyLog.show("Gatt server disconnecting...");
			}
		}

		@Override
		public void onServicesDiscovered(BluetoothGatt gatt, int status) {
			super.onServicesDiscovered(gatt, status);
			if (status == BluetoothGatt.GATT_SUCCESS) {
				if (DEBUG) MyLog.show("Gatt service discovered...");
				broadcast(GATT_SERVICE_DISCOVERED);
			} else {
				if (DEBUG) MyLog.show("Gatt service didn't discovered...");
			}
		}

		@Override
		public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
			super.onCharacteristicChanged(gatt, characteristic);
			if (DEBUG) MyLog.show("Characteristic value updated...");
			broadcast(ACTION_CHARAC_VALUE_GET_SUCCESS, characteristic);
		}

		@Override
		public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
			super.onCharacteristicRead(gatt, characteristic, status);
			if (status == BluetoothGatt.GATT_SUCCESS) {
				if (DEBUG) MyLog.show("Read characteristic value successfully...");
				broadcast(ACTION_CHARAC_VALUE_GET_SUCCESS, characteristic);
			} else {
				if (DEBUG) MyLog.show("Read characteristic value failed...");
			}
		}

		@Override
		public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
			super.onCharacteristicWrite(gatt, characteristic, status);
			if (status == BluetoothGatt.GATT_SUCCESS) {
				if (DEBUG) MyLog.show("Write characteristic value successfully...");
				broadcast(ACTION_CHARAC_VALUE_WRITE_SUCCESS);
			} else {
				if (DEBUG) MyLog.show("Write characteristic value failed...");
			}
		}
	};

	public void initBle() {
		if (DEBUG) MyLog.show("BleUtil.initBle()");
		// 检测手机是否支持BLE
		if (!mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
			if (DEBUG) MyLog.show("BLE is not supported...");
			return;
		}

		// 获取Bluetooth adapter，对于API level 18及以上，通过BluetoothManager来获取BluetoothAdapter的引用
		mBluetoothManager = (BluetoothManager) mContext.getSystemService(mContext.BLUETOOTH_SERVICE);
		if (mBluetoothManager == null) {
			if (DEBUG) MyLog.show("Unable to init BluetoothManager");
			return;
		}

		mBluetoothAdapter = mBluetoothManager.getAdapter();
		if (mBluetoothAdapter == null) {
			if (DEBUG) MyLog.show("BLE is not supported...");
			return;
		}
	}

	public void startBleScan() {
		if (DEBUG) MyLog.show("BleUtil.startBleScan()");
		isScanning = true;
		mResponseData.getObjList().clear();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mBluetoothLeScanner = mBluetoothAdapter.getBluetoothLeScanner();
			mBluetoothLeScanner.startScan(mScanCallback); // 开始一次BLE设备扫描
		} else {
			mBluetoothAdapter.startLeScan(mLeScanCallback);// 开始一次BLE设备扫描
			if (DEBUG) MyLog.show("Android SDK version is lower than Lollipop...");
		}
	}

	public void stopBleScan() {
		if (DEBUG) MyLog.show("BleUtil.stopBleScan()");
		isScanning = false;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mBluetoothLeScanner.stopScan(mScanCallback); // 停止扫描BLE设备
		} else {
			mBluetoothAdapter.stopLeScan(mLeScanCallback);// 停止扫描BLE设备
			if (DEBUG) MyLog.show("Android SDK version is lower than Lollipop...");
		}
	}

	public ResponseData<BluetoothDevice> getResponseData() {
		return mResponseData;
	}

	public boolean isScanning() {
		return isScanning;
	}

	public void enableBle() {
		if (DEBUG) MyLog.show("BleUtil.enableBle()");
		// 打开Bluetooth Adapter
		if (!mBluetoothAdapter.isEnabled()) {
			//Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
			//startActivity(intent);
			mBluetoothAdapter.enable();// 直接打开手机Bluetooth功能
		}
	}

	public void disableBle() {
		if (DEBUG) MyLog.show("BleUtil.disableBle()");
		if (mBluetoothAdapter != null) {
			mBluetoothAdapter.disable();// 关闭手机Bluetooth功能
		}
	}

	public void connectGatt(String rmtAddress) {
		if (DEBUG) MyLog.show("BleUtil.connectGatt()");
		if (mBluetoothAdapter == null || rmtAddress == null) {
			if (DEBUG) MyLog.show("BluetoothAdapter not initialized or unspecified address");
			return;
		}

		if (mTargetAddress != null && rmtAddress.equals(mTargetAddress)
				&& mBluetoothGatt != null) {
			if (DEBUG) MyLog.show("Try to reconnect previous device");
			mBluetoothGatt.connect();
			return;
		}

		BluetoothDevice bluetoothDevice = mBluetoothAdapter.getRemoteDevice(rmtAddress);// 获取要连接的远端设备对象
		if (bluetoothDevice == null) {
			if (DEBUG) MyLog.show("Device not found. Unable to connect");
			return;
		}

		mBluetoothGatt = bluetoothDevice.connectGatt(mContext, false, mBluetoothGattCallback);// 连接此远端设备上的Gatt服务器
		if (DEBUG) MyLog.show("Create a new connection");
		mTargetAddress = rmtAddress;
	}

	public void disconnectGatt() {
		if (DEBUG) MyLog.show("BleUtil.disconnectGatt()");
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			if (DEBUG) MyLog.show("BluetoothAdapter not initialized");
			return;
		}
		mBluetoothGatt.disconnect();
	}

	public void closeGatt() {
		if (DEBUG) MyLog.show("BleUtil.closeGatt()");
		if (mBluetoothGatt == null) {
			if (DEBUG) MyLog.show("mBluetoothGatt = " + mBluetoothGatt);
			return;
		}
		mBluetoothGatt.close();
		mBluetoothGatt = null;
	}


	public void broadcast(String action) {
		Intent intent = new Intent(action);
		mContext.sendBroadcast(intent);
	}

	public void broadcast(String action, final BluetoothGattCharacteristic charac) {
		Intent intent = new Intent(action);
		//*** 下面是处理不同的characteristic的过程，根据其官方说明来解析数据 ***
		if (UUID_NORDIC_UART_RX.equals(charac.getUuid())) {
			//*按照字符串解析
			byte[] data = charac.getValue();
			if (data != null && data.length > 0) {
				intent.putExtra(CHARACTERISTIC_VALUE, data);
			}
		} else if (UUID_BODY_SENSOR_LOCATION.equals(charac.getUuid())) {
			//*按照十六进制数显示
			byte[] data = charac.getValue();
			if (data != null && data.length > 0) {
				String str0;
				StringBuilder stringBuilder = new StringBuilder();
				for (byte tmp : data) {
					str0 = String.format("%x", tmp);
					stringBuilder.append(str0);
				}
				intent.putExtra(CHARACTERISTIC_VALUE, stringBuilder.toString());
			}
		} else if (UUID_BATTERY_LEVEL.equals(charac.getUuid())) {
			int batteryLevel = charac.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0).intValue();
			if (DEBUG) MyLog.show("Received battery level : " + batteryLevel);
			intent.putExtra(CHARACTERISTIC_VALUE, String.valueOf(batteryLevel));
		} else if (UUID_TEMPERATURE_TYPE.equals(charac.getUuid())) {
			//*按照十六进制数显示
			byte[] data = charac.getValue();
			if (data != null && data.length > 0) {
				String str0;
				StringBuilder stringBuilder = new StringBuilder();
				for (byte tmp : data) {
					str0 = String.format("%x", tmp);
					stringBuilder.append(str0);
				}
				intent.putExtra(CHARACTERISTIC_VALUE, stringBuilder.toString());
			}
		} else if (UUID_HEART_RATE_MEASUREMENT.equals(charac.getUuid())) {
			int heartRate;
			// getIntValue():返回此Characteristc的Value字节数组，Value[]一共8 Bytes
			int flags = charac.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 0).intValue();//Value[0] == "Flags"
			if ((flags & 0x01) == 0) {
				// Flags最后一位为0，Heart Rate值为一个字节; 否则为两个字节
				// '1'表示从Characteristic Value偏移为1的地方开始读取
				heartRate = charac.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT8, 1).intValue();
				if (DEBUG)
					MyLog.show(String.format("Data format UINT8, heart rate: %d", heartRate));
			} else {
				heartRate = charac.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 1).intValue();
				if (DEBUG)
					MyLog.show(String.format("Data format UINT16, heart rate: %d", heartRate));
			}
			intent.putExtra(CHARACTERISTIC_VALUE, String.valueOf(heartRate));
		} else {
			//*默认下characteristic的值按照字符串解析
			byte[] data = charac.getValue();
			if (data != null && data.length > 0) {
				try {
					intent.putExtra(CHARACTERISTIC_VALUE, new String(data, "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		}
		mContext.sendBroadcast(intent);
	}

	public List<BluetoothGattService> getAllServices() {
		if (DEBUG) MyLog.show("BleUtil.getAllServices()");
		return mBluetoothGatt.getServices();// 返回一个远端设备所提供Service的list
	}

	public void setCharacteristicNotification(BluetoothGattCharacteristic charac, boolean enabled) {
		if (DEBUG) MyLog.show("BleUtil.setCharacteristicNotification()");
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			return;
		}
		// Characteristic是Notify属性
		if ((charac.getProperties() & BluetoothGattCharacteristic.PROPERTY_NOTIFY) > 0) {
			mBluetoothGatt.setCharacteristicNotification(charac, enabled);
			// 先获取Characteristics的Client Characteristic Configuration描述符(0x2902)
			BluetoothGattDescriptor desc = charac.getDescriptor(UUID.fromString(GattProfile.CLIENT_CHARACTERISTIC_CONFIGURATION));
			// 把Descriptor(0x2902)中的notification功能打开
			desc.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
			mBluetoothGatt.writeDescriptor(desc);
		}
	}

	public void readCharacteristic(BluetoothGattCharacteristic charac) {
		if (DEBUG) MyLog.show("BleUtil.readCharacteristic()");
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			return;
		}
		mBluetoothGatt.readCharacteristic(charac);
	}

	public void writeCharacteristic(final BluetoothGattCharacteristic charac, String inputMsg) {
		if (DEBUG) MyLog.show("BleUtil.writeCharacteristic()");
		if (mBluetoothAdapter == null || mBluetoothGatt == null) {
			return;
		}
//        charac.setWriteType(BluetoothGattCharacteristic.PERMISSION_WRITE);
//        charac.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);

//        charac.setValue(inputMsg.getBytes());
		charac.setValue(inputMsg);
		mBluetoothGatt.writeCharacteristic(charac);
	}
}
