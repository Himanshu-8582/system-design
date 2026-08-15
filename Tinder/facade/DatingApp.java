package Tinder.facade;

import java.util.ArrayList;
import java.util.List;

import Tinder.Services.LocationService;
import Tinder.Services.NotificationService;
import Tinder.enums.MatcherType;
import Tinder.enums.SwipeAction;
import Tinder.factories.MatcherFactory;
import Tinder.matchers.Matcher;
import Tinder.models.ChatRoom;
import Tinder.models.User;

public class DatingApp {
    private List<User> users;
    private List<ChatRoom> chatRooms;
    private Matcher matcher;
    
    // Singleton Pattern
    private static DatingApp instance;

    private DatingApp() {
        users = new ArrayList<>();
        chatRooms = new ArrayList<>();

         // Default to location-based matcher
        matcher = MatcherFactory.createMatcher(MatcherType.LOCATION_BASED);
    }

    public static DatingApp getInstance() {
        if (instance == null) {
            instance = new DatingApp();
        }
        return instance;
    }

    public void setMatcher(MatcherType type) {
        matcher = MatcherFactory.createMatcher(type);
    }

    public User createUser(String userId) {
        User user = new User(userId);
        users.add(user);
        return user;
    }

    public User getUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public List<User> findNearbyUsers(String userId, double maxDistance) {
        User user = getUserById(userId);
        if (user == null) {
            return new ArrayList<>();
        }

        // Find users within maxDistance km
        List<User> nearbyUsers = LocationService.getInstance().findNearbyUsers(
                user.getProfile().getLocation(), maxDistance, users);

        // Filter out the user themselves        
        nearbyUsers.remove(user);

        // Filter out users that don't match preferences or have already been swiped       
        List<User> filteredUsers = new ArrayList<>();
        for (User otherUser : nearbyUsers) {
            // Skip users that have already been interacted with
            if (!user.hasInteractedWith(otherUser.getId())) {
                
                // Calculate match score
                double score = matcher.calculateMatchScore(user, otherUser);
                
                // If score is above 0, they meet basic preference criteria
                if (score > 0) {
                    filteredUsers.add(otherUser);
                }
            }
        }

        return filteredUsers;
    }

    public boolean swipe(String userId, String targetUserId, SwipeAction action) {
        User user = getUserById(userId);
        User targetUser = getUserById(targetUserId);

        if (user == null || targetUser == null) {
            System.out.println("User not found.");
            return false;
        }

        user.swipe(targetUserId, action);

        // Check if it's a match
        if (action == SwipeAction.RIGHT && targetUser.hasLiked(userId)) {
            // It's a match!
            String chatRoomId = userId + "_" + targetUserId;
            ChatRoom chatRoom = new ChatRoom(chatRoomId, userId, targetUserId);
            chatRooms.add(chatRoom);

            // Notify both users
            NotificationService.getInstance().notifyUser(userId, "You have a new match with " + targetUser.getProfile().getName() + "!");
            NotificationService.getInstance().notifyUser(targetUserId, "You have a new match with " + user.getProfile().getName() + "!");
            return true;
        }
        return false;
    }

    public ChatRoom getChatRoom(String user1Id, String user2Id) {
        for (ChatRoom chatRoom : chatRooms) {
            if (chatRoom.hasParticipant(user1Id) && chatRoom.hasParticipant(user2Id)) {
                return chatRoom;
            }
        }
        return null;
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        ChatRoom chatRoom = getChatRoom(senderId, receiverId);
        if (chatRoom == null) {
            System.out.println("No chat room found between these users.");
            return;
        }

         // Notify the receiver
        chatRoom.addMessage(senderId, content);
        NotificationService.getInstance().notifyUser(receiverId, "New message from " + getUserById(senderId).getProfile().getName());
    }

    public void displayUser(String userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        user.displayProfile();
    }

    public void displayChatRoom(String user1Id, String user2Id) {
        ChatRoom chatRoom = getChatRoom(user1Id, user2Id);
        if (chatRoom == null) {
            System.out.println("No chat room found between these users.");
            return;
        }
        chatRoom.displayChat();
    }
}
