package com.example.activities;

/* PARA METER LOS MOVIMIENTOS */
class Controls {
    private Hebra hebra;
    public Controls(Hebra h) {
        this.hebra = h;
    }

    public void leftButtonPressed() {
        System.out.println("MOVIMIENTO IZQUIERDA");
    }

    public void leftButtonReleased() {
    }

    public void rightButtonReleased() {
    }

    public void rightButtonPressed() {
        System.out.println("MOVIMIENTO DCHA");
        //hebra.run();
    }

    public void downButtonPressed() {
        System.out.println("MOVIMIENTO ABAJO");
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
