package com.virtual.market;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Historical_Data extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historical__data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_historical__data, menu);
        return true;
    }
}
