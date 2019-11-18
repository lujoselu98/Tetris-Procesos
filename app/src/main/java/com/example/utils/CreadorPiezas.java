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

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreadorPiezas {

    private int contadorPiezas;
    private final MainActivity mainActivity;
    private static Random r;
    private List<PesoPieza> pesos;
    private final double numPiezas = 7;
    private int minVeces = 0; //Para saber cuantas veces ha salido la pieza que menos veces a salido

    static {
        try {
            r = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public CreadorPiezas(MainActivity mainActivity) {
        contadorPiezas = 1;
        this.mainActivity = mainActivity;
        pesos = new ArrayList<>();
        System.out.println("El valor es:" + (double)1/7);
        for(int i=1; i<=numPiezas; i++){
            int tipo = i-1;
            pesos.add(new PesoPieza(0, tipo));
            System.out.println("Pieza " +i+ " con peso"+0);
        }
    }


    public Pieza crearPieza(int rows) {
        int n = siguientePieza();
        actualizarMin();
        return crearPieza(n, n, rows);
    }

    private void actualizarMin(){
        int minPeso = pesos.get(0).getPeso();
        for(PesoPieza p: pesos){
            if(p.getPeso() < minPeso){
                minPeso = p.getPeso();
            }
        }
        minVeces = minPeso;
    }

    public int siguientePieza(){

        List<PesoPieza> candidatos = new ArrayList<>();
        for(PesoPieza p: pesos){
            if(Math.abs(p.getPeso() - minVeces) < 3){
                candidatos.add(p);
            }
        }
        Random r = new Random();
        int n = r.nextInt(candidatos.size());
        candidatos.get(n).aumentarPeso();

        return candidatos.get(n).getTipoPieza();
    }

    /*private int cogerColor(int x) {
        switch (this.mainActivity.getTipoPieza()) {
            case "Tipo 1":
                switch (x) {
                    case 0:
                        return Color.CYAN;
                    case 1:
                        return Color.rgb(254, 139, 9);
                    case 2:
                        return Color.BLUE;
                    case 3:
                        return Color.RED;
                    case 4:
                        return Color.GREEN;
                    case 5:
                        return Color.MAGENTA;
                    case 6:
                        return Color.YELLOW;
                    default:
                        break;
                }
                break;
            case "Tipo 2":
                switch (x) {
                    case 0:
                        return Color.CYAN;
                    case 1:
                        return Color.GREEN;
                    case 2:
                        return Color.BLUE;
                    case 3:
                        return Color.MAGENTA;
                    case 4:
                        return Color.rgb(254, 139, 9);
                    case 5:
                        return Color.YELLOW;
                    case 6:
                        return Color.RED;
                    default:
                        break;
                }
                break;
            case "Tipo 3":
                switch (x) {
                    case 0:
                        return Color.RED;
                    case 1:
                        return Color.YELLOW;
                    case 2:
                        return Color.MAGENTA;
                    case 3:
                        return Color.GREEN;
                    case 4:
                        return Color.BLUE;
                    case 5:
                        return Color.GRAY;
                    case 6:
                        return Color.CYAN;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return -1;
    }*/


    private Pieza crearPieza(int x, int color, int rows) {
        contadorPiezas++;
        if (x == 0) return new PiezaI(contadorPiezas, color, rows);
        if (x == 1) return new PiezaL(contadorPiezas, color, rows);
        if (x == 2) return new PiezaLI(contadorPiezas, color, rows);
        if (x == 3) return new PiezaZ(contadorPiezas, color, rows);
        if (x == 4) return new PiezaZI(contadorPiezas, color, rows);
        if (x == 5) return new PiezaT(contadorPiezas, color, rows);
        if (x == 6) return new PiezaC(contadorPiezas, color, rows);

        return null;
    }
}
