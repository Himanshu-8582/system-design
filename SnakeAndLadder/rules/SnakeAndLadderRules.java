package SnakeAndLadder.rules;

import SnakeAndLadder.models.Board;

public interface SnakeAndLadderRules {
    public boolean isValidMove(int currentPos, int diceValue, int boardSize);

    public int calculateNewPosition(int currentPos, int diceValue, Board board);

    public boolean checkWinCondition(int position, int boardSize);
}
