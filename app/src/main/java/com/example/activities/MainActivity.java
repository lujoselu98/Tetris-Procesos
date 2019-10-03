package com.example.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pieces.Pieza;
import com.example.pieces.PiezaC;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;
import com.example.pieces.PiezaLI;
import com.example.pieces.PiezaT;
import com.example.pieces.PiezaZ;
import com.example.pieces.PiezaZI;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity {
    Ventana v;
    Hebra h;
    Cronometro cronometro;
    int ivID;
    int puntuacion = 0;
   @SuppressLint("ClickableViewAccessibility")
   @Override
    protected void onCreate(final Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
       setContentView(R.layout.activity_juego);


       TextView textView = (TextView) findViewById(R.id.Cronometro);
       cronometro = new Cronometro("CuentaAtras", textView);
       Thread c = new Thread(cronometro);
       v = new Ventana(this);
       RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(R.id.relativelayout1, R.id.relativelayout1);
       v.setLayoutParams(params1);
       RelativeLayout relativeSteinAnzeige = (RelativeLayout) findViewById(R.id.relativelayout1);
       v.setBackgroundColor(Color.YELLOW);
       relativeSteinAnzeige.addView(v);

       h = new Hebra(true, this, v);
       NextPieceView piezaSig = new NextPieceView(this, h.getTetris());
       h.setTableroPiezaSig(piezaSig);

       LinearLayout.LayoutParams parametro = new LinearLayout.LayoutParams(R.id.LinearLayoutLateralPieza, R.id.LinearLayoutLateralPieza);
       piezaSig.setLayoutParams(parametro);
       LinearLayout relativeSteinLinear = (LinearLayout) findViewById(R.id.LinearLayoutLateralPieza);
       relativeSteinLinear.addView(piezaSig);


       //setContentView(R.layout.activity_juego); //Iniciamos la pantalla del tablero del Tetris, faltaría meter encima de los botones el canvas
       Controls controls = new Controls(h);


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

       findViewById(R.id.button_pause).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_UP) {
               Button pause = (Button) findViewById(R.id.button_pause);
               if (pause.getText().toString().compareTo("Pause") == 0) {
                   h.setPuedoMover(false);
                   cronometro.pause();
                   pause.setText("Resume");
               } else {
                   h.setPuedoMover(true);
                   cronometro.reanudar();
                   pause.setText("Pause");
               }
           }

           return true;

       });


       findViewById(R.id.button_right).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               System.out.println("BOTON DERECHO");
               controls.rightButtonPressed(h.getPiezaActual());

               sumar_puntuacion(v, 50);
               findViewById(R.id.button_right).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.rightButtonReleased();
               findViewById(R.id.button_right).setPressed(false);
           }

           return true;
       });

       findViewById(R.id.button_left).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.leftButtonPressed(h.getPiezaActual());
               findViewById(R.id.button_left).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.leftButtonReleased();
               findViewById(R.id.button_left).setPressed(false);
           }

           return true;
       });

       findViewById(R.id.button_soft_drop).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.downButtonPressed(h.getPiezaActual());
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
                   controls.rotateRightPressed(h.getPiezaActual());
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
                   controls.rotateLeftPressed(h.getPiezaActual());
                   (findViewById(R.id.button_rotate_left)).setPressed(true);
               } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   (findViewById(R.id.button_rotate_left)).setPressed(false);
               }

               return true;
           });
       }
   }


    public void gameOver(){
        Intent intent = new Intent(this,PantallaReinicio.class);
        this.startActivity(intent);


    }
    private void sumar_puntuacion(Ventana v, int ptos) {
       puntuacion+=ptos;
       TextView texto = (TextView) findViewById(R.id.puntuacion);
       texto.setText("Ptos:"+puntuacion);
    }



}
