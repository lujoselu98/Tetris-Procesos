package com.example.activities;

import android.graphics.Color;

import com.example.pieces.Pieza;
import com.example.pieces.PiezaC;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;
import com.example.pieces.PiezaLI;
import com.example.pieces.PiezaT;
import com.example.pieces.PiezaZ;
import com.example.pieces.PiezaZI;

import java.util.Random;

public class CreadorPiezas {

    int contadorPiezas;

    public CreadorPiezas(){
        contadorPiezas = 1;
    }


    public Pieza crearPieza(){
        Random r = new Random();
        int n = r.nextInt(7);

        return crearPieza(n, cogerColor(n));
    }

    public int cogerColor(int x) {

        if(x==0) return Color.CYAN;
        if(x==1) return Color.rgb(254,139,9);
        if(x==2) return Color.BLUE;
        if(x==3) return Color.RED;
        if(x==4) return Color.GREEN;
        if(x==5) return Color.MAGENTA;
        if(x==6) return Color.YELLOW;

        return -1;
    }


    public Pieza crearPieza(int x, int color){
        contadorPiezas++;
        if(x==0) return new PiezaI(contadorPiezas, color);
        if(x==1) return new PiezaL(contadorPiezas, color);
        if(x==2) return new PiezaLI(contadorPiezas, color);
        if(x==3) return new PiezaZ(contadorPiezas, color);
        if(x==4) return new PiezaZI(contadorPiezas, color);
        if(x==5) return new PiezaT(contadorPiezas, color);
        if(x==6) return new PiezaC(contadorPiezas, color);

        return null;
    }
}
