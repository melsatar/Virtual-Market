package com.virtual.market;

import java.util.List;
import java.util.Random;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class User_Signup extends Activity {


	String area_id;
	String[] Cities;
	String[] Areas;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_signup);
		Parse.initialize(this, "qM2zTgc02p85wv6dks9TCnw0jt1rV72J0oYSM1TE",
				"BCCNEjUhtZ1DVZXb2FTqCFX4O0ywtoaFCrkwwCMV");
		Fill_Cities();
		final Spinner spCities = (Spinner) findViewById(R.id.sp_cities);
		spCities.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String City_Name = spCities.getItemAtPosition(arg2).toString();
				System.out.println(City_Name);
				ParseQuery thiscity = new ParseQuery("City");
				thiscity.whereEqualTo("city_name", City_Name);
				thiscity.findInBackground(new FindCallback() {
					public void done(List<ParseObject> CityList,
							ParseException e) {
						if (e == null) {

							Fill_Areas(CityList.get(0).getObjectId());
						} else {
							Log.d("City", "Error: " + e.getMessage());
						}
					}
				});
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});

		OnClickListener lsn_signup = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				System.out.println("Here");
				EditText txtpass = (EditText) findViewById(R.id.txt_pass);
				EditText txtemail = (EditText) findViewById(R.id.txt_email);
				EditText txtphone = (EditText) findViewById(R.id.txt_phone);
				EditText txtaddress = (EditText) findViewById(R.id.txt_address);
				EditText txtusername = (EditText) findViewById(R.id.txt_username);
				final ParseUser user = new ParseUser();
				String Email = txtemail.getText().toString();
				if (Email.isEmpty()) {
					ShowDialogBox("you have to enter you Email");
				} else {
					user.setEmail(Email);
				}
				String Pass = txtpass.getText().toString();
				if (Pass.isEmpty()) {
					ShowDialogBox("you have to enter you Password");
				} else {
					user.setPassword(Pass);
				}
				String username = txtusername.getText().toString();
				if (username.isEmpty()) {
					ShowDialogBox("you have to enter you User Name");
				} else {
					user.setUsername(username);
				}
				String address = txtaddress.getText().toString();
				if (address.isEmpty()) {
					ShowDialogBox("you have to enter you Address");
				} else {
					user.put("address", address);
				}
				final String Phone = txtphone.getText().toString();
				if (Phone.isEmpty()) {
					ShowDialogBox("you have to enter you Phone Number");
				} else {
					user.put("user_mobile", Phone);
					user.put("smsVerified", false);
				}
//				Spinner spAreas = (Spinner) findViewById(R.id.sp_areas);
//				String Area_IDD = get_area_id(spAreas.getSelectedItem()
//						.toString());
//				System.err.println(Area_IDD);
//				System.out.println(Area_IDD);
//				user.put("area_id", Area_IDD);
//				
//				System.out.println("AREA NAME " + spAreas.getSelectedItem().toString());
				user.put("user_type", 1);
				if (!username.isEmpty() && !Pass.isEmpty()
						&& !address.isEmpty() && !Email.isEmpty()
						&& !Phone.isEmpty()) {
					int min = 10000;
					int max = 99999;

					Random r = new Random();
					final int RN = r.nextInt(max - min + 1) + min;
					user.put("code", RN);
					user.signUpInBackground(new SignUpCallback() {
						public void done(ParseException e) {
							if (e == null) {
								// Hooray! Let them use the app now.
								VerifyPhoneSendSMS(RN, Phone);
								ShowDialogBox("you have been registred, please verify your mobile to be able to log in ");
							} else {
								ShowDialogBox("The user name is already registred, please try another name or log in directly");
								System.out.println(e.toString());
							}
						}
					});

				}

			}
		};
		Button btn_signup = (Button) findViewById(R.id.btn_signup);
		btn_signup.setOnClickListener(lsn_signup);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_user_signup, menu);
		return true;
	}

	private void ShowDialogBox(String message) {

		AlertDialog myQuittingDialogBox = new AlertDialog.Builder(this)
				.setTitle("Notification")
				.setMessage(message)
				.setPositiveButton("Okay",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int whichButton) {
							}
						}).create();

		myQuittingDialogBox.show();
	}

	public void VerifyPhoneSendSMS(int RN, String phoneNo) {

		try {
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, null, String.valueOf(RN), null,
					null);
			Toast.makeText(getApplicationContext(), "We sent you SMS!",
					Toast.LENGTH_LONG).show();

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later!", Toast.LENGTH_LONG)
					.show();
			e.printStackTrace();
		}

		Intent myIntent = new Intent(User_Signup.this, SMSValidate.class);
		Bundle bun = new Bundle();
		bun.putString("RN", String.valueOf(RN));

		myIntent.putExtras(bun);
		startActivity(myIntent);
		finish();
	}

	private void Fill_Cities() {
		final Spinner spCities = (Spinner) findViewById(R.id.sp_cities);
		ParseQuery query = new ParseQuery("City");
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> CityList, ParseException e) {
				if (e == null) {
					Log.d("Cities", "Retrieved " + CityList.size() + " Cities");
					Cities = new String[CityList.size()];
					for (int i = 0; i < CityList.size(); i++) {

						Cities[i] = CityList.get(i).getString("city_name");

					}
					ArrayAdapter<String> city_adapter = new ArrayAdapter<String>(
							User_Signup.this,
							android.R.layout.simple_spinner_item, Cities);
					spCities.setAdapter(city_adapter);
					city_adapter.notifyDataSetChanged();

				} else {
					Log.d("City", "Error: " + e.getMessage());
				}
			}
		});
	}

	private void Fill_Areas(String city_id) {
		final Spinner spAreas = (Spinner) findViewById(R.id.sp_areas);
		ParseQuery query = new ParseQuery("Area");
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.whereEqualTo("city_id", city_id);
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> AreaList, ParseException e) {
				if (e == null) {
					Log.d("Area", "Retrieved " + AreaList.size() + " Areas");
					Areas = new String[AreaList.size()];
					for (int i = 0; i < AreaList.size(); i++) {

						Areas[i] = AreaList.get(i).getString("area_name");

					}
					ArrayAdapter<String> city_adapter = new ArrayAdapter<String>(
							User_Signup.this,
							android.R.layout.simple_spinner_item, Areas);
					spAreas.setAdapter(city_adapter);
					city_adapter.notifyDataSetChanged();

				} else {
					Log.d("Areas", "Error: " + e.getMessage());
				}
			}
		});
	}

/*	private String get_area_id(String area_name) {
		ParseQuery query = new ParseQuery("Area");
		query.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);
		query.whereEqualTo("area_name", area_name);
		
		query.findInBackground(new FindCallback() {
			public void done(List<ParseObject> AreaList, ParseException e) {
				if (e == null) {
					area_id = new String();
					area_id = AreaList.get(0).getObjectId();

				} else {
					Log.d("Areas", "Error: " + e.getMessage());
				}
			}
		});
		return area_id;
	}*/

}
