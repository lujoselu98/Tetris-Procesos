package com.example.activities;

import android.annotation.SuppressLint;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utils.CreadorPiezas;
import com.example.views.Ventana;
import com.example.pieces.Bloque;
import com.example.pieces.Pieza;

import java.util.List;
public class TableroTetris extends AppCompatActivity {
    private final Bloque[][] tablero;
    private Pieza piezaSiguiente;
    private Pieza piezaActual;
    private Pieza piezaRapida;
    private final CreadorPiezas creador;
    private boolean perdido = false;
    private final MainActivity mainActivity;
    private int contadorPiezas = 0;
    private final Ventana ventana;
    private int COLUMNAS = 10;
    private int FILAS = 20;
    private int eliminateRows = 0;
    @SuppressLint("ResourceType")
    public TableroTetris(MainActivity mainActivity, Ventana v){
        tablero = new Bloque[20][10];
        creador = new CreadorPiezas(mainActivity);
        piezaActual = creador.crearPieza(2*eliminateRows);
        piezaSiguiente = creador.crearPieza(2*eliminateRows);
        this.mainActivity = mainActivity;
        this.ventana = v;
        for(int i=0; i<FILAS; i++){
            for(int j=0; j<COLUMNAS; j++){
                int[] pos = {i,j};
                tablero[i][j] = new Bloque(false, 0, Color.GRAY, pos);
            }
        }
    }

    public Bloque bloqueEn(int fila, int columna){
        return this.tablero[fila][columna];
    }

    public Pieza getPiezaSig() {
        return piezaSiguiente;
    }
    public int getEliminateRows(){
        return this.eliminateRows;
    }

    public Pieza getPiezaRapida() {
        return piezaRapida;
    }

    public void bajar(Pieza pieza){
       /* Pieza piezaMover;
        if (piezaActualBool) {
            piezaMover = this.getPiezaActual();
        }else{
            piezaMover = this.getPiezaRapida();
        }*/
        if (mainActivity.getH().getPuedoMover()) {
            pieza.bajar();
            if (noPosible(pieza)) {
                pieza.subir();
                siguientePieza();
                //ventana.borrarPieza(pieza);
            }
        }
    }

    public void bajarPiezaRapida(Pieza pieza){
        pieza.bajar();
        if(noPosible(pieza)){
            pieza.subir();
            posarPiezaActual(pieza);
            ventana.borrarPieza(pieza);
        }
    }
    public void despDcha(Pieza pieza){
        if (mainActivity.getH().getPuedoMover()) {
            pieza.despDcha();
            if (noPosible(pieza)) {
                pieza.despIzqda();
            }
        }
    }

    public void despIzqda(Pieza pieza){
        if (mainActivity.getH().getPuedoMover()) {
            pieza.despIzqda();
            if (noPosible(pieza)) {
                pieza.despDcha();
            }
        }
    }

    public void rotarDcha(Pieza pieza){
        if (mainActivity.getH().getPuedoMover()) {
            pieza.rotarDcha();
            if (noPosible(pieza)) {
                pieza.rotarIzqda();
            }
        }
    }

    public void rotarIzqda(Pieza pieza){
        if (mainActivity.getH().getPuedoMover()) {
            pieza.rotarIzqda();
            if (noPosible(pieza)) {
                pieza.rotarDcha();
            }
        }
    }

    public boolean noPosible(Pieza pieza){

        List<Bloque> b = pieza.bloquesActivos();

        boolean siBajo = true;

        for(Bloque bloque: b){
            siBajo = (bloque.isInBounds(FILAS,COLUMNAS) && !posicionOcupada(bloque.getPosicion()));
            if(!siBajo){
                break;
            }
        }

        return !siBajo;
    }

    private void siguientePieza(){
        if(!comprobarPerdido()) {
            posarPiezaActual(piezaActual);
            ventana.borrarPieza(piezaActual);
            this.piezaActual = piezaSiguiente;
            ventana.setPieza(piezaActual);
            piezaSiguiente = creador.crearPieza(2*eliminateRows);
        }
    }

    private void posarPiezaActual(Pieza pieza){
        int fila_mayor=0;
        int fila_menor=FILAS-1;
        List<Bloque> bloques = pieza.bloquesActivos();
        contadorPiezas++;
        for(Bloque bloque: bloques){
            int[] pos = bloque.getPosicion();
            tablero[pos[0]][pos[1]] = bloque;
            if(fila_mayor<pos[0]){fila_mayor=pos[0];}
            if(fila_menor>pos[0]){fila_menor=pos[0];}
        }
        System.out.println("fila_mayor = " + fila_mayor);
        System.out.println("fila_menor = " + fila_menor);
        borrar_lineas(fila_mayor,fila_menor);
    }

    public Pieza getPiezaActual() {
        return piezaActual;
    }

    public void setPiezaRapida(Pieza piezaRapida) {
        this.piezaRapida = piezaRapida;
    }

    private boolean posicionOcupada(int[] pos) {
        return tablero[pos[0]][pos[1]].isActivo();
    }

    public boolean comprobarPerdido(){
        int i=0;
        while(i < COLUMNAS && !perdido){
            perdido = tablero[2*eliminateRows][i].isActivo();
            i++;
        }
        return perdido;
    }
    public void hard_Drop (Pieza pieza){
        if (mainActivity.getH().getPuedoMover()) {
            int n = contadorPiezas;
            //Mientras que no se haya posado otra pieza
            while (contadorPiezas - n == 0) {
                bajar(pieza);
            }
        }
    }
    private void borrar_lineas(int fila_mayor, int fila_menor){
        int i=fila_mayor;
        while(i>=fila_menor) {
            System.out.println("fila_mayor = " + fila_mayor);
            System.out.println("fila_menor = " + fila_menor);
            System.out.println("i = " + i);
            if(comprobar_fila_llena(i)){
                borrarfila(i);
                mainActivity.sumar_puntuacion( 30);
                fila_menor--;
            }else{
                i--;
            }
        }
    }

    private void borrarfila(int fila) {
        System.out.println("Estoy en borrar fila");
        for (int i = fila; i > eliminateRows*2; i--) {
            for (int j = 0; j < COLUMNAS; j++) {
                tablero[i][j]=new Bloque(tablero[i-1][j]);
                tablero[i][j].bajar();
            }
        }
    }

    private boolean comprobar_fila_llena(int fila){
        for (int j = 0; j < COLUMNAS; j++) {
            if(!tablero[fila][j].isActivo()){return false;}
        }
        return true;
    }

    public boolean piezasChocan(){
        List<Bloque> bloquesActual = piezaActual.bloquesActivos();
        List<Bloque> bloquesRapido = piezaRapida.bloquesActivos();

        boolean seChocan = false;

        for(Bloque ba: bloquesActual){
            for(Bloque br : bloquesRapido){
                if(ba.mismaPosicion(br)){
                    return true;
                }
            }
        }

        return seChocan;
    }
    public void eliminarFilas(){
        eliminateRows++;
        for (int i=0;i<2*eliminateRows;i++){
            for (int j=0;j<this.COLUMNAS;j++){
                tablero[i][j].setColor(Color.BLACK);
            }
        }
    }

    public int getFILAS() {
        return FILAS;
    }

}
