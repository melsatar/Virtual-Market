package com.virtual.market;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_Login extends Activity {
	protected static final int Request_code = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);
        
        OnClickListener lsn_login = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				EditText txtpass = (EditText) findViewById(R.id.txt_pass);
				EditText txtusername = (EditText) findViewById(R.id.txt_username);
				ParseUser user = new ParseUser();
				String Email = txtusername.getText().toString();
				String Pass = txtpass.getText().toString();
				user.setUsername(Email);
				user.setPassword(Pass);
				user.setEmail(Email);
				ParseUser.logInInBackground(Email, Pass,
						new LogInCallback() {
							public void done(ParseUser user,
									ParseException e) {
								if (user != null) {
									// Hooray! The user is logged in.

									if (user.getBoolean("smsVerified") == true) {
										System.out
												.println("Login Successfully");
										Toast.makeText(getBaseContext(),
												"You have logged in ",
												Toast.LENGTH_SHORT).show();
										Intent intent = new Intent();
										// intent.setClass(this, )
										intent.setClass(User_Login.this,
												User_MainActivity.class);
										startActivityForResult(intent,
												Request_code);
										finish();
									} else {
										ShowDialogBox("Please verify you phone number first");
										Intent intent = new Intent();
										intent.setClass(User_Login.this,
												SMSValidate.class);
										startActivityForResult(intent,
												Request_code);
										finish();

									}

								} else {

									ShowDialogBox("The User Name or password is incorrect, please try Again");

								}
							}
						});

			}
		};
		Button btn_login = (Button) findViewById(R.id.btn_login);
		btn_login.setOnClickListener(lsn_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user__login, menu);
        return true;
    }
	private void ShowDialogBox(String message) {

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

		myQuittingDialogBox.show();
	}
}
