package com.example.loganpatino.hackohio2016;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);




    }

    public void enterMain(View view)
    {

        //final Animation animShake = AnimationUtils.loadAnimation(this, R.anim.shake);
        ImageButton btn = (ImageButton) findViewById(R.id.circle2);
        //btn.startAnimation(animShake)3;
        setScaleAnimation(btn);

        // Execute some code after 2 seconds have passed
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(InitialActivity.this, MainActivity.class);
                startActivity(intent);;
            }
        }, 500);
    }

    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(1000);
        view.startAnimation(anim);
    }

}
