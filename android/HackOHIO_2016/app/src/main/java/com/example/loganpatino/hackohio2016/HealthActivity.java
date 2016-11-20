package com.example.loganpatino.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HealthActivity extends AppCompatActivity {

    private String[] healthArray = {"Jogging", "Running", "Yoga"};

    private ListView healthListView;
    private ArrayAdapter healthAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        healthListView = (ListView) findViewById(R.id.health_list);

        healthAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, healthArray);
        healthListView.setAdapter(healthAdapter);
    }
}

