package com.example.views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.example.activities.TableroTetris;
import com.example.pieces.Bloque;
import com.example.pieces.Pieza;

import java.util.ArrayList;

public class Ventana extends View {
    private final Paint paint = new Paint();
    private final ArrayList<Pieza> arrayListPiezas;
    private TableroTetris t;
    private int rows = 20;
    private int cols = 10;


    public Ventana(Context context) {

        super(context);
        arrayListPiezas = new ArrayList<>();
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        int colSize = getWidth() / cols;
        int rowSize = getHeight() / rows;


        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                Bloque actual = t.bloqueEn(i, j);
                Rect cuadradoPieza = new Rect(actual.getPosicion()[1] * colSize, actual.getPosicion()[0] * rowSize, actual.getPosicion()[1] * colSize + colSize, actual.getPosicion()[0] * rowSize + rowSize);


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
        for (Pieza p : arrayListPiezas) {
            Bloque[][] forma = p.getForma();
            for (Bloque[] bloques : forma) {
                for (Bloque bloque : bloques) {
                    if (bloque.isActivo()) {


                        Rect cuadradoPieza = new Rect(bloque.getPosicion()[1] * colSize, bloque.getPosicion()[0] * rowSize, bloque.getPosicion()[1] * colSize + colSize, bloque.getPosicion()[0] * rowSize + rowSize);
                        // border
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setColor(Color.BLACK);
                        paint.setStrokeWidth(8);
                        canvas.drawRect(cuadradoPieza, paint);
                        // fill
                        paint.setStyle(Paint.Style.FILL);
                        paint.setColor(bloque.getColor());
                        canvas.drawRect(cuadradoPieza, paint);


                    }
                }
            }
        }



    }

    public void setPieza(Pieza p) {

            this.arrayListPiezas.add(p);

    }



    public void borrarPieza(Pieza pieza) {
        this.arrayListPiezas.remove(pieza);
    }


    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setTablero(TableroTetris t) {
        this.t = t;	
    }
}

