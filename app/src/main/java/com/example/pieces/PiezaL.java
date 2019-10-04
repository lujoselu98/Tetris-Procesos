package com.example.pieces;

import android.graphics.Color;

public class PiezaL extends Pieza3x3{

    public PiezaL(int id, int color){
        super(id, color);

        //Colocar las piezas
        forma[0][1].activar();
        forma[1][1].activar();
        forma[2][1].activar();
        forma[2][2].activar();
    }
}
