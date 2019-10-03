package com.example.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.activities.R;
import com.example.activities.TableroTetris;
import com.example.pieces.Pieza;
import com.example.pieces.PiezaC;
import com.example.pieces.PiezaI;
import com.example.pieces.PiezaL;
import com.example.pieces.PiezaLI;
import com.example.pieces.PiezaT;
import com.example.pieces.PiezaZ;
import com.example.pieces.PiezaZI;

import java.util.ArrayList;
import java.util.Random;

/*
paint next Piece on Screen
 */

public class NextPieceView extends View {

   private TableroTetris tetris;
   private Pieza piezaSig;
    private Bitmap squarePiece = BitmapFactory.decodeResource(getResources(), R.drawable.cuadrado);
    private Bitmap tPiece = BitmapFactory.decodeResource(getResources(), R.drawable.t);
    private Bitmap zPiece = BitmapFactory.decodeResource(getResources(), R.drawable.z);
    private Bitmap sPiece = BitmapFactory.decodeResource(getResources(), R.drawable.s);
    private Bitmap jPiece = BitmapFactory.decodeResource(getResources(), R.drawable.l_invertida);
    private Bitmap lPiece = BitmapFactory.decodeResource(getResources(), R.drawable.l);
    private Bitmap iPiece = BitmapFactory.decodeResource(getResources(), R.drawable.i);

    public NextPieceView(Context context, TableroTetris tetris) {
        super(context);
        this.tetris = tetris;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        piezaSig = tetris.getPiezaSig();
        if(piezaSig instanceof PiezaC){
            canvas.drawBitmap(squarePiece, 0, 0, p);
        }else if(piezaSig instanceof PiezaI){
            canvas.drawBitmap(iPiece, 0, 0, p);
        }else if(piezaSig instanceof PiezaL){
            canvas.drawBitmap(lPiece, 0, 0, p);
        }else if(piezaSig instanceof PiezaLI){
            canvas.drawBitmap(jPiece, 0, 0, p);
        }else if(piezaSig instanceof PiezaT){
            canvas.drawBitmap(tPiece, 0, 0, p);
        }else if(piezaSig instanceof PiezaZ){
            canvas.drawBitmap(zPiece, 0, 0, p);
        }else if(piezaSig instanceof PiezaZI){
            canvas.drawBitmap(sPiece, 0, 0, p);
        }

    }

}
