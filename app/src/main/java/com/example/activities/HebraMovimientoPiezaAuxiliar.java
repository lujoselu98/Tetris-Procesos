package com.example.activities;


import com.example.pieces.Pieza;

/* IGUAL QUE EL WORKTHREAD */
public class HebraMovimientoPiezaAuxiliar extends Thread {
    private boolean puedoMover;
    private boolean finPartida;
    private int velocidadCaida = 1000;
    private MainActivity mainActivity;
    private Ventana ventana;
    TableroTetris tetris;
    private CreadorPiezas creadorPiezas;
    private Pieza pieza;
    private boolean hebraActiva;

    public void setHebraActiva(boolean hebraActiva) {
        this.hebraActiva = hebraActiva;
    }

    public HebraMovimientoPiezaAuxiliar(boolean puedoMover, MainActivity mainActivity, Ventana v, int velocidad, boolean hebraActiva) {
        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.ventana = v;
        if (velocidad != 0) {
            this.velocidadCaida = velocidadCaida / velocidad;
        }
        this.creadorPiezas = new CreadorPiezas(mainActivity);
        this.hebraActiva = hebraActiva;
    }


    public TableroTetris getTableroTetris() {
        return tetris;
    }

    @Override
    public void run() {
        while (!finPartida) {
            if (hebraActiva && puedoMover) {
                System.out.println("VOY A CREAR SEGUNDA PIEZA");
                if(tetris.getPiezaRapida() == null) {
                    pieza = creadorPiezas.crearPieza(tetris.getEliminateRows());
                    ventana.setPieza(pieza);
                    tetris.setPiezaRapida(pieza);
                }
                while (puedoMover) {

                    //ventana.setPieza(tetris.getPiezaActual());
                    //tetris.bajar();
                    //tetris.siguientePieza();

                    //ventana.setPieza(tetris.getPiezaSig());
                    //tetris.getPiezaSig().bajar();
                    if (!tetris.esPosible(pieza)) {
                        //puedoMover = false;
                        hebraActiva = false;
                        puedoMover = false;
                    }else{
                        System.out.println("ES POSIBLE MOVER SEGUNDA PIEZA");
                    }



                    tetris.bajarPiezaRapida(tetris.getPiezaRapida());

                    if(tetris.piezasChocan()){
                        ventana.borrarPieza(tetris.getPiezaRapida());
                        tetris.setPiezaRapida(null);
                        puedoMover=false;
                        hebraActiva=false;
                    }

                    ventana.invalidate();

                    if (tetris.comprobarPerdido()) {
                        System.out.println("PERDIDO");
                        setFinPartida();
                        puedoMover = false;
                    }
                    try {

                        Thread.sleep(velocidadCaida);

                    } catch (InterruptedException ignored) {

                    }

                }
            }
        }
        //mainActivity.gameOver();
    }

    public void setPuedoMover(boolean mover) {
        this.puedoMover = mover;
    }

    public boolean getPuedoMover() {
        return this.puedoMover;
    }

    public boolean getFinPartida() {
        return this.finPartida;
    }

    public void setFinPartida() {
        this.finPartida = true;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    public TableroTetris getTetris() {
        return tetris;
    }

    public Pieza getPiezaActual() {
        return this.pieza;
    }

    public Pieza getPiezaSiguiente() {
        return this.tetris.getPiezaSig();
    }

    public Ventana getVentana() {
        return ventana;
    }


    public void setTableroTetris(TableroTetris tableroTetris) {
        this.tetris = tableroTetris;
    }

    public boolean isHebraActiva() {
        return hebraActiva;
    }
}
