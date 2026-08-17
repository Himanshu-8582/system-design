package TicTacToe.models;

public class Board {
    private Symbol[][] grid;
    private int size;
    private Symbol emptyCell;

    public Board(int s) {
        size = s;
        emptyCell = new Symbol('-');
        grid = new Symbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = emptyCell;
            }
        }
    }

    public boolean isCellEmpty(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        return grid[row][col] == emptyCell;
    }

    public boolean placeMark(int row, int col, Symbol mark) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if (!isCellEmpty(row, col)) {
            return false;
        }
        grid[row][col] = mark;
        return true;
    }

    public Symbol getCell(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return emptyCell;
        }
        return grid[row][col];
    }

    public int getSize() {
        return size;
    }

    public Symbol getEmptyCell() {
        return emptyCell;
    }

    public void display() {
        System.out.print("\n  ");
        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < size; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].getMark() + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
