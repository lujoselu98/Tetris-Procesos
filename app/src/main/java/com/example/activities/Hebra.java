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
    private int velocidadCaida = 1000;
    private NextPieceView tableroPiezaSig;
    MainActivity mainActivity;
    private Ventana v;
    TableroTetris tetris;

    //Modelo modelo;

    public Hebra(boolean puedoMover, MainActivity mainActivity, Ventana v,int velocidad) {
        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.v = v;
        if(velocidad != 0){
            this.velocidadCaida = velocidadCaida / velocidad;
        }

        tetris = new TableroTetris(this.mainActivity);
        v.setPieza(tetris.getPiezaActual());
        v.setTablero(tetris);
    }

    public TableroTetris getTablero(){
        return tetris;
    }

    @Override
    public void run() {
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
                    setFinPartida();
                    puedoMover = false;
                }
                try {
                    System.out.println("VOY A DORMIR");
                    Thread.sleep(velocidadCaida);

                } catch (InterruptedException ignored) {

                }

            }
        }
        mainActivity.gameOver();
    }


    public void setPuedoMover(boolean mover){
        this.puedoMover = mover;
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
