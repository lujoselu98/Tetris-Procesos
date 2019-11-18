package com.example.pieces;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class Bloque {
    private boolean activo; //Si está activo significa que está en el tablero o que es parte de una pieza que se está moviendo (si está activo se pinta)
    private int color;
    private final int identificador; //Es el número de piezas que han salido hasta el momento, así no se repite
    private final int[] posicion;

    public Bloque(boolean activo, int identificador, int color, int[] posicion) {
        this.activo = activo;
        this.color = color;
        this.identificador = identificador;
        this.posicion = posicion;
    }

    public Bloque(Bloque bloque) {
        this.activo = bloque.isActivo();
        this.color = bloque.getColor();
        this.identificador = bloque.getIdentificador();
        this.posicion = Arrays.copyOf(bloque.getPosicion(), 2);
    }

    public boolean isActivo() {
        return activo;
    }

    public void activar() {
        this.activo = true;
    }

    public void bajar() {
        posicion[0] += 1;
    } //Desplazar una fila hacia abajo

    public void setPosicion(int[] pos) {
        this.posicion[0] = pos[0];
        this.posicion[1] = pos[1];
    }

    public int[] getPosicion() {
        return posicion;
    }

    public int getColor() {
        return color;
    }

    private int getIdentificador() {
        return identificador;
    }

    public boolean isInBounds(int filas, int columnas) {
        return (posicion[0] >= 0 && posicion[0] < filas && posicion[1] >= 0 && posicion[1] < columnas);
    }

    public boolean mismaPosicion(Bloque b) {
        return (this.posicion[0] == b.getPosicion()[0] && this.posicion[1] == b.getPosicion()[1]);
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void desactivar() {
        this.activo = false;
        this.color = -1;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return mismaPosicion((Bloque) obj) && ((Bloque) obj).color == this.color && ((Bloque) obj).activo == this.activo && ((Bloque) obj).identificador == this.identificador;
    }
}
