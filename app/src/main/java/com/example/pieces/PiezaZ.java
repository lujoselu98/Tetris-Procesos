package com.example.pieces;

public class PiezaZ extends Pieza3x3{

    public PiezaZ(int id, int color, int rows){
        super(id,color,rows);

        forma[0][0].activar();
        forma[0][1].activar();
        forma[1][1].activar();
        forma[1][2].activar();
    }

    public Pieza clonar(){
        return new PiezaZ(this.id, this.color, 0);
    }
}
