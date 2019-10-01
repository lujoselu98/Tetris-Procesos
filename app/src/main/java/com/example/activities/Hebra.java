package com.example.activities;


/* IGUAL QUE EL WORKTHREAD */
public class Hebra extends Thread{
    private boolean puedoMover;
    MainActivity mainActivity;
    //Modelo modelo;

    public Hebra(boolean puedoMover, MainActivity mainActivity) {
        this.puedoMover = puedoMover;
        this.mainActivity = mainActivity;
    }
    @Override
    public void run() {
        System.out.println("SOY LA HEBRA Y ESPEROOOO");
       //mainActivity.mostrarCanvas();   //Mostramos el canvas

        while (puedoMover) {
           System.out.println("ESTOY DENTRO DEL WHILE DE LA HEBRA");
           mover();
            try {
                System.out.println("VOY A DORMIR");
                Thread.sleep(5000);

            } catch (InterruptedException ignored) {

            }

        }


    }
    public void setPuedoMover(boolean mover){
        this.puedoMover = mover;
    }
    private void metodoHebra() {

    }
    public void mover() {
        System.out.println("ES EL MODELO");
        int[] rows_L = new int[]{0, 1, 2, 3};
        int[] cols_L = new int[]{0, 0, 0, 1};
        //canvas.drawPiece(4,rows_L,cols_L,getAndroidCanvas());
        //MOVER PIEZA
    }

}
