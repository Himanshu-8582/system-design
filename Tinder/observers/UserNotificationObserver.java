package Tinder.observers;

public class UserNotificationObserver implements NotificationObserver {
    private String userId;

    public UserNotificationObserver(String id) {
        userId = id;
    }

    public void update(String message) {
        System.out.println("Notification for user " + userId + ": " + message);
    }
}
