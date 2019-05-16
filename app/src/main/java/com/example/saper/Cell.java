package com.example.saper;

public interface Cell<T> {

    boolean isBomb();

    boolean isSuggestBomb();

    boolean isSuggestEmpty();

    /**
     * устанавливает предположение, что нет бобмы
     */
    void suggestEmpty();

    /**
     * устанавливает предположение, что есть бомба
     */
    void suggestBomb();

    /**
     * Рисует клетку
     * @param paint
     * @param real - рисуем реальное значение, либо то, что пользователь выбрал
     */
    void draw(T paint, boolean real);

}
