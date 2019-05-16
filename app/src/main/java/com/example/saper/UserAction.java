package com.example.saper;

/**
 * действия пользователя
 */
public interface UserAction {

    //кнопка инициализации игры
    void initGame();

    /**
     * действия пользователя. Он выбрал какую-то ячейку, где предполагает, либо это бомба, либо пустое место
     * @param x
     * @param y
     * @param bomb
     */
    void select(int x, int y, boolean bomb);

}
