package com.example.pieces;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Pieza{
    protected Bloque[][] forma;
    protected int id;
    protected int color;


    Pieza(int id, int color) {
        this.id = id;
        this.color = color;
    }

    public abstract Pieza clonar();

    public void rotarDcha() {
        int filas = forma.length;
        int columnas = forma[0].length;
        Bloque[][] aux = new Bloque[filas][columnas]; //Matriz de bloques de la pieza rotada
        int[] posAux; //Para guardar la nueva posición del bloque

        //Bucle para cambiar las filas por columnas
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                //Copiamos el bloque
                aux[f][c] = new Bloque(forma[columnas - 1 - c][f]);
                //Ajustamos el valor de su posición.
                posAux = Arrays.copyOf(forma[f][c].getPosicion(), 2);
                aux[f][c].setPosicion(posAux);//Actualizamos la posicion del bloque que acabamos de trasladar*/

            }
        }

        forma = aux;
    }

    public void rotarIzqda() { //Podria hacer girar a la derecha tres veces pero me parece muy sucio
        int filas = forma.length;
        int columnas = forma[0].length;
        Bloque[][] aux = new Bloque[filas][columnas];

        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                int[] posAux;
                aux[f][c] = new Bloque(forma[c][filas - 1 - f]); //Copiamos el bloque

                //Ajustamos el valor de su posición.
                posAux = Arrays.copyOf(forma[f][c].getPosicion(), 2);
                aux[f][c].setPosicion(posAux);//Actualizamos la posicion del bloque que acabamos de trasladar*/
            }
        }
        forma = aux;
    }

    public void subir() {
        desplazarBloques(-1, 0);
    }

    public void bajar() {
        desplazarBloques(1, 0);
    } //Desplazar una fila hacia abajo

    public void despIzqda() {
        desplazarBloques(0, -1);
    } //Desplazar una fila hacia la izqda

    public void despDcha() {
        desplazarBloques(0, 1);
    } //Desplazar una fila hacia la dcha

    public Bloque[][] getForma() {
        return forma;
    }

    void desplazarBloques(int fOffset, int cOffset) { //Para desplazar los bloques hacia abajo o los lados
        for (Bloque[] bloques1 : forma) {
            for (Bloque bloque : bloques1) {
                int[] nuevaPos = new int[2];
                nuevaPos[0] = bloque.getPosicion()[0] + fOffset;
                nuevaPos[1] = bloque.getPosicion()[1] + cOffset;
                bloque.setPosicion(nuevaPos);
            }
        }
    }

    public List<Bloque> bloquesActivos() {
        ArrayList<Bloque> bloques = new ArrayList<>();
        for (Bloque[] bloques1 : forma) {
            for (Bloque bloque : bloques1) {
                if (bloque.isActivo()) {
                    bloques.add(bloque);
                }
            }
        }
        return bloques;
    }

    public int getColor() {
        return color;
    }


}
