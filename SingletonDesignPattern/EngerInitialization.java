public class EngerInitialization {
    public static class Singleton {
        static Singleton instance = new Singleton();

        private Singleton() {
            System.out.println("Singleton class constructor called");
        }

        static Singleton getInstance() {
            return instance;
        }
    }
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1==s2);
    }
}
