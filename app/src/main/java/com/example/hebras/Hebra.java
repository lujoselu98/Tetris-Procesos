package com.example.hebras;

import com.example.activities.MainActivity;
import com.example.views.NextPieceView;
import com.example.activities.TableroTetris;
import com.example.views.Ventana;
import com.example.pieces.Pieza;

import java.io.IOException;

/* IGUAL QUE EL WORKTHREAD */
public class Hebra extends Thread {
    private boolean puedoMover;
    private boolean finPartida;
    private int velocidadCaida = 1000;
    private NextPieceView tableroPiezaSig;
    private final MainActivity mainActivity;
    private final Ventana ventana;
    private final TableroTetris tetris;
    private final Cronometro cronometro;
    private HebraMovimientoPiezaAuxiliar hebraSegundaPieza;

    private int segAntSegundaPieza = 0;

    private int segAnt = 0;

    private int segMusica = 0;


    public Hebra(boolean puedoMover, MainActivity mainActivity, Ventana v, int velocidad, Cronometro cronometro) {

        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.ventana = v;
        if (velocidad != 0) {
            this.velocidadCaida = velocidadCaida / velocidad;
        }
        tetris = new TableroTetris(this.mainActivity, this.ventana, this.mainActivity.getModoFantasia(),this.mainActivity.getModoLegacy());
        v.setPieza(tetris.getPiezaActual());
        v.setSombra(tetris.getSombra());
        v.setTablero(tetris);
        this.cronometro = cronometro;
        if (this.mainActivity.getModoSegundaPieza()) {
            hebraSegundaPieza = new HebraMovimientoPiezaAuxiliar(false, this.mainActivity, this.ventana, 10, false);
            hebraSegundaPieza.setTableroTetris(this.tetris);
            hebraSegundaPieza.start();
        }
    }

    public HebraMovimientoPiezaAuxiliar getHebraSegundaPieza() {
        return this.hebraSegundaPieza;
    }

    @Override
    public void run() {
        //mainActivity.getMediaPlayer().start();
        //mainActivity.getMediaPlayer().setLooping(true);
        try {
            mainActivity.cambiarCancion();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (!finPartida) {
            while (puedoMover) {
                if (this.mainActivity.getModoSegundaPieza() && cronometro.getSegundos() % 30 == 0 && cronometro.getSegundos() != 0 && segAntSegundaPieza != cronometro.getSegundos()) {
                    System.out.println("VOY A ACTIVAR LA SEGUNDA PIEZAAAA");
                    hebraSegundaPieza.setHebraActiva(true);
                    segAntSegundaPieza = cronometro.getSegundos();
                    hebraSegundaPieza.setPuedoMover(true);
                }
                tetris.bajar(tetris.getPiezaActual());

                System.out.println("LLAMO A INVALIDATE");
                ventana.invalidate();
                tableroPiezaSig.invalidate();

                if (tetris.comprobarPerdido()) {
                    System.out.println("PERDIDO");
                    this.finPartida = true;
                    cronometro.setFinPartida();
                    puedoMover = false;
                    this.mainActivity.getMediaPlayer().pause();
                }
                try {

                    Thread.sleep(velocidadCaida);

                } catch (InterruptedException ignored) {

                }


                if (cronometro.getSegundos() % 50 == 0 && cronometro.getSegundos() != 0 && segAnt != cronometro.getSegundos() && this.mainActivity.getModoReduccion()) {

                    segAnt = cronometro.getSegundos();
                    tetris.eliminarFilas();
                    ventana.setRows(tetris.getFilas());
                    ventana.setTablero(tetris);

                }

                if (cronometro.getSegundos() % 20 == 0 && cronometro.getSegundos() != 0 && segMusica != cronometro.getSegundos()) {
                    segMusica = cronometro.getSegundos();
                    try {
                        mainActivity.cambiarCancion();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        mainActivity.gameOver();
    }


    public void setPuedoMover(boolean mover) {
        this.puedoMover = mover;
        if (mainActivity.getModoSegundaPieza()) {
            hebraSegundaPieza.setPuedoMover(mover);
        }
    }

    public boolean getPuedoMover() {
        return this.puedoMover;
    }

    public TableroTetris getTetris() {
        return tetris;
    }

    public Pieza getPiezaActual() {
        return this.tetris.getPiezaActual();
    }

    public Ventana getVentana() {
        return ventana;
    }

    public void setTableroPiezaSig(NextPieceView piezaSig) {
        this.tableroPiezaSig = piezaSig;
    }

}
