package Tinder.models;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom {
    private String id;
    private List<String> participantIds;
    private List<Message> messages;

    public ChatRoom(String roomId, String user1Id, String user2Id) {
        id = roomId;
        participantIds = new ArrayList<>();
        participantIds.add(user1Id);
        participantIds.add(user2Id);
        messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void addMessage(String senderId, String content) {
        Message msg = new Message(senderId, content);
        messages.add(msg);
    }

    public boolean hasParticipant(String userId) {
        return participantIds.contains(userId);
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<String> getParticipants() {
        return participantIds;
    }

    public void displayChat() {
        System.out.println("===== Chat Room: " + id + " =====");
        for (Message msg : messages) {
            System.out.println("[" + msg.getFormattedTime() + "] " + msg.getSenderId() + ": " + msg.getContent());
        }
        System.out.println("=========================");
    }
}
