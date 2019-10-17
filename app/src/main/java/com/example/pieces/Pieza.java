package com.example.pieces;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Pieza {
    int identificador;
    Bloque[][] forma;
    int[] centro;
    int color;
    //int[] bloquesActivos; //Los bloques activos en la forma. Posicion i guarda la columna j : [fila1] = columna3

    public Pieza(int id, int color){
        this.identificador = id;
        this.color = color;
    }

    public void rotarDcha(){
        int filas = forma.length;
        int columnas = forma[0].length;
        Bloque[][] aux = new Bloque[filas][columnas]; //Matriz de bloques de la pieza rotada
        int[] posAux = new int[2]; //Para guardar la nueva posición del bloque

        //Bucle para cambiar las filas por columnas
        for(int f=0; f<filas; f++){
            for(int c=0; c<columnas; c++){

                //Copiamos el bloque
                aux[f][c] = new Bloque(forma[columnas-1-c][f]);

                //Ajustamos el valor de su posición.
                posAux = Arrays.copyOf(forma[f][c].getPosicion(),2);
                System.out.println(Arrays.toString(posAux));
                aux[f][c].setPosicion(posAux);//Actualizamos la posicion del bloque que acabamos de trasladar*/

            }
        }

        forma = aux;
    }

    public void rotarIzqda(){ //Podria hacer girar a la derecha tres veces pero me parece muy sucio
        int filas = forma.length;
        System.out.println(filas);
        int columnas = forma[0].length;
        System.out.println(columnas);
        Bloque[][] aux = new Bloque[filas][columnas];

        for(int f=0; f<filas; f++){
            for(int c=0; c<columnas; c++){
                int[] posAux;
                aux[f][c] = new Bloque(forma[c][filas-1-f]); //Copiamos el bloque

                //Ajustamos el valor de su posición.
                posAux = Arrays.copyOf(forma[f][c].getPosicion(),2);
                System.out.println(Arrays.toString(posAux));
                aux[f][c].setPosicion(posAux);//Actualizamos la posicion del bloque que acabamos de trasladar*/
            }
        }



        forma = aux;
    }

    public void actualizarBloquesActivos(){

    }

    public void subir(){
        centro[0] -= 1;
        desplazarBloques(-1,0);
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

    public int getColor() {
        return color;
    }

    public List<Bloque> bloquesActivos(){
        List<Bloque> bloques = new ArrayList();

        for(int i=0; i<forma.length; i++){
            for(int j=0; j<forma.length; j++){
                if(forma[i][j].isActivo()){
                    bloques.add(forma[i][j]);
                }
            }
        }

        return bloques;
    }
}
