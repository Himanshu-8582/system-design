package Tinder.Services;

import java.util.HashMap;
import java.util.Map;

import Tinder.observers.NotificationObserver;

public class NotificationService {
    private Map<String, NotificationObserver> observers;

    // Singleton Pattern
    private static NotificationService instance;
    private NotificationService() {
        observers = new HashMap<>();
    }
    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }
    public void registerObserver(String userId, NotificationObserver observer) {
        observers.put(userId, observer);
    }
    public void removeObserver(String userId) {
        observers.remove(userId);
    }
    public void notifyUser(String userId, String message) {
        if (observers.containsKey(userId)) {
            observers.get(userId).update(message);
        }
    }
    public void notifyAll(String message) {
        for (Map.Entry<String, NotificationObserver> pair : observers.entrySet()) {
            pair.getValue().update(message);
        }
    }
}
