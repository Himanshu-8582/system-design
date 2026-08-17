package TicTacToe.facade;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

import TicTacToe.models.Board;
import TicTacToe.models.TicTacToePlayer;
import TicTacToe.observer.Observer;
import TicTacToe.strategies.StandardTicTacToeRules;
import TicTacToe.strategies.TicTacToeRules;

public class TicTacToeGame {
    private Board board;
    private Deque<TicTacToePlayer> players;
    private TicTacToeRules rules;
    private List<Observer> observers;
    private boolean gameOver;
    
    public TicTacToeGame(int boardSize) {
        board = new Board(boardSize);
        players = new ArrayDeque<>();
        rules = new StandardTicTacToeRules();
        observers = new ArrayList<>();
        gameOver = false;
    }
    
    public void addPlayer(TicTacToePlayer player) {
        players.addLast(player);
    }
    
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notify(String msg) {
        for(Observer observer : observers) {
            observer.update(msg);
        }
    }
    
    public void play() {
        if(players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }
        
        notify("Tic Tac Toe Game Started!");
        
        Scanner scanner = new Scanner(System.in);
        
        while(!gameOver) {
            board.display();
            
            // Take out the current player from dequeue
            TicTacToePlayer currentPlayer = players.peekFirst();
            System.out.print(currentPlayer.getName() + " (" + currentPlayer.getSymbol().getMark() + ") - Enter row and column: ");
            
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            
            // check if move is valid
            if(rules.isValidMove(board, row, col)) {
                board.placeMark(row, col, currentPlayer.getSymbol());
                notify(currentPlayer.getName() + " played (" + row + "," + col + ")");
                
                if(rules.checkWinCondition(board, currentPlayer.getSymbol())) {
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify(currentPlayer.getName() + " wins!");

                    gameOver = true;
                }
                else if(rules.checkDrawCondition(board)) {
                    board.display();
                    
                    System.out.println("It's a draw!");
                    notify("Game is Draw!");

                    gameOver = true;
                }
                else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            }
            else {
                System.out.println("Invalid move! Try again.");
            }
        }
    }
}
