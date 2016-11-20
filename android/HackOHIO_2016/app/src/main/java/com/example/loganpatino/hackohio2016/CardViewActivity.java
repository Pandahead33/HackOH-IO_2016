package com.example.loganpatino.hackohio2016;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class CardViewActivity extends Activity {

    TextView personName;
    TextView personAge;
    ImageView personPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cardview_activity);
        //personName = (TextView)findViewById(R.id.person_name);
        personPhoto = (ImageView)findViewById(R.id.person_photo);

        personName.setText("Sports");
        personPhoto.setImageResource(R.drawable.sports);
    }
}