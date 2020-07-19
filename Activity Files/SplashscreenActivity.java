package com.example.newpark;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashscreenActivity extends AppCompatActivity {

    private static int SPLASH_TIMER = 5000;

    TextView poweredByLine;
    ImageView backgroungLogo;
    Animation sideAnim, bottomAnim;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        poweredByLine = findViewById(R.id.splash_slogan);
        backgroungLogo = findViewById(R.id.splash_logo);


        sideAnim = AnimationUtils.loadAnimation(this,R.anim.side_anim);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        backgroungLogo.setAnimation(sideAnim);
        poweredByLine.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intTologin = new Intent(SplashscreenActivity.this,LoginActivity.class);
                startActivity(intTologin);
                finish();
            }
        },SPLASH_TIMER);

    }
}