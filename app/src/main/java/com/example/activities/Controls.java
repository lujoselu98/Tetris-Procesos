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
    }

    public void leftButtonReleased() {
    }

    public void rightButtonReleased() {
    }

    public void rightButtonPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO DCHA");
        pieza.despDcha();

        //hebra.run();
    }

    public void downButtonPressed(Pieza pieza) {
        System.out.println("MOVIMIENTO ABAJO");
        pieza.bajar();
    }

    public void downButtonReleased() {
    }

    public void dropButtonPressed() {
        System.out.println("MOVIMIENTO ABAJO RAPIDO");
    }

    public void rotateRightPressed() {
        System.out.println("MOVIMIENTO GIRAR");
    }

    public void rotateLeftPressed() {
        System.out.println("MOVIMIENTO GIRAR");
    }
}
