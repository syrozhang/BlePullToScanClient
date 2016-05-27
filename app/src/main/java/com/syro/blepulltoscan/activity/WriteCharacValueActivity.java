package com.syro.blepulltoscan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.syro.blepulltoscan.R;


/**
 * Created by Syro on 2016-02-01.
 */
public class WriteCharacValueActivity extends BaseActivity {
	private EditText mEtInputMsg;
	private Button mBtnSend;
	private Toolbar mToolbar;
	private String mCharacName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write);//把布局文件实例化为View并填充到Activity中
		Intent intent = getIntent();// 返回启动此Activity的Intent
		mCharacName = intent.getStringExtra("CharacName");
//        initToolbar(mCharacName);

		mEtInputMsg = (EditText) findViewById(R.id.et_write);
		mBtnSend = (Button) findViewById(R.id.btn_start_to_write);
		mBtnSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String str = mEtInputMsg.getText().toString();
				Intent intent = new Intent();
				intent.putExtra("inputString", str);
				setResult(RESULT_OK, intent);
				finish();// 关闭Activity
			}
		});
	}

//	private void initToolbar(String characName) {
//		mToolbar = (Toolbar) findViewById(R.id.activity_write_toolbar);
//		mToolbar.setTitle(characName);
//		mToolbar.setLogo(R.drawable.app_logo);
//		setSupportActionBar(mToolbar);// 添加Toolbar到Activity
//	}
}
