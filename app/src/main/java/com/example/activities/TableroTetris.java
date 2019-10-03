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
    private boolean perdido = false;
    private int contadorPiezas = 0;

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
        if(!esPosible()){
            piezaActual.subir();
            siguientePieza();
        }
    }

    public void despDcha(){
        piezaActual.despDcha();
        if(!esPosible()){
            piezaActual.despIzqda();
        }
    }

    public void despIzqda(){
        piezaActual.despIzqda();
        if(!esPosible()){
            piezaActual.despDcha();
        }
    }

    public void rotarDcha(){
        piezaActual.rotarDcha();
        if(!esPosible()){
            piezaActual.rotarIzqda();
        }
    }

    public void rotarIzqda(){
        piezaActual.rotarIzqda();
        if(!esPosible()){
            piezaActual.rotarDcha();
        }
    }

    public boolean esPosible(){
        List<Bloque> b = piezaActual.bloquesActivos();

        boolean siBajo = true;

        for(Bloque bloque: b){
            siBajo = (bloque.isInBounds(FILAS,COLUMNAS) && !posicionOcupada(bloque.getPosicion()));
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
        contadorPiezas++;
        for(Bloque bloque: bloques){
            int pos[] = bloque.getPosicion();
            tablero[pos[0]][pos[1]] = bloque;
        }
    }

    public Pieza getPiezaActual() {
        return piezaActual;
    }

    public boolean posicionOcupada(int[] pos) {
        return tablero[pos[0]][pos[1]].isActivo();
    }

    public boolean comprobarPerdido(){
        int i=0;
        while(i < COLUMNAS && !perdido){
            perdido = tablero[0][i].isActivo();
            i++;
        }
        return perdido;
    }
    public void hard_Drop (){
        int n = contadorPiezas;
        //Mientras que no se haya posado otra pieza
        while(contadorPiezas - n == 0){
            bajar();
        }
    }
}
