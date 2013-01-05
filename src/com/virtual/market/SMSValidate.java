package com.virtual.market;

import com.parse.ParseUser;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSValidate extends Activity {
	protected static final int Request_code = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_validate);
        Bundle bun = getIntent().getExtras();
		final String s = bun.getString("RN");
		Button BT = (Button) findViewById(R.id.btn_verify);
		final EditText ET = (EditText) findViewById(R.id.txt_code);

		BT.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				if (s.equals(ET.getText().toString())) {
					ParseUser currentUser = ParseUser.getCurrentUser();
					currentUser.put("smsVerified", true);
					currentUser.saveInBackground();

					Toast.makeText(getApplicationContext(), "Thank You",
							Toast.LENGTH_LONG).show();
					Toast.makeText(getBaseContext(),
							"You have logged in ",
							Toast.LENGTH_SHORT).show();
					Intent intent = new Intent();
					// intent.setClass(this, )
					intent.setClass(SMSValidate.this,
							User_MainActivity.class);
					startActivityForResult(intent,
							Request_code);
					finish();
				} else {
					Toast.makeText(getApplicationContext(),
							"Please Check the Code Again!", Toast.LENGTH_LONG)
							.show();
				}

			}
		});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_sms_validate, menu);
        return true;
    }
}
