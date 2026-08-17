package TicTacToe.strategies;

import TicTacToe.models.Board;
import TicTacToe.models.Symbol;

public interface TicTacToeRules {
    boolean isValidMove(Board board, int row, int col);
    boolean checkWinCondition(Board board, Symbol symbol);
    boolean checkDrawCondition(Board board);
}
