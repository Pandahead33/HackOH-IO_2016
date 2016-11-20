package com.example.loganpatino.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RandomActivity extends AppCompatActivity {

    private String[] randomArray = {"GO"};

    private ListView randomListView;
    private ArrayAdapter randomAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        randomListView = (ListView) findViewById(R.id.random_list);

        randomAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, randomArray);
        randomListView.setAdapter(randomAdapter);
    }
}

