package TicTacToe.models;

public class TicTacToePlayer {
    private int playerId;
    private String name;
    private Symbol symbol;
    private int score;

    public TicTacToePlayer(int playerId, String n, Symbol s) {
        this.playerId = playerId;
        name = n;
        symbol = s;
        score = 0;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore() {
        score++;
    }
}
