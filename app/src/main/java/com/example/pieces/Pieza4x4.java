package com.example.pieces;

public class Pieza4x4 extends Pieza{

    private boolean ultGiroDcha;
    private boolean ultGiroIzqda;

    Pieza4x4(int id, int color, int rows){
        super(id, color);
        ultGiroDcha = false;
        ultGiroIzqda = false;
        //Creamos los bloques
        this.forma = new Bloque[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int[] posBloque = new int[2];
                posBloque[0] = i;
                posBloque[1] = j+3; //Para que el bloque también esté desplazado al centro
                this.forma[i][j] = new Bloque(false,id,color,posBloque);
            }
        }

        desplazarBloques(rows,0);
    }

    //Solo puede rotar en un sentido una vez seguida así la Pieza Linea no se desplaza de columna al girar
    @Override
    public void rotarDcha() {
        if(!ultGiroDcha) {
            super.rotarDcha();
            ultGiroIzqda = false;
            ultGiroDcha = true;
        }else{
            rotarIzqda();
        }
    }

    @Override
    public void rotarIzqda() {
        if(!ultGiroIzqda) {
            super.rotarIzqda();
            ultGiroIzqda = true;
            ultGiroDcha = false;
        }else{
            rotarDcha();
        }
    }

    public Pieza clonar(){
        return null;
    }
}
