package com.virtual.market;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class User_Reports extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__reports);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_user__reports, menu);
        return true;
    }
}
