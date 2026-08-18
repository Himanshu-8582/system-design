package SnakeAndLadder.observer;

public class SnakeAndLadderConsoleNotifier implements Observer {
    public void update(String msg) {
        System.out.println("[NOTIFICATION] " + msg);
    }
}
