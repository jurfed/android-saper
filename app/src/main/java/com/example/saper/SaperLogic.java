package com.example.saper;


/**
 * логика игры
 */
public interface SaperLogic {

    //загружаем поле
    void LoadBoard(Cell[][] cells);

    /**
     * проверяем нужно ли бахнуть на 4-ое нажатие на бомбу
     * @param x
     * @param y
     * @return
     */
    boolean shouldBang(int x, int y);

    /**
     * событие, кот приходит от пользователя. Пользователь сделал предположение, а мы должны это отметить
     * @param x
     * @param y
     * @param bomb
     */
    void suggest(int x, int y, boolean bomb);

    /**
     * Метод, кот проверяет, разгадал ли пользователь все поле или нет
     */
    boolean finish();

}
