package com.example.saper;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class GUIBoard extends View implements Board {
    public static final float PADDING = 50;

    public Cell<Paint>[][] cells;

    boolean drawCell = false;

    boolean bang = false;
    boolean congratulation = false;

    int cellX = 0;
    int cellY = 0;

    Paint paint = new Paint();

    private void init() {
        paint.setColor(Color.BLACK);
    }

    public GUIBoard(Context context) {
        super(context);
        init();
    }

    public GUIBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GUIBoard(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //  canvas.drawRect(0, 0, 200, 200, paint);
        if (this.cells != null) {
            for (int x = 0; x != cells.length; x++) {
                for (int y = 0; y != cells[0].length; y++) {

                    cells[x][y].draw(paint, bang);
//                    font = new Font("Serif", Font.BOLD, 10);
                    if (bang) {//реальное значение
                        if (cells[x][y].isBomb()) {
                            canvas.drawRect(x * PADDING, y * PADDING, PADDING, PADDING, paint);
                            paint.setColor(Color.BLACK);
                            canvas.drawText("Ты продул Бееееее...",0,200,paint);
//                            font = new Font("Serif", Font.BOLD, 40);
//                            graphics.setColor(Color.RED);
//                            graphics.setFont(font);
//                            graphics.drawString("*", x * PADDING + PADDING / 2, y * PADDING + PADDING / 2);
//                            graphics.drawString("YOU LOOSE", 50, 200);
//                paint. print("[*] ");
                        } else {
//                            graphics.setColor(Color.BLACK);
                            canvas.drawRect(x * PADDING, y * PADDING, PADDING, PADDING, paint);
//                            graphics.setColor(Color.BLACK);
//                            graphics.setFont(font);
//                            graphics.drawString("[ ]", x * PADDING + PADDING / 2, y * PADDING + PADDING / 2);
//                paint.print("[ ] ");
                        }
                    } else {//предположение пользователя
                        if (cells[x][y].isSuggestBomb()) {
//                            paint.setStrokeWidth(0);
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(Color.RED);
                            canvas.drawRect(x * PADDING, y * PADDING, PADDING + x * PADDING, PADDING + y * PADDING, paint);
//                            graphics.setColor(Color.BLACK);
//                            graphics.setFont(font);
//                            graphics.drawString("?", x * PADDING +PADDING/2, y * PADDING + PADDING/2);
//                paint.print("[?] ");
                        } else if (cells[x][y].isSuggestEmpty()) {
//                            graphics.setColor(Color.GREEN);
                            canvas.drawRect(x * PADDING, y * PADDING, PADDING + x * PADDING, PADDING + y * PADDING, paint);
//                            graphics.setColor(Color.BLACK);
//                            graphics.setFont(font);
//                            graphics.drawString(" [ ]", x * PADDING+PADDING/2, y * PADDING + PADDING/2);
//                paint.print("[ ] ");
                        } else {
                            paint.setStrokeWidth(0);
                            paint.setStyle(Paint.Style.STROKE);
                            canvas.drawRect(x * PADDING, y * PADDING, PADDING + x * PADDING, PADDING + y * PADDING, paint);
//                            graphics.setFont(font);
//                            graphics.setColor(Color.BLACK);
//                            graphics.drawString("[X]", x * PADDING+PADDING/2, y * PADDING + PADDING/2);
//                paint.print("[X] ");//если ничего еще не предположил
                        }
                    }

                }
            }


            if (congratulation) {
                paint.setColor(Color.BLACK);
                canvas.drawText("УРА Ты выиграл !!!!!!!!!!!!!!!!!!!!",0,200,paint);
                System.out.println();

/*                font = new Font("Serif", Font.BOLD, 30);
                graphics.setFont(font);
                graphics.drawString("CONGRATULATION!!!!!!!", 50, 200);*/
            }

        }
    }

    @Override
    public void drawBoard(Cell[][] cells) {
        congratulation = false;
        this.cells = cells;
        bang = false;
        this.invalidate();
    }

    @Override
    public void drawCell(int x, int y) {
        bang = false;
        drawCell = true;
        cellX = x;
        cellY = y;

        this.invalidate();
    }

    @Override
    public void drawBang() {
        bang = true;
        congratulation = false;
        this.invalidate();
    }

    @Override
    public void drawCongratulate() {
        congratulation = true;
        this.invalidate();
    }
}
