package com.example.activities;

public class HebraModoSegundaPieza extends Thread{
    private Hebra hebraPrincipal;

    public HebraModoSegundaPieza(Hebra h){
        this.hebraPrincipal = h;
    }

    @Override
    public void run() {
        while (!hebraPrincipal.getFinPartida()) {
            while (hebraPrincipal.getPuedoMover()) {
                hebraPrincipal.getVentana().setPieza(hebraPrincipal.getTableroTetris().getPiezaActual());
                hebraPrincipal.getTableroTetris().bajar();
                System.out.println("ESTOY DENTRO DEL WHILE DE LA HEBRA");
                System.out.println("LLAMO A INVALIDATE");
                hebraPrincipal.getVentana().invalidate();
                hebraPrincipal.getTableroPiezaSig().invalidate();
                System.out.println("INVALIDATE SUPERADO");
               /* if (hebraPrincipal.getTableroTetris().comprobarPerdido()) {
                    System.out.println("PERDIDO");
                    setFinPartida();
                    puedoMover = false;
                }*/
                try {
                    System.out.println("VOY A DORMIR HEBRA PIEZA 30 SEGUNDOS");
                    Thread.sleep(30000);

                } catch (InterruptedException ignored) {

                }

            }
        }
    }
}
