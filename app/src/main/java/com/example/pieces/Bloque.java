package com.example.pieces;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Bloque {
    private boolean activo; //Si está activo significa que está en el tablero o que es parte de una pieza que se está moviendo (si está activo se pinta)
    private int color;
    private int identificador; //Es el número de piezas que han salido hasta el momento, así no se repite
    private int[] posicion;

    public Bloque(boolean activo, int identificador, int color, int[] posicion) {
        this.activo = activo;
        this.color = color;
        this.identificador = identificador;
        this.posicion = posicion;
    }

    //Constructor que hace una copia de otro bloque;
    public Bloque(Bloque bloque){
        this.activo = bloque.isActivo();
        this.color = bloque.getColor();
        this.identificador = bloque.getIdentificador();
        this.posicion = Arrays.copyOf(bloque.getPosicion(),2);
    }

    public boolean isActivo() {
        return activo;
    }

    public void activar(){
        this.activo = true;
    }

    public boolean seChocaCon(Bloque otro) {
        return (this.getIdentificador() != otro.getIdentificador() && otro.isActivo());
    }

    public void bajar() { posicion[0] += 1;} //Desplazar una fila hacia abajo

    public void despIzqda() { posicion[1] -= 1;} //Desplazar una fila hacia la izqda }

    public void despDcha(){ posicion[1] += 1;} //Desplazar una fila hacia la dcha }

    public void setPosicion(int[] pos){
        this.posicion[0] = pos[0];
        this.posicion[1] = pos[1];
    }

    public int[] getPosicion(){
        int[] aux = new int[2];
        aux[0] = this.posicion[0];
        aux[1] = this.posicion[1];

        return aux;
    }

    public int getColor() {
        return color;
    }

    public int getIdentificador() {
        return identificador;
    }
}
