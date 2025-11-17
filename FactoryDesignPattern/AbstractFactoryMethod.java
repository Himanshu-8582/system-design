
public class AbstractFactoryMethod {

    // Product 1 abstraction Burger
    public interface Burger {
        void prepare();
    }

    public static class BasicBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing a Basic Burger");
        }
    }

    public static class StandardBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing a Standard Burger");
        }
    }

    public static class BasicWheatBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing a Basic Wheat Burger");
        }
    }

    // Product 2 abstraction GarlicBread
    public interface GarlicBread {
        void prepare();
    }

    public static class BasicGarlicBread implements GarlicBread {
        public void prepare() {
            System.out.println("Preparing a Basic Garlic Bread");
        }
    }

    public static class BasicWheatGarlicBread implements GarlicBread {
        public void prepare() {
            System.out.println("Preparing a Basic Wheat Garlic Bread");
        }
    }


    // Factory Abstraction
    public interface Factory {
        public Burger createBurger(String type);

        public GarlicBread createBread(String type);
    }

    public static class SinghBurger implements Factory {
        public Burger createBurger(String type) {
            if (type.equals("Basic"))
                return new BasicBurger();
            else if (type.equals("Standard"))
                return new StandardBurger();
            else {
                System.out.println("Invalid Type");
                return null;
            }
        }

        public GarlicBread createBread(String type) {
            if (type.equals("Basic"))
                return new BasicGarlicBread();
            else {
                System.out.println("Invalid Type");
                return null;
            }
        }
    }

    public static class KingBurger implements Factory {
        public Burger createBurger(String type) {
            if (type.equals("Basic"))
                return new BasicWheatBurger();
            else {
                System.out.println("Invalid Type");
                return null;
            }
        }

        public GarlicBread createBread(String type) {
            if (type.equals("Wheat"))
                return new BasicWheatGarlicBread();
            else {
                System.out.println("Invalid Type");
                return null;
            }
        }
    }

    public static void main(String[] args) {
        String burgerType = "Basic";
        String garlicBreadType = "Wheat";
        Factory mealFactory = new KingBurger();
        Burger burger = mealFactory.createBurger(burgerType);
        GarlicBread bread = mealFactory.createBread(garlicBreadType);

        burger.prepare();
        bread.prepare();
    }
}
