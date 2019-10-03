package com.example.activities;

import android.graphics.Color;

import com.example.pieces.Bloque;
import com.example.pieces.Pieza;

import java.util.List;

public class TableroTetris {
    private Bloque[][] tablero;
    private Pieza piezaSiguiente;
    private Pieza piezaActual;
    private CreadorPiezas creador;

    private final int COLUMNAS = 10;
    private final int FILAS = 20;

    public TableroTetris(){
        tablero = new Bloque[20][10];
        creador = new CreadorPiezas();
        piezaActual = creador.crearPieza();
        piezaSiguiente = creador.crearPieza();

        for(int i=0; i<FILAS; i++){
            for(int j=0; j<COLUMNAS; j++){
                int[] pos = {i,j};
                tablero[i][j] = new Bloque(false, 0, Color.WHITE, pos);
            }
        }
    }

    public Bloque bloqueEn(int fila, int columna){
        return this.tablero[fila][columna];
    }

    public void bajar(){
        piezaActual.bajar();
        if(!puedoBajar()){
            piezaActual.subir();
            siguientePieza();
        }
    }

    public boolean puedoBajar(){
        List<Bloque> b = piezaActual.bloquesActivos();

        boolean siBajo = true;

        for(Bloque bloque: b){
            siBajo = (bloque.isInBounds(FILAS,COLUMNAS) && bloque.seChocaCon(bloqueEn(bloque.getPosicion()[0], bloque.getPosicion()[1])));
            if(!siBajo){
                break;
            }
        }

        return siBajo;
    }

    public void siguientePieza(){
        posarPiezaActual();
        this.piezaActual = piezaSiguiente;
        piezaSiguiente = creador.crearPieza();
    }

    public void posarPiezaActual(){
        List<Bloque> bloques = piezaActual.bloquesActivos();

        for(Bloque bloque: bloques){
            int pos[] = bloque.getPosicion();
            tablero[pos[0]][pos[1]] = bloque;
        }
    }

    public Pieza getPiezaActual() {
        return piezaActual;
    }
}
