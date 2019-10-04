package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.example.pieces.Bloque;
import com.example.pieces.Pieza;
import com.example.pieces.PiezaL;
import java.util.Arrays;

public class Ventana extends View {
    private int squaresize = 1;
    Paint paint = new Paint();
    Pieza p;
    TableroTetris t;

    /*int rowSize = Canvas.getHeight() / 20;
    int colSize = Canvas.getWidth() / 10;*/
    int rowSize;
    int colSize;


    public Ventana(Context context) {

        super(context);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        Bloque[][] forma= p.getForma();
        int filas = forma.length;
        int columnas = forma[0].length;

        colSize = canvas.getWidth()/10;
        rowSize = canvas.getHeight()/20;

        paint.setStyle(Paint.Style.FILL);

        for(int i=0; i<20; i++){
            for(int j=0; j<10; j++){
                Bloque actual = t.bloqueEn(i,j);
                //paint.setColor(actual.getColor());
                Rect cuadradoPieza = new Rect(actual.getPosicion()[1] * colSize, actual.getPosicion()[0] * rowSize, actual.getPosicion()[1] * colSize + colSize, actual.getPosicion()[0] * rowSize + rowSize);
                //canvas.drawRect(cuadradoPieza, paint);

                // border
                paint.setStyle(Paint.Style.STROKE);
                paint.setColor(Color.BLACK);
                paint.setStrokeWidth(8);
                canvas.drawRect(cuadradoPieza, paint);
                // fill
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(actual.getColor());
                canvas.drawRect(cuadradoPieza, paint);
            }
        }

         for(int i=0; i<filas;i++){
            for(int j=0; j<columnas;j++){
                if(forma[i][j].isActivo()) {

                    Rect cuadradoPieza = new Rect(forma[i][j].getPosicion()[1] * colSize, forma[i][j].getPosicion()[0] * rowSize, forma[i][j].getPosicion()[1] * colSize + colSize, forma[i][j].getPosicion()[0] * rowSize + rowSize);
                    // border
                    paint.setStyle(Paint.Style.STROKE);
                    paint.setColor(Color.BLACK);
                    paint.setStrokeWidth(8);
                    canvas.drawRect(cuadradoPieza, paint);
                    // fill
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(forma[i][j].getColor());
                    canvas.drawRect(cuadradoPieza, paint);


                }
            }
        }
    }

    public void setPieza(Pieza p) {
        this.p = p;
    }
    public void setTablero(TableroTetris t){this.t = t;}
}

