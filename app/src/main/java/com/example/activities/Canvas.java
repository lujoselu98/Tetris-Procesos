package com.example.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class Canvas extends View {
    private int squaresize = 1;
    Paint paint = new Paint();

    int left = 180;
    int top = 100;

    int right = 880;
    int bottom = 1500;

    Rect border = new Rect(left, top, right, bottom);

    int height = bottom - top;
    int width = right - left;

    Rect [][] grid = new Rect[height][width];


    int rows = 20;
    int cols = 10;

    int inc_row = height / rows;
    int inc_col = width / cols;


    public Canvas(Context context) {
        super(context);
    }

    int[] rows_L = new int[]{0, 1, 2, 2};
    int[] cols_L = new int[]{0, 0, 0, 1};


    int[] rows_L_down = new int[]{1, 2, 3, 3};

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRGB(255,255,255);

        // Draw border
        paint.setColor(Color.BLACK);
        canvas.drawRect(border, paint);

        drawPiece(4, rows_L, cols_L, canvas);
    }


    public void drawPiece(int num_blocks, int[] pos_rows, int[] pos_cols, android.graphics.Canvas canvas){
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);

//        int left_up_row = 0;
//        int left_up_col = 0;

//        int right_down_row = 0;
//        int right_down_col = 0;

        for(int i = 0; i < num_blocks; i++){
            Rect square = new Rect(
                    left + (pos_cols[i] * inc_row),
                    top + (pos_rows[i] * inc_col),
                    left + ((pos_cols[i] + 1) * inc_row),
                    top + ((pos_rows[i] + 1) * inc_col)
            );
            canvas.drawRect(square, paint);
        }
    }
    private void drawGrid(int x, int y, int xBorder, int yBorder, android.graphics.Canvas canvas)
    {
        paint.setColor(Color.BLUE);

        for (int zeilePixel = 0; zeilePixel <= rows; zeilePixel++) {
            canvas.drawLine(x, y + zeilePixel * squaresize, xBorder, y + zeilePixel * squaresize, paint);
        }

        for (int spaltePixel = 0; spaltePixel <= cols; spaltePixel++) {
            canvas.drawLine(x + spaltePixel * squaresize, y, x + spaltePixel * squaresize, yBorder, paint);
        }

        // Draw border
        paint.setColor(Color.WHITE);
        canvas.drawLine(x, y, x, yBorder, paint);
        canvas.drawLine(x, y, xBorder, y, paint);
        canvas.drawLine(xBorder, yBorder, xBorder, y, paint);
        canvas.drawLine(xBorder, yBorder, x, yBorder, paint);
    }

}

