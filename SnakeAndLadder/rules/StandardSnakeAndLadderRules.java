package SnakeAndLadder.rules;

import SnakeAndLadder.models.Board;
import SnakeAndLadder.models.BoardEntity;

public class StandardSnakeAndLadderRules implements SnakeAndLadderRules {
    @Override
    public boolean isValidMove(int currentPos, int diceValue, int boardSize) {
        return (currentPos + diceValue) <= boardSize;
    }
    
    @Override
    public int calculateNewPosition(int currentPos, int diceValue, Board board) {
        int newPos = currentPos + diceValue;
        BoardEntity entity = board.getEntity(newPos);
        
        if(entity != null) {
            return entity.getEnd();
        }
        return newPos;
    }
    
    @Override
    public boolean checkWinCondition(int position, int boardSize) {
        return position == boardSize;
    }
}
