package com.example.pieces;

import android.graphics.Color;

public class PiezaZ extends Pieza3x3{

    public PiezaZ(int id, int color){
        super(id,color);

        forma[0][0].activar();
        forma[0][1].activar();
        forma[1][1].activar();
        forma[1][2].activar();
    }
}
