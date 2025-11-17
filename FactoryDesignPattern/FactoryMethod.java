public class FactoryMethod {
    public interface Burger {
        void prepare();
    }

    public static class BasicBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing Basic Burger");
        }
    }

    public static class StandardBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing Standard Burger");
        }
    }

    public static class PremiumBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing Premium Burger");
        }
    }

    public static class BasicWheatBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing Basic Wheat Burger");
        }
    }

    public static class StandardWheatBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing Standard Wheat Burger");
        }
    }

    public static class PremiumWheatBurger implements Burger {
        public void prepare() {
            System.out.println("Preparing Premium Wheat Burger");
        }
    }

    public interface BurgerFactory {
        public Burger creatBurger(String type);
    }

    public static class SinghBurger implements BurgerFactory {
        public Burger creatBurger(String type) {
            if (type.equals("Basic"))
                return new BasicBurger();
            else if (type.equals("Standard"))
                return new StandardBurger();
            else if (type.equals("Premium"))
                return new PremiumBurger();
            else {
                System.out.println("Invalid Option");
                return null;
            }
        }
    }

    public static class KingBurger implements BurgerFactory {
        public Burger creatBurger(String type) {
            if (type.equals("Basic"))
                return new BasicWheatBurger();
            else if (type.equals("Standard"))
                return new StandardWheatBurger();
            else if (type.equals("Premium"))
                return new PremiumWheatBurger();
            else {
                System.out.println("Invalid Option");
                return null;
            }
        }
    }
    public static void main(String[] args) {
        String type = "Basic";
        BurgerFactory myBurgerFactory = new KingBurger();
        // BurgerFactory myBurgerFactory = new SinghBurger();
        Burger burger = myBurgerFactory.creatBurger(type);
        burger.prepare();
    }
}
