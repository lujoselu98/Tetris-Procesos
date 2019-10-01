package com.example.pieces;

import android.graphics.Color;

public abstract class Pieza {
    int identificador;
    Bloque[][] forma;
    int[] centro;
    Color color;
    //int[] bloquesActivos; //Los bloques activos en la forma. Posicion i guarda la columna j : [fila1] = columna3

    public Pieza(int id, Color color){
        this.identificador = id;
        this.color = color;
    }

    public void rotarDcha(){
        int filas = forma.length;
        int columnas = forma[0].length;
        Bloque[][] aux = new Bloque[filas][columnas];

        for(int f=0; f<filas; f++){
            for(int c=0; c<columnas; c++){
                aux[f][c] = forma[columnas+1-c][f];
                aux[f][c].setPosicion(forma[f][c].getPosicion());//Actualizamos la posicion del bloque que acabamos de trasladar

            }
        }

        forma = aux;
    }

    public void rotarIzqda(){ //Podria hacer girar a la derecha tres veces pero me parece muy sucio
        int filas = forma.length;
        int columnas = forma[0].length;
        Bloque[][] aux = new Bloque[filas][columnas];

        for(int f=0; f<filas; f++){
            for(int c=0; c<columnas; c++){
                aux[f][c] = forma[c][filas+1-f]; //Trasladamos el bloque
                aux[f][c].setPosicion(forma[f][c].getPosicion());//Actualizamos la posicion del bloque que acabamos de trasladar
            }
        }

        forma = aux;
    }

    public void actualizarBloquesActivos(){

    }

    public void bajar(){
        centro[0] += 1;
        desplazarBloques(1,0);
    } //Desplazar una fila hacia abajo

    public void despIzqda(){
        centro[1] -= 1;
        desplazarBloques(0,-1);
    } //Desplazar una fila hacia la izqda

    public void despDcha(){
        centro[1] += 1;
        desplazarBloques(0,1);
    } //Desplazar una fila hacia la dcha

    public int getIdentificador() {
        return identificador;
    }

    public Bloque[][] getForma() {
        return forma;
    }

    public int[] getCentro() {
        return centro;
    }

    /*public int[] getBloquesActivos() {
        return bloquesActivos;
    }*/

    private void desplazarBloques(int fOffset, int cOffset){ //Para desplazar los bloques hacia abajo o los lados
        for(int i=0; i<forma.length; i++){
            for(int j=0; j<forma.length; j++){
                int[] nuevaPos = new int[2];
                nuevaPos[0] = forma[i][j].getPosicion()[0]+fOffset;
                nuevaPos[1] = forma[i][j].getPosicion()[1]+cOffset;
                forma[i][j].setPosicion(nuevaPos);
            }
        }
    }

    public Color getColor() {
        return color;
    }
}
