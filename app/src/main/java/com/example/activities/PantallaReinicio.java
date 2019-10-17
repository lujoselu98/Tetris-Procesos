package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class PantallaReinicio extends AppCompatActivity  {
    String[] arrayNombres;
    int[] arrayPuntuaciones;
    ArrayList<ArrayList> jugadores;
    ArrayList<String> listado;
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reinicio);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        arrayNombres = new String[10];
        arrayPuntuaciones = new int[10];
        cogerMejoresJugadores();
        jugadores = new ArrayList<>();
        Bundle datos = this.getIntent().getExtras();
        assert datos != null;
//        int longArray = datos.getInt("longArray");
        listado = new ArrayList<String>();
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listado);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adaptador);
        for (int i = 0; i < 10; i++) {
           // arrayNombres[i] = datos.getString("nombre"+i);
            //arrayPuntuaciones[i] = datos.getInt("puntuacion"+i);

            //jugadores.add(new ArrayList());
            String name = "nombre"+i;
            System.out.println(name);
            System.out.println("NOMBRE: "+datos.getString(name));
            listado.add(datos.getString("nombre"+i)+" "+datos.getLong("puntuacion"+i));

            //jugadores.get(i).add(datos.getString("nombre"+i));
            //jugadores.get(i).add(datos.getInt("puntuacion"+i));
        }
        System.out.println("VOY A IMPRIMIR EL LISTADO");
        System.out.println(listado.toString());
        adaptador.notifyDataSetChanged();
        //System.out.println(arrayNombres.toString());
        //System.out.println(arrayPuntuaciones.toString());
        //System.out.println(jugadores.toString());

        /*TextView puntos = (TextView) findViewById(R.id.historialPuntuaciones);
        puntos.setText(jugadores.toString());*/
       /* for (int i = 0; i < arrayNombres.length; i++) {
            System.out.println(arrayNombres[i]);
            if(arrayNombres[i]==null){
                puntos.setText("ANONIMO");
            }else
                puntos.setText(arrayNombres[i]);
        }*/
        //puntos.setText(arrayNombres.toString()+" "+arrayPuntuaciones.toString());


        Button buttonReinicio = findViewById(R.id.activity_main_button_restart);
        if (buttonReinicio != null) {
            (findViewById(R.id.activity_main_button_restart)).setOnTouchListener((view, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent intent = new Intent(this,Settings.class);
                    this.startActivity(intent);
                    (findViewById(R.id.activity_main_button_restart)).setPressed(true);
                    finish();
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    (findViewById(R.id.activity_main_button_restart)).setPressed(false);
                }

                return true;
            });
        }

    }

    public void cogerMejoresJugadores(){

    }
}
