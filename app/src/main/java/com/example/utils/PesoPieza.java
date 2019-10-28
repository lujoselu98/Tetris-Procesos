package com.example.utils;

public class PesoPieza {
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

    public int compare(PesoPieza p1) {
        return Double.compare(peso, p1.getPeso());
    }
}
