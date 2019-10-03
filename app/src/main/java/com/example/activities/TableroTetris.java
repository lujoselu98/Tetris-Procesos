package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pieces.Bloque;
import com.example.pieces.Pieza;
import com.example.pieces.PiezaC;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;
import com.example.pieces.PiezaLI;
import com.example.pieces.PiezaT;
import com.example.pieces.PiezaZ;
import com.example.pieces.PiezaZI;

public class TableroTetris extends AppCompatActivity {
    private Bloque[][] tablero;
    private Pieza piezaSiguiente;
    private Pieza piezaActual;
    private CreadorPiezas creador;

    private final int COLUMNAS = 10;
    private final int FILAS = 20;

    @SuppressLint("ResourceType")
    public TableroTetris(){
        tablero = new Bloque[20][10];
        creador = new CreadorPiezas();
        piezaActual = creador.crearPieza();
        piezaSiguiente = creador.crearPieza();


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

    public Pieza getPiezaSig() {
        return piezaSiguiente;
    }

    /*public void piezaSig(){
        ImageView iv = (ImageView) findViewById(R.id.piezaSiguiente);
        Pieza piezaSig = getPiezaSig();
        if(piezaSig instanceof PiezaC){
            iv.setImageResource(R.drawable.cuadrado);
        }else if(piezaSig instanceof PiezaI){
            iv.setImageResource(R.drawable.i);
        }else if(piezaSig instanceof PiezaL){
            iv.setImageResource(R.drawable.l);
        }else if(piezaSig instanceof PiezaLI){
            iv.setImageResource(R.drawable.l_invertida);
        }else if(piezaSig instanceof PiezaT){
            iv.setImageResource(R.drawable.t);
        }else if(piezaSig instanceof PiezaZ){
            iv.setImageResource(R.drawable.z);
        }else if(piezaSig instanceof PiezaZI){
            iv.setImageResource(R.drawable.s);
        }
    }*/
}
