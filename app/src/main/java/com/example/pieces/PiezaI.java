package com.example.pieces;

import android.graphics.Color;

public class PiezaI extends Pieza4x4 { //El palo

    public PiezaI(int id, Color color) {
        super(id, color);

        forma[1][0].activar();
        forma[1][1].activar();
        forma[1][2].activar();
        forma[1][3].activar();
    }
}
