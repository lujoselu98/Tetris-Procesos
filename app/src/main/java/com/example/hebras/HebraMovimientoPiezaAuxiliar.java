package com.example.hebras;


import com.example.utils.CreadorPiezas;
import com.example.activities.MainActivity;
import com.example.activities.TableroTetris;
import com.example.views.Ventana;
import com.example.pieces.Pieza;

/* IGUAL QUE EL WORKTHREAD */
public class HebraMovimientoPiezaAuxiliar extends Thread {
    private boolean puedoMover;
    private boolean finPartida;
    private int velocidadCaida = 1000;
    private final Ventana ventana;
    private TableroTetris tetris;
    private final CreadorPiezas creadorPiezas;
    private Pieza pieza;
    private boolean hebraActiva;

    public void setHebraActiva(boolean hebraActiva) {
        this.hebraActiva = hebraActiva;
    }

    public HebraMovimientoPiezaAuxiliar(boolean puedoMover, MainActivity mainActivity, Ventana v, int velocidad, boolean hebraActiva) {
        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.ventana = v;
        if (velocidad != 0) {
            this.velocidadCaida = velocidadCaida / velocidad;
        }
        this.creadorPiezas = new CreadorPiezas(mainActivity);
        this.hebraActiva = hebraActiva;
    }


    @Override
    public void run() {
        while (!finPartida) {
            if (hebraActiva && puedoMover) {
                System.out.println("VOY A CREAR SEGUNDA PIEZA");
                if (tetris.getPiezaRapida() == null) {
                    pieza = creadorPiezas.crearPieza(tetris.getEliminateRows());
                    ventana.setPieza(pieza);
                    tetris.setPiezaRapida(pieza);
                }
                while (puedoMover) {
                    if (tetris.noPosible(pieza)) {
                        hebraActiva = false;
                        puedoMover = false;
                    }
                    tetris.bajarPiezaRapida(tetris.getPiezaRapida());
                    if (tetris.piezasChocan()) {
                        ventana.borrarPieza(tetris.getPiezaRapida());
                        tetris.setPiezaRapida(null);
                        puedoMover = false;
                        hebraActiva = false;
                    }

                    ventana.invalidate();

                    if (tetris.comprobarPerdido()) {
                        System.out.println("PERDIDO");
                        this.finPartida = true;
                        puedoMover = false;
                    }
                    try {

                        Thread.sleep(velocidadCaida);

                    } catch (InterruptedException ignored) {

                    }

                }
            }
        }
    }

    public void setPuedoMover(boolean mover) {
        this.puedoMover = mover;
    }

    public Pieza getPiezaActual() {
        return this.pieza;
    }


    public void setTableroTetris(TableroTetris tableroTetris) {
        this.tetris = tableroTetris;
    }

    public boolean isHebraActiva() {
        return hebraActiva;
    }
}
