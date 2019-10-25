package com.example.hebras;

import android.os.Handler;
import android.util.Log;
import android.widget.TextView;


public class Cronometro implements Runnable {
    // Atributos privados de la clase
    private final TextView etiq; // Etiqueta para mostrar la información
    private final String nombrecronometro; // Nombre del cronómetro
    private int segundos; // Segundos, minutos y horas que lleva activo el cronómetro
    private final Handler escribirenUI; // Necesario para modificar la UI
    private Boolean pausado; // Para pausar el cronómetro
    private String salida; // Salida formateada de los datos del cronómetro
    private boolean finPartida;

    /**
     * Constructor de la clase
     *
     * @param nombre   Nombre del cronómetro
     * @param etiqueta Etiqueta para mostrar información
     */
    public Cronometro(String nombre, TextView etiqueta) {
        etiq = etiqueta;
        salida = "";
        segundos = 0;
        nombrecronometro = nombre;
        escribirenUI = new Handler();
        pausado = Boolean.FALSE;
        finPartida = Boolean.FALSE;
    }

    @Override
    /*
     * Acción del cronómetro, contar tiempo en segundo plano
     */
    public void run() {
        try {
            while (!finPartida) {
                Thread.sleep(1000);
                salida = "";
                if (!pausado) {
                    segundos++;
                    salida += segundos;
                    // Modifico la UI
                    try {
                        escribirenUI.post(() -> etiq.setText("Tiempo: " + salida));
                    } catch (Exception e) {
                        Log.i("Cronometro", "Error en el cronometro " + nombrecronometro + " al escribir en la UI: " + e.toString());
                    }
                }
            }
        } catch (InterruptedException e) {
            Log.i("Cronometro", "Error en el cronometro " + nombrecronometro + ": " + e.toString());
        }
    }

    public void pause() {
        pausado = !pausado;
    }

    public void reanudar() {
        pausado = !pausado;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setFinPartida() {
        this.finPartida = true;
    }
}