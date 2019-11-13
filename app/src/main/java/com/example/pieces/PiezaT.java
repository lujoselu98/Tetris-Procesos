package com.example.pieces;

public class PiezaT extends Pieza3x3{

    public PiezaT(int id, int color, int rows){
        super(id,color,rows);

        forma[0][1].activar();
        forma[1][0].activar();
        forma[1][1].activar();
        forma[1][2].activar();
    }

    public Pieza clonar(){
        return new PiezaT(this.id, this.color, 0);
    }
}
