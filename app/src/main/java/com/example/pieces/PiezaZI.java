package com.example.pieces;

import android.graphics.Color;

public class PiezaZI extends Pieza3x3 {

    public PiezaZI(int id, Color color) {
        super(id, color);

        forma[0][0].activar();
        forma[0][1].activar();
        forma[1][1].activar();
        forma[1][2].activar();
    }
}
