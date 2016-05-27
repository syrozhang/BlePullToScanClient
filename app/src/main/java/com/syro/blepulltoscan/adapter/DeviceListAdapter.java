package com.syro.blepulltoscan.adapter;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.syro.blepulltoscan.R;

import java.util.ArrayList;

/**
 * Created by Syro on 2016-01-06.
 */
public class DeviceListAdapter extends BaseAdapter {
	private Context mContext;
	private int mLayoutRes;
	private LayoutInflater mLayoutInflater;
	private ArrayList<BluetoothDevice> list = new ArrayList<BluetoothDevice>();

	public DeviceListAdapter(Context context, int layoutRes) {
		this.mContext = context;
		this.mLayoutRes = layoutRes;
//        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mLayoutInflater = LayoutInflater.from(mContext);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) { //第一次创建的item
			convertView = mLayoutInflater.inflate(mLayoutRes, null);
			viewHolder = new ViewHolder();
			viewHolder.dvc_name = (TextView) convertView.findViewById(R.id.tv_device_name);
			viewHolder.dvc_addr = (TextView) convertView.findViewById(R.id.tv_device_address);
			convertView.setTag(viewHolder);
		} else { //缓存中已有的item
			viewHolder = (ViewHolder) convertView.getTag();
		}
		BluetoothDevice bluetoothDevice = list.get(position);
		String name = bluetoothDevice.getName();
		if (name != null && name.length() > 0) {
			viewHolder.dvc_name.setText(bluetoothDevice.getName());
		} else {//名字为空
			viewHolder.dvc_name.setText("Unknown Device");
		}
		viewHolder.dvc_addr.setText(bluetoothDevice.getAddress());
		return convertView;
	}

	public void addItems(ArrayList<BluetoothDevice> itemList) {
		list.addAll(itemList);
		notifyDataSetChanged();
	}

	public void clearItems() {
		list.clear();
		notifyDataSetChanged();
	}

	public ArrayList<BluetoothDevice> getList() {
		return list;
	}

	static class ViewHolder {
		TextView dvc_name;
		TextView dvc_addr;
	}
}
