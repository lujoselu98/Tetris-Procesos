package com.example.pieces;

public class Pieza2x2 extends Pieza {

    Pieza2x2(int id, int color, int rows){
        super(id);

        //centro = new int[]{0,4};

        //Creamos los bloques
        this.forma = new Bloque[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int[] posBloque = new int[2];
                posBloque[0] = i;
                posBloque[1] = j+4; //Para que el bloque también esté desplazado al centro del tablero
                this.forma[i][j] = new Bloque(false,id,color,posBloque);
            }
        }

        for (int i = 0; i < rows; i++){
            bajar();
        }
    }

    public void rotarDcha(){ //No rotan
    }

    public void rotarIzqda(){ //No rotan
    }
}
