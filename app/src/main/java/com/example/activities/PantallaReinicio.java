package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PantallaReinicio extends AppCompatActivity  {
    private String[] arrayNombres;
    private int[] arrayPuntuaciones;
    private ArrayList<ArrayList> jugadores;
    private ArrayList<String> listado;
    private FirebaseFirestore db;
    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_reinicio);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        arrayNombres = new String[10];
        arrayPuntuaciones = new int[10];
        jugadores = new ArrayList<>();
        Bundle datos = this.getIntent().getExtras();
        assert datos != null;
//        int longArray = datos.getInt("longArray");
        listado = new ArrayList<>();
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listado);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adaptador);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        db = FirebaseFirestore.getInstance();
        //jugadores.add(new ArrayList());
//
/*db.collection("Rating").orderBy("puntuacion", Query.Direction.DESCENDING).limit(10)
        .get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int i = 0;
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        //Log.d(TAG, document.getId() + " => " + document.getData());
                        System.out.println(document.getId() + " => " + document.getData());
                        System.out.println(document.getData().get("nombre"));
                        //jugadores.add(new ArrayList());
                        String name = "nombre" + i;
                        System.out.println(name);
                        System.out.println(document.getData().get("nombre"));
                        intent.putExtra(name, (String) document.getData().get("nombre"));
                        intent.putExtra("puntuacion" + i, (long) document.getData().get("puntuacion"));
                        i++;
                    }
                    intent.putExtra("longArray", i);
                    comenzarActividad();

                } else {
                    //Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });
}*/
        ratingBar.setOnRatingBarChangeListener((ratingBar1, v, b) -> {
            Map<String, Object> user = new HashMap<>();
            user.put("valoracion", ratingBar1.getRating());
            db.collection("Rating")
                    .add(user)
                    .addOnSuccessListener(documentReference -> {
                        System.out.println("EXITO");
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    })
                    .addOnFailureListener(e -> {
                        System.out.println("FRACASO");
                        //Log.w(TAG, "Error adding document", e);
                    });
        });
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
}
