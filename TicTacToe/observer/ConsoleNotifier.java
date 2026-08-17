package TicTacToe.observer;


public class ConsoleNotifier implements Observer {
    public void update(String msg) {
        System.out.println("[Notification] " + msg);
    }
}
