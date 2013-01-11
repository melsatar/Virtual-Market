package com.virtual.market;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class User_MainActivity extends Activity {

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user__main);

		OnClickListener lsn_Scan = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(User_MainActivity.this, Item_Scanning.class);
				startActivity(intent);

			}
		};
		Button btn_Scan = (Button) findViewById(R.id.btn_scan);
		btn_Scan.setOnClickListener(lsn_Scan);

		OnClickListener lsn_Reports = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(User_MainActivity.this, User_Reports.class);
				startActivity(intent);

			}
		};
		Button btn_Reports = (Button) findViewById(R.id.btn_Reports);
		btn_Reports.setOnClickListener(lsn_Reports);

		OnClickListener lsn_data = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(User_MainActivity.this, Historical_Data.class);
				startActivity(intent);

			}
		};
		Button btn_data = (Button) findViewById(R.id.btn_historical);
		btn_data.setOnClickListener(lsn_data);

		OnClickListener lsn_cart = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(User_MainActivity.this, Shopping_cart.class);
				startActivity(intent);

			}
		};
		Button btn_cart = (Button) findViewById(R.id.btn_viewCart);
		btn_cart.setOnClickListener(lsn_cart);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_user__main, menu);
		return true;
	}
}
