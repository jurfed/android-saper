package com.example.saper;


/**
 * реализация логики
 */
public class Easy implements SaperLogic {
    private Cell[][] cells;

    @Override
    public void LoadBoard(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * взрываемся если это бомба, и пользователь не предположил, что это бомба
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean shouldBang(int x, int y) {
        final Cell selected = this.cells[x][y];
        return selected.isBomb() && !selected.isSuggestBomb();
    }

    /**
     * установка предположения пользователя
     * @param x
     * @param y
     * @param bomb
     */
    @Override
    public void suggest(int x, int y, boolean bomb) {
        if (bomb){
            this.cells[x][y].suggestBomb();
        }else{
            this.cells[x][y].suggestEmpty();
        }
    }


    /**
     * проверка на завершение игры
     * @return
     */
    @Override
    public boolean finish() {
        boolean finish = false;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                finish = ((cell.isSuggestBomb() && cell.isBomb()) ||
                        (cell.isSuggestEmpty() && !cell.isBomb()));
                if(!finish){
                    break;
                }
            }
            if(!finish){
                break;
            }
        }
        return finish;
    }
}
