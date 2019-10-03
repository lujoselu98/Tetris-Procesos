package com.example.activities;

import android.graphics.Color;

import com.example.pieces.Bloque;
import com.example.pieces.Pieza;

public class TableroTetris {
    private Bloque[][] tablero;
    private Pieza PiezaSiguiente;
    private Pieza PiezaActual;
    private CreadorPiezas creador;

    private final int COLUMNAS = 10;
    private final int FILAS = 20;

    public TableroTetris(){
        tablero = new Bloque[20][10];
        creador = new CreadorPiezas();
        PiezaActual = creador.crearPieza();
        PiezaSiguiente = creador.crearPieza();

        for(int i=0; i<FILAS; i++){
            for(int j=0; j<COLUMNAS; j++){
                int[] pos = new int[2];
                tablero[i][j] = new Bloque(false, 0, Color.YELLOW, pos);
            }
        }
    }

    public Bloque bloqueEn(int fila, int columna){
        return this.tablero[fila][columna];
    }

}
