package com.example.utils;

public class PesoPieza implements Comparable{
    private double peso;
    private int tipoPieza;

    public PesoPieza(double peso, int tipoPieza) {
        this.peso = peso;
        this.tipoPieza = tipoPieza;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
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


}
