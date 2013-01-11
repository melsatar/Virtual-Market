package com.virtual.market;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Historical_Data extends Activity {
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical__data);
        
        ArrayList<ItemDetails> image_details = GetSearchResults();
        
        final ListView lv1 = (ListView) findViewById(R.id.listV_main);
        lv1.setAdapter(new ItemListBaseAdapter(this, image_details));
        
        lv1.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> a, View v, int position, long id) { 
        		Object o = lv1.getItemAtPosition(position);
            	ItemDetails obj_itemDetails = (ItemDetails)o;
        		Toast.makeText(Historical_Data.this, "You have chosen : " + " " + obj_itemDetails.getName(), Toast.LENGTH_LONG).show();
        	}  
        });
    }
    
    private ArrayList<ItemDetails> GetSearchResults(){
    	ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
    	
    	ItemDetails item_details = new ItemDetails();
    	item_details.setName("Pizza");
    	item_details.setItemDescription("Spicy Chiken Pizza");
    	item_details.setPrice("RS 310.00");
//    	item_details.setImageNumber(1);
    	results.add(item_details);
    	
    	
    	return results;
    }
}