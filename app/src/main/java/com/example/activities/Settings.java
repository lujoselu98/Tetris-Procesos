package com.example.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity implements AdapterView.OnItemSelectedListener {
    //--Declaramos la variable para nuestro control
    private SeekBar seekBar1;
    private TextView porcentajeDificultad;
    private String tipoPieza;
    private int progreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Velocidad

        porcentajeDificultad = findViewById(R.id.textoNivelDificultad);
        seekBar1 = findViewById(R.id.seekBarVelocidad);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentajeDificultad.setText("" + progress);
                progreso = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //No tiene que hacer nada
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //No tiene que hacer nada
            }
        });

        //MODO DIFICIL (PIEZA CADA 30 SEGUNDOS)

        Switch switchModoSegundaPieza = findViewById(R.id.casillaModoSegundaPieza);
        Switch switchModoFantasia = findViewById(R.id.casillaModoFantasia);
        Switch switchModoReduccion = findViewById(R.id.casillaModoReduccion);
        Switch switchModoLegacy = findViewById(R.id.casillaModoLegacy);

        //Tipo Piezas
        Spinner tipoPiezas = findViewById(R.id.seleccion_tipo_piezas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.tipo_piezas, R.layout.spinner_levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoPiezas.setAdapter(adapter);
        tipoPiezas.setOnItemSelectedListener(this);

        //Nombre Usuario
        EditText nombreUsuario = findViewById(R.id.nombreJugador);
        //BOTON PARA INICIAR PARTIDA

            switchModoLegacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(switchModoFantasia.isChecked()){
                        switchModoFantasia.toggle();
                    }
                    if(switchModoReduccion.isChecked()){
                        switchModoReduccion.toggle();
                    }
                    if(switchModoSegundaPieza.isChecked()) {
                        switchModoSegundaPieza.toggle();
                    }
                }
            });

        Button botonInicioPartida = findViewById(R.id.botonInicioPartida);
        botonInicioPartida.setOnClickListener(v -> {
            Intent intent = new Intent(Settings.this, MainActivity.class);
            intent.putExtra("tipoPieza", tipoPieza);
            intent.putExtra("porcentaje", progreso);
            intent.putExtra("nombreJugador", nombreUsuario.getText().toString());

            intent.putExtra("modoDificil", switchModoSegundaPieza.isChecked());
            intent.putExtra("modoFantasia", switchModoFantasia.isChecked());
            intent.putExtra("modoReduccion", switchModoReduccion.isChecked());
            intent.putExtra("modoLegacy", switchModoLegacy.isChecked());
            startActivity(intent);
            finish();
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tipoPieza = parent.getItemAtPosition(position).toString();
        System.out.println(tipoPieza);

        Toast.makeText(parent.getContext(), tipoPieza, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        throw new UnsupportedOperationException();
    }

}