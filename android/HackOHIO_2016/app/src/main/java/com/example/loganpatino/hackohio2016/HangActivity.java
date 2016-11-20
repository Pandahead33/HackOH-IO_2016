package com.example.loganpatino.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HangActivity extends AppCompatActivity {

    private String[] hangArray = {"Coffee", "Movies", "Minigolf"};

    private ListView hangListView;
    private ArrayAdapter arrayAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang);

        hangListView = (ListView) findViewById(R.id.hang_list);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hangArray);
        hangListView.setAdapter(arrayAdapter);
    }
}

