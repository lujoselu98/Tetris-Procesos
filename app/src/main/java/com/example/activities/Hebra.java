package com.example.activities;


import android.graphics.Color;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;

import com.example.pieces.Pieza;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;

/* IGUAL QUE EL WORKTHREAD */
public class Hebra extends Thread{
    private boolean puedoMover;
    private boolean finPartida;
    private int velocidadCaida = 5000;

    MainActivity mainActivity;
    Pieza p;
    private Ventana v;
    TableroTetris tetris;

    //Modelo modelo;

    public Hebra(boolean puedoMover, MainActivity mainActivity, Ventana v) {
        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.v = v;
        p = new PiezaI(1,Color.parseColor("#FF00FF"));
        v.setPieza(p);

        tetris = new TableroTetris();
        v.setTablero(tetris);
    }


    @Override
    public void run() {
        System.out.println("SOY LA HEBRA Y ESPEROOOO");
       //mainActivity.mostrarCanvas();   //Mostramos el canvas
        while(!finPartida) {
            while (puedoMover) {
                p.bajar();
                //p.rotarDcha();
                System.out.println("ESTOY DENTRO DEL WHILE DE LA HEBRA");
                //mover(p);
                System.out.println("LLAMO A INVALIDATE");
                v.invalidate();
                System.out.println("INVALIDATE SUPERADO");
                try {
                    System.out.println("VOY A DORMIR");
                    Thread.sleep(velocidadCaida);

                } catch (InterruptedException ignored) {

                }

            }
        }

    }
    public void setPuedoMover(boolean mover){
        this.puedoMover = mover;
    }
    private void metodoHebra() {

    }
    public Pieza getPieza(){
        return p;
    }
    public void mover(Pieza p) {
        System.out.println("ES EL MODELO");
        int[] rows_L = new int[]{0, 1, 2, 3};
        int[] cols_L = new int[]{0, 0, 0, 1};
        //canvas.drawPiece(4,rows_L,cols_L,getAndroidCanvas());
        //MOVER PIEZA
    }
    public void setFinPartida(){
        this.finPartida = true;
    }

    public Ventana getV() {
        return v;
    }
}
