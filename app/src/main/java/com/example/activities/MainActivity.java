package com.example.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    Canvas canvas;
   @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Hebra h = new Hebra(true,this);
       System.out.println("Voy a crear el modelo:");
        Modelo modelo = new Modelo(h);
       final Button button = findViewById(R.id.activity_main_button_new_game);
        canvas = new Canvas(this);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setContentView(R.layout.activity_juego); //Iniciamos la pantalla del tablero del Tetris, faltaría meter encima de los botones el canvas

                //setContentView(canvas);
                //h.run();
                //Esto arranca la hebra pero deja de visualizarse el Canvas


            }
        });

        //setContentView(canvas);
    }
    public void mostrarCanvas(){
        setContentView(R.layout.activity_juego);
    }
}
