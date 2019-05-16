package com.example.saper;

public interface Board {

    void drawBoard(Cell[][] cells);


    void drawCell(int x, int y);

    /**
     * рисует взрыв всех бомб
     */
    void drawBang();

    /**
     * рисует поздравление, когда игра выиграна
     */
    void drawCongratulate();

}
