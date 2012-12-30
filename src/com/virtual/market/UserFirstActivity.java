package com.virtual.market;

import com.parse.Parse;
import com.parse.ParseUser;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class UserFirstActivity extends Activity {

	protected static final int Request_code = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_first);
		if (isNetworkAvailable()) {
		Parse.initialize(this, "qM2zTgc02p85wv6dks9TCnw0jt1rV72J0oYSM1TE", "BCCNEjUhtZ1DVZXb2FTqCFX4O0ywtoaFCrkwwCMV");

		ParseUser currentUser = ParseUser.getCurrentUser();
		if (currentUser != null) {

			if (currentUser.getBoolean("smsVerified") == true) {
				Intent intent = new Intent();
				intent.setClass(UserFirstActivity.this, User_MainActivity.class);
				startActivityForResult(intent, Request_code);
				Toast.makeText(getBaseContext(), "You have logged in ",
						Toast.LENGTH_SHORT).show();
				Toast.makeText(getBaseContext(), currentUser.getUsername(),
						Toast.LENGTH_SHORT).show();
				finish();
			} else {
				AlertDialog diaBoxmessage = ShowDialogBox("Please verify you phone first");
				VerifyPhone(currentUser.getInt("code"),	currentUser.getInt("user_id"));
				diaBoxmessage.show();

			}
		}

		OnClickListener lsn_signup = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(UserFirstActivity.this, User_Signup.class);
				startActivityForResult(intent, Request_code);
				finish();

			}
		};
		Button btn_signup = (Button) findViewById(R.id.btn_signup);
		btn_signup.setOnClickListener(lsn_signup);

		OnClickListener lsn_login = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(UserFirstActivity.this, User_Login.class);
				startActivityForResult(intent, Request_code);
				finish();

			}
		};
		Button btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(lsn_login);
		}
		else {
			AlertDialog diaBoxmessage = ShowDialogBox("There is no Internet Connection, please make sure that you have a proper connection to the Internet!!!");
			diaBoxmessage.show();

			this.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_user_first, menu);
		return true;
	}
	
	public boolean isNetworkAvailable() {
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private AlertDialog ShowDialogBox(String message) {

		AlertDialog myQuittingDialogBox =

		new AlertDialog.Builder(this)
				.setTitle("Notification")
				.setMessage(message)

				.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						})

				.create();

		return myQuittingDialogBox;
	}
	
	public void VerifyPhone(int RN, int user_id) {

		Intent myIntent = new Intent(UserFirstActivity.this, SMSValidate.class);
		Bundle bun = new Bundle();
		bun.putString("RN", String.valueOf(RN));
		bun.putInt("user_id", user_id);

		myIntent.putExtras(bun);
		startActivity(myIntent);
		finish();
	}
}
