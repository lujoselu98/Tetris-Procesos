package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.example.activities.R;

public class SplashActivity extends Activity {
    private final int DURACION_SPLASH = 1000;//5000;

    public void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, Settings.class);
                startActivity(intent);
                finish();
            };
        }, DURACION_SPLASH);
    }
}
