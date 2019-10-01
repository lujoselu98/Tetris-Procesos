package com.example.activities;

import android.annotation.SuppressLint;
import android.content.Context;
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

    /*int rowSize = getHeight() / 20;
    int colSize = getWidth() / 10;*/
    int rowSize = 20;
    int colSize = 20;


    public Ventana(Context context) {

        super(context);
        p = new PiezaL(1,new Color());
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        Bloque[][] forma= p.getForma();

        paint.setStyle(Paint.Style.FILL);
         for(int i=0; i<forma.length;i++){
            for(int j=0; j<forma.length;j++){
                if(forma[i][j].isActivo()){
                    paint.setColor(Color.BLUE);

                }else{
                    paint.setColor(Color.WHITE);
                }

                Rect cuadradoPieza = new Rect(forma[i][j].getPosicion()[1]*colSize, forma[i][j].getPosicion()[0]*rowSize, forma[i][j].getPosicion()[1]*colSize + colSize, forma[i][j].getPosicion()[0]*rowSize + rowSize);
                canvas.drawRect(cuadradoPieza, paint);
            }
        }
    }

    public void setPieza(Pieza p) {
        this.p = p;
    }
}

