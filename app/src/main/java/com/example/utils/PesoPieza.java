package com.example.utils;

public class PesoPieza implements Comparable{
    private int peso;
    private int tipoPieza;

    public PesoPieza(int peso, int tipoPieza) {
        this.peso = peso;
        this.tipoPieza = tipoPieza;
    }

    public int getPeso() {
        return peso;
    }

    public int getTipoPieza() {
        return tipoPieza;
    }

    public void setTipoPieza(int tipoPieza) {
        this.tipoPieza = tipoPieza;
    }

    @Override
    public int compareTo(Object other) {
        PesoPieza aux = (PesoPieza) other;
        return Double.compare(peso, ((PesoPieza) other).getPeso());
    }

    public void cambiarPeso(double p){
        this.peso+=p;
    }
    public void aumentarPeso(){
        this.peso+=1;
    }


}
