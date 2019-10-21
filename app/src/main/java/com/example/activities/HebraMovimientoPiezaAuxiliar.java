package com.example.activities;



import com.example.pieces.Pieza;

/* IGUAL QUE EL WORKTHREAD */
public class HebraMovimientoPiezaAuxiliar extends Thread{
    private boolean puedoMover;
    private boolean finPartida;
    private int velocidadCaida = 1000;
    private NextPieceView tableroPiezaSig;
    private MainActivity mainActivity;
    private Ventana ventana;
    TableroTetris tetris;


    public HebraMovimientoPiezaAuxiliar(boolean puedoMover, MainActivity mainActivity, Ventana v, int velocidad) {
        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.ventana = v;
        if(velocidad != 0){
            this.velocidadCaida = velocidadCaida / velocidad;
        }

    }

    public TableroTetris getTableroTetris(){
        return tetris;
    }

    @Override
    public void run() {
        while (!finPartida) {
            while (puedoMover) {
                ventana.setPieza(tetris.getPiezaActual());
                tetris.bajar();
                System.out.println("ESTOY DENTRO DEL WHILE DE LA HEBRA");
                System.out.println("LLAMO A INVALIDATE");
                ventana.invalidate();
                tableroPiezaSig.invalidate();
                System.out.println("INVALIDATE SUPERADO");
                if (tetris.comprobarPerdido()) {
                    System.out.println("PERDIDO");
                    setFinPartida();
                    puedoMover = false;
                }
                try {
                    System.out.println("VOY A DORMIR");
                    Thread.sleep(velocidadCaida);

                } catch (InterruptedException ignored) {

                }

            }
        }
        mainActivity.gameOver();
    }


    public void setPuedoMover(boolean mover){
        this.puedoMover = mover;
    }

    public boolean getPuedoMover(){
        return this.puedoMover;
    }

    public boolean getFinPartida(){
        return this.finPartida;
    }

    public void setFinPartida(){
        this.finPartida = true;
    }

    public MainActivity getMainActivity(){
        return mainActivity;
    }

    public TableroTetris getTetris(){
        return tetris;
    }
    public Pieza getPiezaActual(){
        return this.tetris.getPiezaActual();
    }
    public Pieza getPiezaSiguiente(){
        return this.tetris.getPiezaSig();
    }
    public Ventana getVentana(){
        return ventana;
    }

    public void setTableroPiezaSig(NextPieceView piezaSig) {
        this.tableroPiezaSig = piezaSig;
    }
    public NextPieceView getTableroPiezaSig() {
        return this.tableroPiezaSig;
    }

    public void setTableroTetris(TableroTetris tableroTetris) {
        this.tetris = tableroTetris;
    }
}
