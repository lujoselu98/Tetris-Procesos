package com.example.activities;

import android.annotation.SuppressLint;

import androidx.appcompat.app.AppCompatActivity;

import com.example.utils.CreadorPiezas;
import com.example.views.Ventana;
import com.example.pieces.Bloque;
import com.example.pieces.Pieza;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

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
    private int columnas = 10;
    private int filas = 20;
    private int eliminateRows = 0;
    private boolean seHaCambiado;
    // TODO: 25/10/2019 : Pasarle el parámetro desde la pantalla de inicio
    private boolean modoFantasia;
    private boolean modoLegacy = true;
    private HashSet<Bloque> eliminados = new HashSet<>();

    @SuppressLint("ResourceType")
    public TableroTetris(MainActivity mainActivity, Ventana v, boolean modoFantasia) {
        tablero = new Bloque[20][10];
        creador = new CreadorPiezas(mainActivity);
        piezaActual = creador.crearPieza(2 * eliminateRows);
        piezaSiguiente = creador.crearPieza(2 * eliminateRows);
        seHaCambiado = false;
        this.modoFantasia = modoFantasia;
        this.mainActivity = mainActivity;
        this.ventana = v;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int[] pos = {i, j};
                tablero[i][j] = new Bloque(false, 0, -1, pos);
            }
        }
    }

    public Bloque bloqueEn(int fila, int columna) {
        return this.tablero[fila][columna];
    }

    public Pieza getPiezaSig() {
        return piezaSiguiente;
    }

    public int getEliminateRows() {
        return this.eliminateRows;
    }

    public Pieza getPiezaRapida() {
        return piezaRapida;
    }

    public void bajar(Pieza pieza) {
        if (mainActivity.getH().getPuedoMover()) {
            pieza.bajar();
            if (noPosible(pieza)) {
                pieza.subir();
                siguientePieza();
            }
        }
    }

    public void bajarPiezaRapida(Pieza pieza) {
        pieza.bajar();
        if (noPosible(pieza)) {
            pieza.subir();
            posarPiezaActual(pieza);
            ventana.borrarPieza(pieza);
        }
    }

    public void despDcha(Pieza pieza) {
        if (mainActivity.getH().getPuedoMover()) {
            pieza.despDcha();
            if (noPosible(pieza)) {
                pieza.despIzqda();
            }
        }
    }

    public void despIzqda(Pieza pieza) {
        if (mainActivity.getH().getPuedoMover()) {
            pieza.despIzqda();
            if (noPosible(pieza)) {
                pieza.despDcha();
            }
        }
    }

    public void rotarDcha(Pieza pieza) {
        if (mainActivity.getH().getPuedoMover()) {
            pieza.rotarDcha();
            if (noPosible(pieza)) {
                pieza.rotarIzqda();
            }
        }
    }

    public void rotarIzqda(Pieza pieza) {
        if (mainActivity.getH().getPuedoMover()) {
            pieza.rotarIzqda();
            if (noPosible(pieza)) {
                pieza.rotarDcha();
            }
        }
    }

    public boolean noPosible(Pieza pieza) {

        List<Bloque> b = pieza.bloquesActivos();

        boolean siBajo = true;

        for (Bloque bloque : b) {
            siBajo = (bloque.isInBounds(filas, columnas) && !posicionOcupada(bloque.getPosicion()));
            if (!siBajo) {
                break;
            }
        }

        return !siBajo;
    }

    private void siguientePieza() {
        if (!comprobarPerdido()) {
            posarPiezaActual(piezaActual);
            ventana.borrarPieza(piezaActual);
            this.piezaActual = piezaSiguiente;
            ventana.setPieza(piezaActual);
            piezaSiguiente = creador.crearPieza(2 * eliminateRows);
        }
    }

    private void posarPiezaActual(Pieza pieza) {
        seHaCambiado = false;
        int filaMayor = 0;
        int filaMenor = filas - 1;
        List<Bloque> bloques = pieza.bloquesActivos();
        contadorPiezas++;
        for (Bloque bloque : bloques) {
            int[] pos = bloque.getPosicion();
            tablero[pos[0]][pos[1]] = bloque;
            if (filaMayor < pos[0]) {
                filaMayor = pos[0];
            }
            if (filaMenor > pos[0]) {
                filaMenor = pos[0];
            }
        }
        if (modoLegacy) {
            borrarColores();
        } else {
            borrarLineas(filaMayor, filaMenor);
        }
    }

    private void borrarColores() {
        int color;
        int n, m = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                Bloque b = tablero[i][j];
                if (b.isActivo()) {
                    eliminados.clear();
                    color = b.getColor();
                    eliminados.add(b);
                    comprobarAdyacentes(color, i, j);
                    if (eliminados.size() >= 5) {
                        for (Bloque bloque : eliminados) {
                            bloque.desactivar();
                        }
                        mainActivity.sumar_puntuacion(20);
                    }
                }
            }
        }
    }

    private void comprobarAdyacentes(int color, int i, int j) {
        if (i < filas - 1) {
            if (tablero[i + 1][j].isActivo() && tablero[i + 1][j].getColor() == color && !eliminados.contains(tablero[i + 1][j])) {
                eliminados.add(tablero[i + 1][j]);
                comprobarAdyacentes(color, i + 1, j);
            }
        }
        if (j < columnas - 1) {
            if (tablero[i][j + 1].isActivo() && tablero[i][j + 1].getColor() == color && !eliminados.contains(tablero[i][j + 1])) {
                eliminados.add(tablero[i][j + 1]);
                comprobarAdyacentes(color, i, j + 1);
            }
        }
        if (i > 0) {
            if (tablero[i - 1][j].isActivo() && tablero[i - 1][j].getColor() == color && !eliminados.contains(tablero[i - 1][j])) {
                eliminados.add(tablero[i - 1][j]);
                comprobarAdyacentes(color, i - 1, j);
            }
        }
        if (j > 0) {
            if (tablero[i][j - 1].isActivo() && tablero[i][j - 1].getColor() == color && !eliminados.contains(tablero[i][j - 1])) {
                eliminados.add(tablero[i][j - 1]);
                comprobarAdyacentes(color, i, j - 1);
            }
        }
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

    public boolean comprobarPerdido() {
        int i = 0;
        while (i < columnas && !perdido) {
            perdido = tablero[2 * eliminateRows][i].isActivo();
            i++;
        }
        return perdido;
    }

    public void hard_Drop(Pieza pieza) {
        if (mainActivity.getH().getPuedoMover()) {
            int n = contadorPiezas;
            //Mientras que no se haya posado otra pieza
            while (contadorPiezas - n == 0) {
                bajar(pieza);
            }
        }
    }

    private void borrarLineas(int filaMayor, int filaMenor) {
        int contadorFilasLlenas = 0;
        int i = filaMayor;
        while (i >= filaMenor) {
            if (comprobarFilaLlena(i)) {
                contadorFilasLlenas++;
                borrarfila(i);
                mainActivity.sumar_puntuacion(30);
                filaMenor--;
            } else {
                i--;
            }
        }

        if (modoFantasia && contadorFilasLlenas == 1) {
            cambiarBloquesaMismoColor();
        } else if (modoFantasia && contadorFilasLlenas > 1) {
            cambiarBloquesColorRandom();
        }

    }

    private void borrarfila(int fila) {
        System.out.println("Estoy en borrar fila");
        for (int i = fila; i > eliminateRows * 2; i--) {
            for (int j = 0; j < columnas; j++) {
                tablero[i][j] = new Bloque(tablero[i - 1][j]);
                tablero[i][j].bajar();
            }
        }
    }

    private boolean comprobarFilaLlena(int fila) {
        for (int j = 0; j < columnas; j++) {
            if (!tablero[fila][j].isActivo()) {
                return false;
            }
        }
        return true;
    }

    public boolean piezasChocan() {
        List<Bloque> bloquesActual = piezaActual.bloquesActivos();
        List<Bloque> bloquesRapido = piezaRapida.bloquesActivos();

        boolean seChocan = false;

        for (Bloque ba : bloquesActual) {
            for (Bloque br : bloquesRapido) {
                if (ba.mismaPosicion(br)) {
                    return true;
                }
            }
        }

        return seChocan;
    }

    public void eliminarFilas() {
        eliminateRows++;
        for (int i = 0; i < 2 * eliminateRows; i++) {
            for (int j = 0; j < this.columnas; j++) {
                tablero[i][j].setColor(7);
            }
        }
    }

    public int getFilas() {
        return filas;
    }

    public void cambiarBloquesaMismoColor() {
        //Cambiamos al color de la pieza actual
        int color = piezaActual.getColor();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (tablero[i][j].isActivo()) tablero[i][j].setColor(color);
            }
        }
    }

    public void cambiarBloquesColorRandom() {
        Random r = new Random();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                int color = r.nextInt(7);
                if (tablero[i][j].isActivo()) tablero[i][j].setColor(color);
            }
        }
    }

    public void cambiarPieza(){
        if(!seHaCambiado) {
            seHaCambiado = true;
            ventana.borrarPieza(piezaActual);
            Pieza aux = piezaActual.clonar();
            piezaActual = piezaSiguiente;
            piezaSiguiente = aux;
            ventana.setPieza(piezaActual);
        }
    }
}
