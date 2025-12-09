class BridgePattern {

    // Implementor hierarchy for Engine  : Low level layer (LLL)
    public interface Engine {
        public void start();
    }

    // concrete implementations of Engine
    public static class ElectricEngine implements Engine {
        @Override
        public void start() {
            System.out.println("Starting Electric Engine");
        }
    }

    public static class DieselEngine implements Engine {
        @Override
        public void start() {
            System.out.println("Starting Diesel Engine");
        }
    }

    public static class PetrolEngine implements Engine {
        @Override
        public void start() {
            System.out.println("Starting Petrol Engine");
        }
    }


    // Abstraction hierarchy for Car : High level layer (HLL)
    public static abstract class Car {               
        protected Engine e;
        Car(Engine e) {
            this.e = e;
        }

        public abstract void drive();
    }
    
    public static class SUV extends Car {
        public SUV(Engine e) {
            super(e);
        }
        @Override
        public void drive() {
            e.start();
            System.out.println("Driving SUV");
        }
    }

    public static class Sedan extends Car {                 
        public Sedan(Engine e) {
            super(e);
        }

        @Override
        public void drive() {
            e.start();
            System.out.println("Driving Sedan");
        }
    }
    
    

    public static void main(String[] args) {
        Engine electricEngine = new ElectricEngine();
        Engine dieselEngine = new DieselEngine();
        Engine petrolEngine = new PetrolEngine();
        
        Car suvWithElectricEngine = new SUV(electricEngine);   // electric engine + suv
        Car sedanWithDieselEngine = new Sedan(dieselEngine);   // diesel engine + sedan
        Car sedanWithPetrolEngine = new Sedan(petrolEngine);   // petrol engine + sedan

        suvWithElectricEngine.drive();
        sedanWithDieselEngine.drive();
        sedanWithPetrolEngine.drive();
    }
}