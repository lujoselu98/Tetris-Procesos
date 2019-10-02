package com.example.activities;

import com.example.pieces.Pieza;

/* PARA METER LOS MOVIMIENTOS */
class Controls {
    private Hebra hebra;
    public Controls(Hebra h) {
        this.hebra = h;
    }

    public void leftButtonPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO IZQUIERDA");
        pieza.despIzqda();
        hebra.getV().invalidate();
    }

    public void leftButtonReleased() {
    }

    public void rightButtonReleased() {
    }

    public void rightButtonPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO DCHA");
        pieza.despDcha();
        hebra.getV().invalidate();

        //hebra.run();
    }

    public void downButtonPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO ABAJO");
        pieza.bajar();
        hebra.getV().invalidate();
    }

    public void downButtonReleased() {
    }

    public void dropButtonPressed() {
        System.out.println("MOVIMIENTO ABAJO RAPIDO");
    }

    public void rotateRightPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO GIRAR");
        pieza.rotarDcha();
        hebra.getV().invalidate();
    }

    public void rotateLeftPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO GIRAR");
        pieza.rotarIzqda();
        hebra.getV().invalidate();
    }
}
