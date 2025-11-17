public class FactoryPattern {

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


    public static class BurgerFactory {
        public Burger creatBurger(String type) {
            if (type.equals("Basic"))
                return new BasicBurger();
            else if (type.equals("Standard"))
                return new StandardBurger();
            else if (type.equals("Premium"))
                return new PremiumBurger();
            else{
                System.out.println("Invalid Option");
                return null;
            }
        }
    }

    public static void main(String[] args) {
        // String type = "Standard";
        String type = "Premium";
        BurgerFactory myBurgerFactory = new BurgerFactory();
        Burger burger = myBurgerFactory.creatBurger(type);
        burger.prepare();
    }
}