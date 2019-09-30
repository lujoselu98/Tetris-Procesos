package com.example.pieces;

import android.graphics.Color;

public class PiezaZ extends Pieza3x3{

    public PiezaZ(int id, Color color){
        super(id,color);

        forma[0][1].activar();
        forma[0][2].activar();
        forma[1][0].activar();
        forma[1][1].activar();
    }
}
