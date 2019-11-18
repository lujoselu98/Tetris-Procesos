package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hebras.Cronometro;
import com.example.hebras.Hebra;
import com.example.utils.Controls;
import com.example.views.NextPieceView;
import com.example.views.Ventana;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    private Ventana ventana;
    private Hebra h;
    private Cronometro cronometro;
    private int puntuacion = 0;
    private String tipoPieza;
    private int nivelVelocidad;
    private Boolean modoSegundaPieza;
    private Boolean modoFantasia;
    private Boolean modoReduccion;
    private Boolean modoLegacy;
    private String nombreJugador;
    private FirebaseFirestore db;
    private Intent intent;
    private MediaPlayer mediaPlayer;
    private int positionMediaPlayer;
    private int cancion = 0;
    private ArrayList canciones = new ArrayList();
    private int[] tiempos = new int[3];

    @SuppressLint("ClickableViewAccessibility")
    @Override

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mediaPlayer = new MediaPlayer();
        canciones.add(R.raw.korobeiniki);
        canciones.add(R.raw.thriller);
        canciones.add(R.raw.livinonaprayer);
        for (int i = 0; i < tiempos.length; i++) {
            tiempos[i] = 0;
        }
        /*try {
            mediaPlayer = MediaPlayer.create(this, R.raw.korobeiniki);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        conexionBaseDatos();
        setContentView(R.layout.activity_juego);

        //Lectura de Datos de la Ventana de Configuración

        intent = new Intent(this, CameraActivity.class);

        Bundle datos = this.getIntent().getExtras();
        assert datos != null;
        tipoPieza = datos.getString("tipoPieza");
        nivelVelocidad = datos.getInt("porcentaje");
        nombreJugador = datos.getString("nombreJugador");

        modoSegundaPieza = datos.getBoolean("modoDificil");
        modoFantasia = datos.getBoolean("modoFantasia");
        modoReduccion = datos.getBoolean("modoReduccion");
        modoLegacy = datos.getBoolean("modoLegacy");
        if(modoLegacy){
            nivelVelocidad+=5;
        }
        TextView textView = findViewById(R.id.Cronometro);
        TextView nombreJug = findViewById(R.id.nombreJug);
        nombreJug.setText("Jugador: " + nombreJugador);
        cronometro = new Cronometro("CuentaAtras", textView);
        Thread c = new Thread(cronometro);
        ventana = new Ventana(this, tipoPieza);
        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(R.id.relativelayout1, R.id.relativelayout1);
        ventana.setLayoutParams(params1);
        RelativeLayout relativeSteinAnzeige = findViewById(R.id.relativelayout1);
        //ventana.setBackgroundColor(Color.YELLOW);
        relativeSteinAnzeige.addView(ventana);

        h = new Hebra(true, this, ventana, nivelVelocidad, cronometro);
        NextPieceView piezaSig = new NextPieceView(this, h.getTetris());
        h.setTableroPiezaSig(piezaSig);

        LinearLayout.LayoutParams parametro = new LinearLayout.LayoutParams(R.id.LinearLayoutLateralPieza, R.id.LinearLayoutLateralPieza);
        piezaSig.setLayoutParams(parametro);
        LinearLayout relativeSteinLinear = findViewById(R.id.LinearLayoutLateralPieza);
        relativeSteinLinear.addView(piezaSig);


        //Iniciamos los controladores del tablero del Tetris
        Controls controls = new Controls(h);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("DISFRUTA DEL TETRIS DEL GRUPO 1")
                .setTitle("INICIAR JUEGO")
                .setCancelable(false)
                .setNeutralButton("JUGAR",
                        (dialog, id) -> {
                            dialog.cancel();
                            h.start();
                           /*if(modoSegundaPieza){
                               hebraModoSegundaPieza.start();
                           }*/
                            c.start();
                        });
        AlertDialog alert = builder.create();
        alert.show();
        //BOTONES

        findViewById(R.id.button_pause).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Button pause = findViewById(R.id.button_pause);
                if (pause.getText().toString().compareTo("Pause") == 0) {
                    h.setPuedoMover(false);
                    cronometro.pause();
                    pause.setText("Resume");
                    positionMediaPlayer = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                } else {
                    h.setPuedoMover(true);
                    cronometro.reanudar();
                    pause.setText("Pause");
                    mediaPlayer.seekTo(positionMediaPlayer);
                    mediaPlayer.start();
                }
            }

            return true;

        });


        findViewById(R.id.button_right).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (modoSegundaPieza && h.getHebraSegundaPieza().getPiezaActual() != null && h.getHebraSegundaPieza().isHebraActiva()) {
                    controls.rightButtonPressed(h.getHebraSegundaPieza().getPiezaActual());
                }
                controls.rightButtonPressed(h.getPiezaActual());
                findViewById(R.id.button_right).setPressed(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //controls.rightButtonReleased();
                findViewById(R.id.button_right).setPressed(false);
            }

            return true;
        });

        findViewById(R.id.button_left).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (modoSegundaPieza && h.getHebraSegundaPieza().getPiezaActual() != null && h.getHebraSegundaPieza().isHebraActiva()) {
                    controls.leftButtonPressed(h.getHebraSegundaPieza().getPiezaActual());
                }
                controls.leftButtonPressed(h.getPiezaActual());
                findViewById(R.id.button_left).setPressed(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //controls.leftButtonReleased();
                findViewById(R.id.button_left).setPressed(false);
            }

            return true;
        });

        findViewById(R.id.button_soft_drop).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                if (modoSegundaPieza && h.getHebraSegundaPieza().getPiezaActual() != null && h.getHebraSegundaPieza().isHebraActiva()) {
                    controls.downButtonPressed(h.getHebraSegundaPieza().getPiezaActual());
                }
                controls.downButtonPressed(h.getPiezaActual());
                (findViewById(R.id.button_soft_drop)).setPressed(true);
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                //controls.downButtonReleased();
                (findViewById(R.id.button_soft_drop)).setPressed(false);
            }

            return true;
        });

        (findViewById(R.id.button_hard_drop)).setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
               /* if (h.getHebraSegundaPieza().getPiezaActual() != null) {
                    controls.dropButtonPressed(h.getHebraSegundaPieza().getPiezaActual());
                }*/
                controls.dropButtonPressed(h.getPiezaActual());
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
                    if (modoSegundaPieza && h.getHebraSegundaPieza().getPiezaActual() != null && h.getHebraSegundaPieza().isHebraActiva()) {
                        controls.rotateRightPressed(h.getHebraSegundaPieza().getPiezaActual());
                    }
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
                    if (modoSegundaPieza && h.getHebraSegundaPieza().getPiezaActual() != null) {
                        controls.rotateLeftPressed(h.getHebraSegundaPieza().getPiezaActual());
                    }
                    controls.rotateLeftPressed(h.getPiezaActual());
                    (findViewById(R.id.button_rotate_left)).setPressed(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    (findViewById(R.id.button_rotate_left)).setPressed(false);
                }

                return true;
            });
        }

        ImageButton buttonCambiarPieza = findViewById(R.id.button_cambiar_pieza);
        if (buttonCambiarPieza != null) {
            (findViewById(R.id.button_cambiar_pieza)).setOnTouchListener((view, event) -> {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    controls.cambiarPieza();

                    (findViewById(R.id.button_cambiar_pieza)).setPressed(true);
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    (findViewById(R.id.button_cambiar_pieza)).setPressed(false);
                }

                return true;
            });
        }
    }

    public boolean getModoReduccion() {
        return this.modoReduccion;
    }

    public void gameOver() {
        Map<String, Object> user = new HashMap<>();
        user.put("nombre", nombreJugador);
        user.put("puntuacion", puntuacion);

        // Add a new document with a generated ID
        db.collection("Jugadores")
                .add(user)
                .addOnSuccessListener(documentReference -> {
                    System.out.println("EXITO");
                    //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                })
                .addOnFailureListener(e -> {
                    System.out.println("FRACASO");
                    //Log.w(TAG, "Error adding document", e);
                });

        //jugadores.add(new ArrayList());
        //
        db.collection("Jugadores").orderBy("puntuacion", Query.Direction.DESCENDING).limit(10)
                .get()
                .addOnCompleteListener(task -> {
                    int i = 0;
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
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
                        intent.putExtra("puntosJugador",puntuacion);
                        comenzarActividad();

                    }  //Log.w(TAG, "Error getting documents.", task.getException());

                });
    }

    private void comenzarActividad() {
        this.startActivity(intent);
        finish();
    }

    public boolean getModoSegundaPieza() {
        return this.modoSegundaPieza;
    }

    public String getTipoPieza() {
        return tipoPieza;
    }

    private void conexionBaseDatos() {
        db = FirebaseFirestore.getInstance();
    }

    public void sumar_puntuacion(int ptos) {
        puntuacion += ptos;
        TextView texto = findViewById(R.id.puntuacion);
        texto.setText("Ptos:" + puntuacion);
    }

    public Hebra getH() {
        return h;
    }

    public boolean getModoFantasia() {
        return modoFantasia;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void cambiarCancion() throws IOException {
        if (cancion == 0) {
            tiempos[2] = mediaPlayer.getCurrentPosition();
        } else {
            tiempos[cancion - 1] = mediaPlayer.getCurrentPosition();
        }
        mediaPlayer.reset();
        mediaPlayer.setDataSource(this, Uri.parse("android.resource://com.tetris/" + canciones.get(cancion)));
        mediaPlayer.prepare();
        mediaPlayer.seekTo(tiempos[cancion]);
        mediaPlayer.start();
        cancion++;
        cancion = cancion % 3;
    }

    public boolean getModoLegacy() {
        return this.modoLegacy;
    }
}
