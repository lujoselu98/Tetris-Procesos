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
    private int velocidadCaida = 500;
    private NextPieceView tableroPiezaSig;
    MainActivity mainActivity;
    private Ventana v;
    TableroTetris tetris;

    //Modelo modelo;

    public Hebra(boolean puedoMover, MainActivity mainActivity, Ventana v) {
        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.v = v;


        tetris = new TableroTetris(this.mainActivity);
        v.setPieza(tetris.getPiezaActual());
        v.setTablero(tetris);
    }

    public TableroTetris getTablero(){
        return tetris;
    }

    @Override
    public void run() {
        System.out.println("SOY LA HEBRA Y ESPEROOOO");
        //mainActivity.mostrarCanvas();   //Mostramos el canvas
        while (!finPartida) {
            while (puedoMover) {
                v.setPieza(tetris.getPiezaActual());
                tetris.bajar();
               System.out.println("ESTOY DENTRO DEL WHILE DE LA HEBRA");
                System.out.println("LLAMO A INVALIDATE");
                v.invalidate();
                tableroPiezaSig.invalidate();
                System.out.println("INVALIDATE SUPERADO");
                if (tetris.comprobarPerdido()) {
                    System.out.println("PERDIDO");
                    finPartida = true;
                    puedoMover = false;
                }
                try {
                    System.out.println("VOY A DORMIR");
                    Thread.sleep(velocidadCaida);

                } catch (InterruptedException ignored) {

                }

            }
            //finPartida = tetris.comprobarPerdido();
        }
    }


    public void setPuedoMover(boolean mover){
        this.puedoMover = mover;
    }
    private void metodoHebra() {

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

    public TableroTetris getTetris(){
        return tetris;
    }
    public Pieza getPiezaActual(){
        return this.tetris.getPiezaActual();
    }
    public Pieza getPiezaSiguiente(){
        return this.tetris.getPiezaSig();
    }

    public void setTableroPiezaSig(NextPieceView piezaSig) {
        this.tableroPiezaSig = piezaSig;
    }
}
