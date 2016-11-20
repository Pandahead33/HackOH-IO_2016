package com.example.loganpatino.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HobbyActivity extends AppCompatActivity {

    private String[] hobbyArray = { "Knitting Circle", "Droning", "Art"};

    private ListView hobbyListView;
    private ArrayAdapter hobbyAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hobby);

        hobbyListView = (ListView) findViewById(R.id.hobby_list);

        hobbyAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, hobbyArray);
        hobbyListView.setAdapter(hobbyAdapter);
    }
}

