package com.example.activities;

import com.example.pieces.Pieza;

/* PARA METER LOS MOVIMIENTOS */
class Controls {
    private Hebra hebra;
    public Controls(Hebra h) {
        this.hebra = h;
    }

    public void leftButtonPressed(Pieza pieza) {
        hebra.tetris.despIzqda(pieza);
        hebra.getVentana().invalidate();
    }

    public void leftButtonReleased() {
    }

    public void rightButtonReleased() {
    }

    public void rightButtonPressed(Pieza pieza) {


        hebra.tetris.despDcha(pieza);
        hebra.getVentana().invalidate();

        //hebra.run();
    }

    public void downButtonPressed(Pieza pieza) {

        hebra.tetris.bajar(pieza);
        hebra.getVentana().invalidate();
    }

    public void downButtonReleased() {
    }

    public void dropButtonPressed(Pieza pieza) {
        hebra.tetris.hard_Drop(pieza);
        hebra.getVentana().invalidate();

    }

    public void rotateRightPressed(Pieza pieza) {

        hebra.tetris.rotarDcha(pieza);
        hebra.getVentana().invalidate();
    }

    public void rotateLeftPressed(Pieza pieza) {

        hebra.tetris.rotarIzqda(pieza);
        hebra.getVentana().invalidate();
    }
}
