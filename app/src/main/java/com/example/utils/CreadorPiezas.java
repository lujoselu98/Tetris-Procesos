package com.example.utils;

import android.graphics.Color;

import com.example.activities.MainActivity;
import com.example.pieces.Pieza;
import com.example.pieces.PiezaC;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;
import com.example.pieces.PiezaLI;
import com.example.pieces.PiezaT;
import com.example.pieces.PiezaZ;
import com.example.pieces.PiezaZI;

import java.util.Random;

public class CreadorPiezas {

    private int contadorPiezas;
    private final MainActivity mainActivity;
    public CreadorPiezas(MainActivity mainActivity){
        contadorPiezas = 1;
        this.mainActivity = mainActivity;
    }


    public Pieza crearPieza( int rows){
        Random r = new Random();
        int n = r.nextInt(7);

        return crearPieza(n, cogerColor(n),rows);
    }

    private int cogerColor(int x) {
        System.out.println("TIPO COLOR               "+this.mainActivity.getTipoPieza());
        switch (this.mainActivity.getTipoPieza()){
            case "Tipo 1":
                if(x==0) return Color.CYAN;
                if(x==1) return Color.rgb(254,139,9);
                if(x==2) return Color.BLUE;
                if(x==3) return Color.RED;
                if(x==4) return Color.GREEN;
                if(x==5) return Color.MAGENTA;
                if(x==6) return Color.YELLOW;
            break;
            case "Tipo 2":
                if(x==0) return Color.CYAN; //I
                if(x==1) return Color.GREEN;  //L
                if(x==2) return Color.BLUE;  //L Invertida
                if(x==3) return Color.MAGENTA;  //Z
                if(x==4) return Color.rgb(254,139,9);  //Z Invertida
                if(x==5) return Color.YELLOW;  // T
                if(x==6) return Color.RED;  // Cuadrado
            break;
            case "Tipo 3":
                if(x==0) return Color.RED; //I
                if(x==1) return Color.YELLOW;  //L
                if(x==2) return Color.MAGENTA;  //L Invertida
                if(x==3) return Color.GREEN;  //Z
                if(x==4) return Color.BLUE;  //Z Invertida
                if(x==5) return Color.GRAY;  // T
                if(x==6) return Color.CYAN;  // Cuadrado
            break;
            case "Color Único":
                if(x==0) return Color.YELLOW; //I
                if(x==1) return Color.YELLOW;  //L
                if(x==2) return Color.YELLOW;  //L Invertida
                if(x==3) return Color.YELLOW;  //Z
                if(x==4) return Color.YELLOW;  //Z Invertida
                if(x==5) return Color.YELLOW;  // T
                if(x==6) return Color.YELLOW;  // Cuadrado
            break;
        }
       /* if(x==0) return Color.CYAN;
        if(x==1) return Color.rgb(254,139,9);
        if(x==2) return Color.BLUE;
        if(x==3) return Color.RED;
        if(x==4) return Color.GREEN;
        if(x==5) return Color.MAGENTA;
        if(x==6) return Color.YELLOW;*/

        return -1;
    }


    private Pieza crearPieza(int x, int color, int rows){
        contadorPiezas++;
        if(x==0) return new PiezaI(contadorPiezas, color,rows);
        if(x==1) return new PiezaL(contadorPiezas, color,rows);
        if(x==2) return new PiezaLI(contadorPiezas, color,rows);
        if(x==3) return new PiezaZ(contadorPiezas, color,rows);
        if(x==4) return new PiezaZI(contadorPiezas, color,rows);
        if(x==5) return new PiezaT(contadorPiezas, color,rows);
        if(x==6) return new PiezaC(contadorPiezas, color,rows);

        return null;
    }
}
