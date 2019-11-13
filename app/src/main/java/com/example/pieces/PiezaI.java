package com.example.pieces;

public class PiezaI extends Pieza4x4 { //El palo

    public PiezaI(int id, int color, int rows) {
        super(id, color,rows);

        forma[1][0].activar();
        forma[1][1].activar();
        forma[1][2].activar();
        forma[1][3].activar();
    }

    public Pieza clonar(){
        return new PiezaI(this.id, this.color, 0);
    }
}
