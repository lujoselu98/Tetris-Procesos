package com.example.activities;

import com.example.pieces.Pieza;

/* IGUAL QUE EL WORKTHREAD */
public class Hebra extends Thread{
    private boolean puedoMover;
    private boolean finPartida;
    private int velocidadCaida = 1000;
    private NextPieceView tableroPiezaSig;
    MainActivity mainActivity;
    private Ventana ventana;
    TableroTetris tetris;
    private Cronometro cronometro;
    private HebraMovimientoPiezaAuxiliar hebraSegundaPieza;

    private int segAntSegundaPieza = 0;

    public int segAnt=0;


    public Hebra(boolean puedoMover, MainActivity mainActivity, Ventana v,int velocidad, Cronometro cronometro) {

        this.puedoMover = puedoMover;
        this.finPartida = false;
        this.mainActivity = mainActivity;
        this.ventana = v;
        if(velocidad != 0){
            this.velocidadCaida = velocidadCaida / velocidad;
        }
        tetris = new TableroTetris(this.mainActivity,this.ventana);
        v.setPieza(tetris.getPiezaActual());
        v.setTablero(tetris);
        this.cronometro = cronometro;
        if(this.mainActivity.getModoSegundaPieza()){
            hebraSegundaPieza = new HebraMovimientoPiezaAuxiliar(false, this.mainActivity, this.ventana, 10,false);
            hebraSegundaPieza.setTableroPiezaSig(this.getTableroPiezaSig());
            hebraSegundaPieza.setTableroTetris(this.getTableroTetris());
            hebraSegundaPieza.start();
        }
    }

    public TableroTetris getTableroTetris(){
        return tetris;
    }
    public HebraMovimientoPiezaAuxiliar getHebraSegundaPieza(){
        return this.hebraSegundaPieza;
    }

    @Override
    public void run() {
        while (!finPartida) {
            while (puedoMover) {
                if(this.mainActivity.getModoSegundaPieza()) {
                    if (cronometro.getSegundos() % 30 == 0 && cronometro.getSegundos() != 0 && segAntSegundaPieza != cronometro.getSegundos()) {
                        System.out.println("VOY A ACTIVAR LA SEGUNDA PIEZAAAA");
                        hebraSegundaPieza.setHebraActiva(true);
                        segAntSegundaPieza = cronometro.getSegundos();
                        hebraSegundaPieza.setPuedoMover(true);
                    }
                }
                ventana.setPieza(tetris.getPiezaActual());
                tetris.bajar(tetris.getPiezaActual());
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


                if (cronometro.getSegundos()%20==0 && cronometro.getSegundos()!=0 && segAnt!=cronometro.getSegundos() && this.mainActivity.getModoReduccion()){
                    segAnt= cronometro.getSegundos();
                    tetris.eliminarFilas();
                    ventana.setRows(tetris.getFILAS());
                    ventana.setTablero(tetris);

                }
            }
        }
        mainActivity.gameOver();
    }


    public void setPuedoMover(boolean mover){
        this.puedoMover = mover;
        hebraSegundaPieza.setPuedoMover(mover);
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

}
