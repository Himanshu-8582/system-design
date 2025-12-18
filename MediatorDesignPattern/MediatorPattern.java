import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MediatorPattern {

    public interface IMediator{
        void registerColleague(Colleague c);

        void send(String from, String msg);
        
        void mute(String who, String whom);

        void sendPrivate(String from, String to, String msg);
    }
    
    public static abstract class Colleague {
        protected IMediator mediator;

        public Colleague(IMediator m) {
            this.mediator = m;
        }

        public abstract String getName();

        public abstract void send(String msg);

        public abstract void sendPrivate(String to, String msg);

        public abstract void receive(String from, String msg);
    }

    public static class ChatMediator implements IMediator {
        private final ArrayList<Colleague> colleagues = new ArrayList<>();
        private final HashMap<String,Set<String>> muted = new HashMap<>(); // (muter, muted)

        public void registerColleague(Colleague c) {
            if (!colleagues.contains(c)) {
                colleagues.add(c);
            }
        }

        public void mute(String who, String whom) {
            muted.computeIfAbsent(who, k -> new HashSet<>()).add(whom);
        }

        private boolean isMuted(String sender, String receiver) {
            return muted.getOrDefault(receiver, Collections.emptySet()).contains(sender);
        }

        public void send(String from, String msg) {
            System.out.println("[" + from + " Broadcasts]: " + msg);
            for (Colleague c : colleagues) {
                if (!c.getName().equals(from) && !isMuted(from, c.getName())) {
                    c.receive(from, msg);
                }
            }
        }

        public void sendPrivate(String from, String to, String msg) {
            System.out.println("[" + from + " -> " + to + "]: " + msg);
            for (Colleague c : colleagues) {
                if (c.getName().equals(to)) {
                    if (isMuted(from, to)) {
                        System.out.println("   Message blocked (muted)");
                        return;
                    }
                    c.receive(from, msg);
                    return;
                }
            }
            System.out.println("[Mediator] User not found: " + to);
        }
    }
    
    public static class User extends Colleague {
        private String name;

        public User(String n,IMediator m){
            super(m);
            this.name = n;
            m.registerColleague(this);
        }

        public String getName() {
            return name;
        }

        public void send(String msg) {
            mediator.send(name, msg);
        }

        public void sendPrivate(String to, String msg) {
            mediator.sendPrivate(name, to, msg);
        }

        public void receive(String from, String msg) {
            System.out.println("   "+name+" got from "+from+": "+msg);
        }
    }
    

    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatMediator();
        User user1 = new User("Rohan", chatRoom);
        User user2 = new User("Neha", chatRoom);
        User user3 = new User("Mohan", chatRoom);

        chatRoom.mute("Rohan", "Mohan");
        user1.send("Hello Everyone");
        user3.send("Hello world"); // Rohan muted Mohan so he cant get message
        user3.sendPrivate("Neha", "Hey neha");
    }
}
