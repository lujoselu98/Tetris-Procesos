package com.example.pieces;

public class PiezaZI extends Pieza3x3 {

    public PiezaZI(int id, int color, int rows) {
        super(id, color,rows);

        forma[0][1].activar();
        forma[0][2].activar();
        forma[1][0].activar();
        forma[1][1].activar();
    }

    public Pieza clonar(){
        return new PiezaZI(this.id, this.color, 0);
    }
}
