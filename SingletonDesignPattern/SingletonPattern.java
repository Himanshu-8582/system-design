public class SingletonPattern {


    // Its is a good practice but not Thread safe

    public static class Singleton {
        private static Singleton instance = null;

        private Singleton() {
            System.out.println("Constructor is called.");
        }

        public static Singleton getInstance() {
            if (instance == null)
                instance = new Singleton();
            return instance;
        }
    }
    
    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1==s2);
    }
}
