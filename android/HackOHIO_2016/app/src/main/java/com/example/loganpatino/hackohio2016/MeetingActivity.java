package com.example.loganpatino.hackohio2016;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;

import com.facebook.login.widget.ProfilePictureView;

/**
 * Created by Pandahead33 on 11/20/2016.
 */
public class MeetingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        String id = getIntent().getStringExtra("sick");
        ProfilePictureView pic = (ProfilePictureView) findViewById(R.id.carl);
        pic.setProfileId(id);





    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MeetingActivity.this, MainTabActivity.class);
        startActivity(intent);

    }

}
