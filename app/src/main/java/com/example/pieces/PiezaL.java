package com.example.pieces;

public class PiezaL extends Pieza3x3{

    public PiezaL(int id, int color, int rows){
        super(id, color,rows);

        //Colocar las piezas
        forma[0][1].activar();
        forma[1][1].activar();
        forma[2][1].activar();
        forma[2][2].activar();
    }

    public Pieza clonar(){
        return new PiezaL(this.id, this.color, 0);
    }
}
