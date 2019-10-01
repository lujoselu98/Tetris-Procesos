package com.example.activities;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


/* COMO EL GAME ACTIVITY  */
public class Modelo extends AppCompatActivity {
    canvasAlex canvasAlex;
    public Controls controls;
    Hebra hebra;
    ArrayList matriz;

    public Modelo(Controls controls, Hebra h) {
        this.controls = controls;
        this.hebra = h;
        this.matriz = new ArrayList<>();
    }

    public Modelo(Hebra h){
        System.out.println("MODELO CREADO");
        this.hebra = h;
       /* for (int i = 0; i < 20; i++) {
            this.matriz.add(new ArrayList<Integer>());
            for (int j = 0; j < 10; j++) {
                ArrayList p = (ArrayList) this.matriz.get(i);
                p.add(0);
            }
        }
        imprimirMatriz(matriz);*/

        //setContentView(R.layout.activity_juego);
        //hebra.run(); //Iniciamos la hebra
    }
  /*  public void imprimirMatriz(ArrayList<ArrayList> matriz){
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(matriz.get(i).get(j));
            }
            System.out.println("");
        }
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findViewById(R.id.button_right).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                System.out.println("BOTON DERECHO");
                controls.rightButtonPressed();
                findViewById(R.id.button_right).setPressed(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                controls.rightButtonReleased();
                findViewById(R.id.button_right).setPressed(false);
            }

            return true;
        });
        System.out.println("ON CREATE MODELO");
        /*canvas = new Canvas(this);
        setContentView(canvas);*/
        setContentView(R.layout.activity_juego);
        hebra.run(); //Iniciamos la hebra

    }
    public void iniciarModelo(){
        setContentView(R.layout.activity_juego);
        hebra.run();
    }
// Register button callback methods
    public void mover(){
        System.out.println("ES EL MODELO");
            findViewById(R.id.button_pause).setOnClickListener(view -> Modelo.this.finish());

            findViewById(R.id.button_right).setOnTouchListener((view, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    System.out.println("BOTON DERECHO");
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
    }


}