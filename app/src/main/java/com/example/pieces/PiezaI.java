package com.example.pieces;

import android.graphics.Color;

public class PiezaI extends Pieza4x4 { //El palo

    public PiezaI(int id, Color color) {
        super(id, color);

        forma[2][0].activar();
        forma[2][1].activar();
        forma[2][2].activar();
        forma[2][3].activar();
    }
}
