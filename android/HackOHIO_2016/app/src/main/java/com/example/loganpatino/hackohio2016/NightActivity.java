package com.example.loganpatino.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NightActivity extends AppCompatActivity {

    private String[] nightArray = { "Bar", "Club"};

    private ListView nightListView;
    private ArrayAdapter nightAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_night);

        nightListView = (ListView) findViewById(R.id.night_list);

        nightAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nightArray);
        nightListView.setAdapter(nightAdapter);
    }
}

