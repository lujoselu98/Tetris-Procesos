package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PantallaReinicio extends AppCompatActivity  {

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_reinicio);

        Button buttonReinicio = findViewById(R.id.activity_main_button_restart);
        if (buttonReinicio != null) {
            (findViewById(R.id.activity_main_button_restart)).setOnTouchListener((view, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(this,MainActivity.class);
                    this.startActivity(intent);
                    (findViewById(R.id.activity_main_button_restart)).setPressed(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    (findViewById(R.id.activity_main_button_restart)).setPressed(false);
                }

                return true;
            });
        }
    }
}
