package com.example.activities;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pieces.Pieza;
import com.example.pieces.PiezaC;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;
import com.example.pieces.PiezaLI;
import com.example.pieces.PiezaT;
import com.example.pieces.PiezaZ;
import com.example.pieces.PiezaZI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends AppCompatActivity {
    Ventana v;
    Hebra h;
    Cronometro cronometro;
    int ivID;
    int puntuacion = 0;
    String tipoPieza;
    int nivelVelocidad;
    Boolean modoSegundaPieza;
    Boolean modoFantasia;
    Boolean modoReduccion;
    String nombreJugador;
    HebraModoSegundaPieza hebraModoSegundaPieza;
    FirebaseFirestore db;
    String[] arrayNombres;
    int[] arrayPuntuaciones;
    ArrayList<ArrayList> jugadores;
    int longArray;
    ArrayList<ArrayList> arrayReal = new ArrayList<>();
    Intent intent;
    @SuppressLint("ClickableViewAccessibility")
   @Override

    protected void onCreate(final Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);

       conexionBaseDatos();
       setContentView(R.layout.activity_juego);

       //Lectura de Datos de la Ventana de Configuración

       jugadores = new ArrayList<>();
        intent = new Intent(this,PantallaReinicio.class);

        Bundle datos = this.getIntent().getExtras();
       assert datos != null;
       tipoPieza = datos.getString("tipoPieza");
       nivelVelocidad = datos.getInt("porcentaje");
       nombreJugador = datos.getString("nombreJugador");

       modoSegundaPieza = datos.getBoolean("modoDificil");
       modoFantasia = datos.getBoolean("modoFantasia");
       modoReduccion = datos.getBoolean("modoDificil");

       TextView textView = (TextView) findViewById(R.id.Cronometro);
       TextView nombreJug = findViewById(R.id.nombreJug);
       if(nombreJugador.compareTo("INTRODUCE TU NOMBRE:")==1){
           nombreJugador.replace("INTRODUCE TU NOMBRE:","ANONIMO");
       }
       nombreJug.setText("Jugador: "+nombreJugador);
       cronometro = new Cronometro("CuentaAtras", textView);
       Thread c = new Thread(cronometro);
       v = new Ventana(this);
       RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(R.id.relativelayout1, R.id.relativelayout1);
       v.setLayoutParams(params1);
       RelativeLayout relativeSteinAnzeige = (RelativeLayout) findViewById(R.id.relativelayout1);
       //v.setBackgroundColor(Color.YELLOW);
       relativeSteinAnzeige.addView(v);

       h = new Hebra(true, this, v,nivelVelocidad);
       NextPieceView piezaSig = new NextPieceView(this, h.getTetris());
       h.setTableroPiezaSig(piezaSig);

       LinearLayout.LayoutParams parametro = new LinearLayout.LayoutParams(R.id.LinearLayoutLateralPieza, R.id.LinearLayoutLateralPieza);
       piezaSig.setLayoutParams(parametro);
       LinearLayout relativeSteinLinear = findViewById(R.id.LinearLayoutLateralPieza);
       relativeSteinLinear.addView(piezaSig);

       //Activación o no de Modos distintos:
       if(modoSegundaPieza){
           hebraModoSegundaPieza = new HebraModoSegundaPieza(h);
       }

       //Iniciamos los controladores del tablero del Tetris
       Controls controls = new Controls(h);
       AlertDialog.Builder builder = new AlertDialog.Builder(this);
       builder.setMessage("DISFRUTA DEL TETRIS DEL GRUPO 1")
               .setTitle("INICIAR JUEGO")
               .setCancelable(false)
               .setNeutralButton("JUGAR",
                       new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int id) {
                               dialog.cancel();
                               h.start();
                               if(modoSegundaPieza){
                                   hebraModoSegundaPieza.start();
                               }
                               c.start();
                           }
                       });
       AlertDialog alert = builder.create();
       alert.show();
       //BOTONES

       findViewById(R.id.button_pause).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_UP) {
               Button pause = (Button) findViewById(R.id.button_pause);
               if (pause.getText().toString().compareTo("Pause") == 0) {
                   h.setPuedoMover(false);
                   cronometro.pause();
                   pause.setText("Resume");
               } else {
                   h.setPuedoMover(true);
                   cronometro.reanudar();
                   pause.setText("Pause");
               }
           }

           return true;

       });


       findViewById(R.id.button_right).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               System.out.println("BOTON DERECHO");
               controls.rightButtonPressed(h.getPiezaActual());
               findViewById(R.id.button_right).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.rightButtonReleased();
               findViewById(R.id.button_right).setPressed(false);
           }

           return true;
       });

       findViewById(R.id.button_left).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.leftButtonPressed(h.getPiezaActual());
               findViewById(R.id.button_left).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.leftButtonReleased();
               findViewById(R.id.button_left).setPressed(false);
           }

           return true;
       });

       findViewById(R.id.button_soft_drop).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.downButtonPressed(h.getPiezaActual());
               (findViewById(R.id.button_soft_drop)).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               controls.downButtonReleased();
               (findViewById(R.id.button_soft_drop)).setPressed(false);
           }

           return true;
       });

       (findViewById(R.id.button_hard_drop)).setOnTouchListener((view, event) -> {
           if (event.getAction() == MotionEvent.ACTION_DOWN) {
               controls.dropButtonPressed();
               (findViewById(R.id.button_hard_drop)).setPressed(true);
           } else if (event.getAction() == MotionEvent.ACTION_UP) {
               (findViewById(R.id.button_hard_drop)).setPressed(false);
           }

           return true;
       });

       ImageButton buttonRotateRight = findViewById(R.id.button_rotate_right);
       if (buttonRotateRight != null) {
           (findViewById(R.id.button_rotate_right)).setOnTouchListener((view, event) -> {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   controls.rotateRightPressed(h.getPiezaActual());
                   (findViewById(R.id.button_rotate_right)).setPressed(true);
               } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   (findViewById(R.id.button_rotate_right)).setPressed(false);
               }

               return true;
           });
       }

       ImageButton buttonRotateLeft = findViewById(R.id.button_rotate_left);
       if (buttonRotateLeft != null) {
           (findViewById(R.id.button_rotate_left)).setOnTouchListener((view, event) -> {
               if (event.getAction() == MotionEvent.ACTION_DOWN) {
                   controls.rotateLeftPressed(h.getPiezaActual());
                   (findViewById(R.id.button_rotate_left)).setPressed(true);
               } else if (event.getAction() == MotionEvent.ACTION_UP) {
                   (findViewById(R.id.button_rotate_left)).setPressed(false);
               }

               return true;
           });
       }
   }


    public void gameOver(){
        Map<String, Object> user = new HashMap<>();
        user.put("nombre",nombreJugador);
        user.put("puntuacion",puntuacion);

        // Add a new document with a generated ID
        db.collection("Jugadores")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        System.out.println("EXITO");
                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println("FRACASO");
                        //Log.w(TAG, "Error adding document", e);
                    }
                });

        //jugadores.add(new ArrayList());
        //
        db.collection("Jugadores").orderBy("puntuacion", Query.Direction.DESCENDING).limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        int i = 0;
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                //Log.d(TAG, document.getId() + " => " + document.getData());
                                System.out.println(document.getId()+" => "+ document.getData());
                                System.out.println(document.getData().get("nombre"));
                                //jugadores.add(new ArrayList());
                                String name = "nombre"+i;
                                System.out.println(name);
                                System.out.println(document.getData().get("nombre"));
                                intent.putExtra(name,(String) document.getData().get("nombre"));
                                intent.putExtra("puntuacion"+i,(long) document.getData().get("puntuacion"));
                                i++;
                            }
                            intent.putExtra("longArray",i);
                            comenzarActividad();

                        } else {
                            //Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
    private void comenzarActividad(){
        this.startActivity(intent);
        finish();
    }


    public String getTipoPieza(){
        return tipoPieza;
    }
    public void conexionBaseDatos(){
        db = FirebaseFirestore.getInstance();
    }
    public void sumar_puntuacion(int ptos) {
       puntuacion+=ptos;
       TextView texto = (TextView) findViewById(R.id.puntuacion);
       texto.setText("Ptos:"+puntuacion);
    }



}
