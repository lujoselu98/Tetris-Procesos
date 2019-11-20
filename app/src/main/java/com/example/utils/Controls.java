package com.example.utils;

import com.example.hebras.Hebra;
import com.example.pieces.Pieza;

/* PARA METER LOS MOVIMIENTOS */
public class Controls {
    private final Hebra hebra;
    public Controls(Hebra h) {
        this.hebra = h;
    }

    public void leftButtonPressed(Pieza pieza) {
        hebra.getTetris().despIzqda(pieza);
        hebra.getTetris().actualizarSombra();
        hebra.getVentana().invalidate();
    }

    public void rightButtonPressed(Pieza pieza) {
        hebra.getTetris().despDcha(pieza);
        hebra.getTetris().actualizarSombra();
        hebra.getVentana().invalidate();
    }

    public void downButtonPressed(Pieza pieza) {
        hebra.getTetris().bajar(pieza);
        hebra.getTetris().actualizarSombra();
        hebra.getVentana().invalidate();
    }

    public void dropButtonPressed(Pieza pieza) {
        hebra.getTetris().hard_Drop(pieza);
        hebra.getVentana().invalidate();

    }

    public void rotateRightPressed(Pieza pieza) {
        hebra.getTetris().rotarDcha(pieza);
        hebra.getTetris().actualizarSombra();
        hebra.getVentana().invalidate();
    }

    public void rotateLeftPressed(Pieza pieza) {

        hebra.getTetris().rotarIzqda(pieza);
        hebra.getTetris().actualizarSombra();
        hebra.getVentana().invalidate();
    }

    public void cambiarPieza(){

        hebra.getTetris().cambiarPieza();
    }
}
