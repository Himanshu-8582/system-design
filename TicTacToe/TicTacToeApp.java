package TicTacToe;

import java.util.Scanner;

import TicTacToe.enums.GameType;
import TicTacToe.facade.TicTacToeGame;
import TicTacToe.factory.TicTacToeGameFactory;
import TicTacToe.models.Symbol;
import TicTacToe.models.TicTacToePlayer;
import TicTacToe.observer.ConsoleNotifier;
import TicTacToe.observer.Observer;

public class TicTacToeApp {
    public static void main(String[] args) {
        System.out.println("=== TIC TAC TOE GAME ===");
        
        // Create game with custom board size
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        int boardSize = scanner.nextInt();
        
        TicTacToeGame game = TicTacToeGameFactory.createGame(GameType.STANDARD, boardSize);
        
        // Add observer
        Observer notifier = new ConsoleNotifier();
        game.addObserver(notifier);
        
        // Create players with custom symbols
        TicTacToePlayer player1 = new TicTacToePlayer(1, "Aditya", new Symbol('X'));
        TicTacToePlayer player2 = new TicTacToePlayer(2, "Harshita", new Symbol('O'));
        
        game.addPlayer(player1);
        game.addPlayer(player2);
        
        // Play the game
        game.play();
        
        scanner.close();
    }
}
