package Tinder.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    private String senderId;
    private String content;
    private long timestamp;

    public Message(String sender, String msg) {
        senderId = sender;
        content = msg;
        timestamp = System.currentTimeMillis();
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getFormattedTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date(timestamp));
    }
}
