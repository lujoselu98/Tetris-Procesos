package com.example.pieces;

import android.graphics.Color;

public class Pieza3x3 extends Pieza{

    public Pieza3x3(int id, Color color){
        super(id, color);

        centro = new int[]{1,4};

        this.forma = new Bloque[3][3];

        //Creamos los bloques
        this.forma = new Bloque[3][3];
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int[] posBloque = new int[2];
                posBloque[0] = i;
                posBloque[1] = j+3; //Para que el bloque también esté desplazado al centro
                this.forma[i][j] = new Bloque(false,id,color,posBloque);
            }
        }
    }

}
