package TicTacToe.factory;

import TicTacToe.enums.GameType;
import TicTacToe.facade.TicTacToeGame;

public class TicTacToeGameFactory {
     public static TicTacToeGame createGame(GameType gt, int boardSize) {
        if(GameType.STANDARD == gt) {
            return new TicTacToeGame(boardSize);
        }
        return null;
    }
}
