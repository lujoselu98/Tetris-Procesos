package com.example.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity {
    canvasAlex canvasAlex;
    Ventana v;
    Cronometro cronometro;
    int puntuacion = 0;
   @SuppressLint("ClickableViewAccessibility")
   @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

       //setContentView(R.layout.activity_main);
       final Hebra h = new Hebra(true,this);
       System.out.println("Voy a crear el modelo:");
       Modelo modelo = new Modelo(h);
       final Button button = findViewById(R.id.activity_main_button_new_game);
       setContentView(R.layout.activity_juego);

       //setContentView(R.layout.activity_juego); //Iniciamos la pantalla del tablero del Tetris, faltaría meter encima de los botones el canvas
       Controls controls = new Controls(h);

       TextView textView = (TextView) findViewById(R.id.Cronometro);
       cronometro = new Cronometro("CuentaAtras",textView);
       Thread c = new Thread(cronometro);


       v = new Ventana(this);
       RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(R.id.relativelayout1,R.id.relativelayout1);
       v.setLayoutParams(params1);
       RelativeLayout relativeSteinAnzeige = (RelativeLayout) findViewById(R.id.relativelayout1);
       v.setBackgroundColor(Color.YELLOW);
       relativeSteinAnzeige.addView(v);
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage("DISFRUTA DEL TETRIS DEL GRUPO 1")
               .setTitle("INICIAR JUEGO")
               .setCancelable(false)
               .setNeutralButton("JUGAR",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               dialog.cancel();
                               h.start();
                               c.start();
                           }
                       });
       AlertDialog alert = builder.create();
       alert.show();
       //BOTONES
       findViewById(R.id.button_pause).setOnClickListener(view -> MainActivity.this.finish());

       findViewById(R.id.button_right).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               System.out.println("BOTON DERECHO");
               sumar_puntuacion(v,50);
               controls.rightButtonPressed();
               findViewById(R.id.button_right).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.rightButtonReleased();
               findViewById(R.id.button_right).setPressed(false);
           }

           return true;
       });

       findViewById(R.id.button_left).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.leftButtonPressed();
               findViewById(R.id.button_left).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.leftButtonReleased();
               findViewById(R.id.button_left).setPressed(false);
           }

           return true;
       });

       findViewById(R.id.button_soft_drop).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.downButtonPressed();
               (findViewById(R.id.button_soft_drop)).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.downButtonReleased();
               (findViewById(R.id.button_soft_drop)).setPressed(false);
           }

           return true;
       });

       (findViewById(R.id.button_hard_drop)).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.dropButtonPressed();
               (findViewById(R.id.button_hard_drop)).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               (findViewById(R.id.button_hard_drop)).setPressed(false);
           }

           return true;
       });

       ImageButton buttonRotateRight = findViewById(R.id.button_rotate_right);
       if (buttonRotateRight != null) {
           (findViewById(R.id.button_rotate_right)).setOnTouchListener((view, event) -> {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   controls.rotateRightPressed();
                   (findViewById(R.id.button_rotate_right)).setPressed(true);
               } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   (findViewById(R.id.button_rotate_right)).setPressed(false);
               }

               return true;
           });
       }

       ImageButton buttonRotateLeft = findViewById(R.id.button_rotate_left);
       if (buttonRotateLeft != null) {
           (findViewById(R.id.button_rotate_left)).setOnTouchListener((view, event) -> {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   controls.rotateLeftPressed();
                   (findViewById(R.id.button_rotate_left)).setPressed(true);
               } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   (findViewById(R.id.button_rotate_left)).setPressed(false);
               }

               return true;
           });
       }
        /*button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                setContentView(R.layout.activity_juego); //Iniciamos la pantalla del tablero del Tetris, faltaría meter encima de los botones el canvas

                //setContentView(canvas);
                h.run();
                //Esto arranca la hebra pero deja de visualizarse el Canvas


            }
        });*/

        //setContentView(canvas);
    }

    private void sumar_puntuacion(Ventana v, int ptos) {
       puntuacion+=ptos;
       TextView texto = (TextView) findViewById(R.id.puntuacion);
       texto.setText("Ptos:"+puntuacion);
    }

    protected String doInBackground(String... params) {
        for (int i = 0; i < 1000; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("THREAD IN BACKGROUND");
            } catch (InterruptedException e) {
                Thread.interrupted();
            }
        }
        return "Executed";
    }
    public void mostrarCanvas(){
        setContentView(R.layout.activity_juego);
    }
}
