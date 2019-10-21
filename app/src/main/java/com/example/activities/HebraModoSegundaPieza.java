package com.example.activities;

public class HebraModoSegundaPieza extends Thread{
    private Hebra hebraPrincipal;
    private HebraMovimientoPiezaAuxiliar hebra;
    public HebraModoSegundaPieza(Hebra h){
        this.hebraPrincipal = h;
    }

    @Override
    public void run() {
        while (!hebraPrincipal.getFinPartida()) {
            try {
                Thread.sleep(30000);
                hebra = new HebraMovimientoPiezaAuxiliar(true,hebraPrincipal.getMainActivity(),hebraPrincipal.getVentana(),300);
                hebra.setTableroPiezaSig(hebraPrincipal.getTableroPiezaSig());
                hebra.setTableroTetris(hebraPrincipal.getTableroTetris());
                hebra.start();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (hebraPrincipal.getPuedoMover()) {
                //hebraPrincipal.getVentana().setPieza(hebraPrincipal.getTableroTetris().getPiezaActual());
                //hebra.getTableroTetris().bajar();
                hebra.getVentana().setPieza(hebraPrincipal.getTableroTetris().getPiezaSig());
                System.out.println("ESTOY DENTRO DEL WHILE DE LA HEBRA");
                System.out.println("LLAMO A INVALIDATE");
                hebra.getVentana().invalidate();
                hebra.getTableroPiezaSig().invalidate();
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
