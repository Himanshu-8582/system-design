import java.util.ArrayList;

public class BadCode {

    public static class User {
        private String name;
        private ArrayList<User> peers =new ArrayList<>();
        private ArrayList<String> mutedUsers=new ArrayList<>();

        public User(String n) {
            this.name = n;
        }

        public void addPeer(User u) {
            peers.add(u);
        }

        public void mute(String userToMute) {
            mutedUsers.add(userToMute);
        }

        public void send(String msg) {
            System.out.println("[" + name + " Broadcasts]: " + msg);
            for (User peer : peers) {
                if (!peer.isMuted(name)) {
                    peer.receive(name, msg);
                }
            }
        }
        
        public boolean isMuted(String userName) {
            for (String name : mutedUsers) {
                if (name.equals(userName))
                    return true;
            }
            return false;
        }

        public void sendTo(User tgt, String msg) {
            System.out.println("[" + name + "->" + tgt.name + " ]: " + msg);
            if (!tgt.isMuted(name)) {
                tgt.receive(name, msg);
            }
        }
        
        public void receive(String from, String msg) {
            System.out.println("   "+name+ " got from "+from+": "+msg);
        }

    }
    public static void main(String[] args) {
        User user1 = new User("Rohan");
        User user2 = new User("Neha");
        User user3 = new User("Mohan");

        user1.addPeer(user2);
        user1.addPeer(user3);

        user2.addPeer(user1);
        user2.addPeer(user3);

        user3.addPeer(user1);
        user3.addPeer(user2);

        user1.mute("Mohan");
        user1.send("Hello everyone");
        user3.send("Hello world");                //Rohan muted Mohan so he cant get message
        user3.sendTo(user2, "Hey Neha");
    }
}
