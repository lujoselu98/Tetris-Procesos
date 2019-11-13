package com.example.pieces;

public class PiezaC extends Pieza2x2 { //El cuadrado

    public PiezaC(int id, int color, int rows) {
        super(id, color,rows);

        forma[0][0].activar();
        forma[0][1].activar();
        forma[1][0].activar();
        forma[1][1].activar();
    }

    public Pieza clonar(){
        return new PiezaC(this.id, this.color, 0);
    }
}
