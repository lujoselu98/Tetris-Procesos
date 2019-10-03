package com.example.pieces;

import android.graphics.Color;

public class PiezaC extends Pieza2x2 { //El cuadrado

    public PiezaC(int id, int color) {
        super(id, color);

        forma[0][0].activar();
        forma[0][1].activar();
        forma[1][0].activar();
        forma[1][1].activar();
    }
}
