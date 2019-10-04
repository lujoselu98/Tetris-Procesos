package com.example.pieces;

import android.graphics.Color;

public class PiezaI extends Pieza4x4 { //El palo

    public PiezaI(int id, int color) {
        super(id, color);

        forma[0][0].activar();
        forma[0][1].activar();
        forma[0][2].activar();
        forma[0][3].activar();
    }
}
