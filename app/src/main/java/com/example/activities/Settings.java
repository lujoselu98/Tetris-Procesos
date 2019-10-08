package com.example.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public  class Settings extends Activity implements AdapterView.OnItemSelectedListener {
    //--Declaramos la variable para nuestro control
    SeekBar seekBar1;
    TextView porcentajeDificultad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Velocidad

        porcentajeDificultad = (TextView) findViewById(R.id.textoNivelDificultad);
        seekBar1 = (SeekBar) findViewById(R.id.seekBarVelocidad);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                porcentajeDificultad.setText("" + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //MODO DIFICIL (PIEZA CADA 30 SEGUNDOS

        Switch switchModoDificil = findViewById(R.id.casillaModoNuevo);

        //Tipo Piezas
        Spinner tipoPiezas = findViewById(R.id.seleccion_tipo_piezas);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.tipo_piezas, R.layout.spinner_levels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoPiezas.setAdapter(adapter);
        tipoPiezas.setOnItemSelectedListener(this);

        //BOTON PARA INICIAR PARTIDA

        Button botonInicioPartida = (Button)findViewById(R.id.botonInicioPartida);
        botonInicioPartida.setOnClickListener(v -> {
            String str1, str2;
            if (switchModoDificil.isChecked())
                str1 = switchModoDificil.getTextOn().toString();
            else
                str1 = switchModoDificil.getTextOff().toString();

            Toast.makeText(getApplicationContext(), "Switch1 -  " + str1 + " \n" ,Toast.LENGTH_SHORT).show();
        });
    }




    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String tipoPieza = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), tipoPieza,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}