package com.example.saper;

import android.graphics.Paint;


public class GUICell implements Cell<Paint> {
    private boolean bomb;
    private boolean suggestBomd = false;
    private boolean suggestEmpty = false;

    public GUICell(boolean bomb) {
        this.bomb = bomb;
    }

    @Override
    public boolean isBomb() {
        return bomb;
    }

    @Override
    public boolean isSuggestBomb() {
        return suggestBomd;
    }

    @Override
    public boolean isSuggestEmpty() {
        return suggestEmpty;
    }

    @Override
    public void suggestEmpty() {
        suggestEmpty = true;
        suggestBomd = false;
    }

    @Override
    public void suggestBomb() {
        suggestBomd = true;
        suggestEmpty = false;
    }

    @Override
    public void draw(Paint paint, boolean real) {

    }
}
