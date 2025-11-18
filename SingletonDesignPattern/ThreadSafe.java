public class ThreadSafe {
    public static class Singleton {
        private static volatile Singleton instance = null;      // ensures visibility of updated value across threads
        private Singleton() {                                   // Private constructor : Prevents direct object creation
            System.out.println("Constructor is called.");
        }

        public static Singleton getInstance() {
            if (instance == null) {                            // First check     
                synchronized (Singleton.class) {               // This ensures only one thread creates the instance.Locks the part of code for a thread, after the complete execution of code thread can unlock this section.
                    if (instance == null) {                    // second check
                        instance = new Singleton();
                    }
                }
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        Singleton s2 = Singleton.getInstance();
        System.out.println(s1 == s2);
    }
}
