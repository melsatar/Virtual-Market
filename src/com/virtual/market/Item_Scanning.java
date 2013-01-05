package com.virtual.market;

import java.util.Currency;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Item_Scanning extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item__scanning);

		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) 
    {	
    	//String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
    	final TextView Item_ID  =(TextView) findViewById(R.id.Item_ID);
    	final TextView Item_title  =(TextView) findViewById(R.id.item_title);
        //final ImageView Item_Image  =(TextView) findViewById(R.id.item_image);
        final TextView Item_Cat  =(TextView) findViewById(R.id.item_cat);
        final TextView Item_Price  =(TextView) findViewById(R.id.item_price);
        final Button btn_AddtoCart  =(Button) findViewById(R.id.addtoCart);
        final ParseUser currentuser = ParseUser.getCurrentUser();

        
        
    	if (requestCode == 0) {
    	      if (resultCode == RESULT_OK) {
    	         //String contents = intent.getStringExtra("SCAN_RESULT");
    	         String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
    	         
    	         ParseQuery query = new ParseQuery("Item");
    	         query.getInBackground("NrCjHQfi8C", new GetCallback() {
    	           public void done(final ParseObject object, ParseException e) {
    	             if (e == null) 
    	             {
    	            	Item_ID.setText(" Product ID : NrCjHQfi8C");
    	             	Item_title.setText(" Product : "+object.getString("item_name"));
    	             	Item_Cat.setText(" Category : "+object.getString("item_desc"));
    	             	Item_Price.setText(" Price : "+object.getString("item_price"));
    	             	
    	             	
    	             	
    	             	
    	             } else {
    	               // something went wrong
    	             }
    	           }
    	         });
    	         // Handle successful scan
    	      } else if (resultCode == RESULT_CANCELED) {
    	         // Handle cancel
    	      }
    	      
				
    	   }
    	
    	OnClickListener lsn_add = new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				ParseObject Cart = new ParseObject("Cart");
				Cart.put("item_amount", 100);
				Cart.put("item_id", "NrCjHQfi8C");
				Cart.put("user_id", currentuser.getObjectId() );
				Cart.saveInBackground();
				

			}
		};
		
		btn_AddtoCart.setOnClickListener(lsn_add);
		
    	}

}
