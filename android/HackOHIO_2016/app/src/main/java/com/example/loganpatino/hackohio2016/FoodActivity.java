package com.example.loganpatino.hackohio2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class FoodActivity extends AppCompatActivity {

    private String[] foodArray = { "1 on 1 Cheap", "1 on 1 Fancy", "Cheap Group Food", "Fancy Group Food"};

    private ListView foodListView;
    private ArrayAdapter foodAdapter;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        foodListView = (ListView) findViewById(R.id.food_list);

        foodAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, foodArray);
        foodListView.setAdapter(foodAdapter);
    }
}

