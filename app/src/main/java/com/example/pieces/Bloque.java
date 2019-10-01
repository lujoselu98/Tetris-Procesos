package com.example.pieces;

import android.graphics.Color;

public class Bloque {
    private boolean activo; //Si está activo significa que está en el tablero o que es parte de una pieza que se está moviendo (si está activo se pinta)
    private Color color;
    private int identificador; //Es el número de piezas que han salido hasta el momento, así no se repite
    private int[] posicion;

    public Bloque(boolean activo, int identificador, Color color, int[] posicion) {
        this.activo = activo;
        this.color = color;
        this.identificador = identificador;
    }

    public boolean isActivo() {
        return activo;
    }

    public void activar(){
        this.activo = true;
    }

    public int getId() {
        return identificador;
    }

    public boolean seChocaCon(Bloque otro) {
        return (this.getId() != otro.getId() && otro.isActivo());
    }

    public void bajar() { posicion[0] += 1;} //Desplazar una fila hacia abajo

    public void despIzqda() { posicion[1] -= 1;} //Desplazar una fila hacia la izqda }

    public void despDcha(){ posicion[1] += 1;} //Desplazar una fila hacia la dcha }

    public void setPosicion(int[] pos){
        this.posicion[0] = pos[0];
        this.posicion[1] = pos[1];
    }

    public int[] getPosicion(){
        return this.posicion;
    }

}
