import java.util.ArrayList;

public class ObserverPattern {

    public interface ISuscriber {
        void update();
    }
    
    public interface IChannel {
        void suscribe(ISuscriber suscribe);

        void unSuscribe(ISuscriber suscribe);

        void notifySuscribers();
    }


    public static class Channel implements IChannel {
        ArrayList<ISuscriber> suscriber;
        String name;
        String latestVideos;

        public Channel(String name) {
            this.name = name;
            suscriber = new ArrayList<>();
        }

        public void suscribe(ISuscriber sub) {
            if (suscriber.contains(sub)) {
                System.out.println("Already suscribed.");
            } else
                suscriber.add(sub);
        }

        public void unSuscribe(ISuscriber sub) {
            if (suscriber.contains(sub))
                suscriber.remove(sub);
            else
                System.out.println("Cant find Suscriber!");
        }

        public void notifySuscribers() {
            for (ISuscriber s : suscriber) {
                s.update();
            }
        }

        public void uploadVideo(String title) {
            latestVideos = title;
            System.out.println("[ " + name + " uploaded\\ " + title + " ]");
            notifySuscribers();
            System.out.println();
        }

        public String getVideoData() {
            return "Checkout our new Video: " + latestVideos;
        }
    }
    

    // Concrete class for suscribers 
    public static class Suscriber implements ISuscriber {
        String name;
        Channel channel;

        // This name follows this channel
        public Suscriber(String name, Channel channel) {
            this.name = name;
            this.channel = channel;
        }
        
        public void update() {
            System.out.println("Hey "+name+", "+ this.channel.getVideoData());
        }
    }

    
    public static void main(String[] args) {
        Channel channel = new Channel("Code Army");
        Suscriber s1 = new Suscriber("Varun", channel);
        Suscriber s2 = new Suscriber("Tarun", channel);
        Suscriber s3 = new Suscriber("Kl", channel);

        channel.suscribe(s1);
        channel.suscribe(s2);

        channel.uploadVideo("Observer Design Pattern Tutorial");

        channel.unSuscribe(s1);
        channel.unSuscribe(s3);

        channel.uploadVideo("Decorator Design Pattern Tutorial");

    }
}
