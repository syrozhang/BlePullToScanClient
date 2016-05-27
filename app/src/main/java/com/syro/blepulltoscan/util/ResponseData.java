package com.syro.blepulltoscan.util;

import java.util.ArrayList;

/**
 * Created by Syro on 2016-01-30.
 */
public class ResponseData<T> {
	private ArrayList<T> mObjList = new ArrayList<T>();

	public ArrayList<T> getObjList() {
		return mObjList;
	}
}
