
public class DecoratorPattern {

    public interface Character {          // Interface
        public String getAbilities();
    }

    public static abstract class MarioDecorator implements Character {     // Abstract class
        protected Character character;

        public MarioDecorator(Character decoratedMario) {
            this.character = decoratedMario;
        }
    }

    public static class Mario implements Character {         // Concrete class mario
        public String getAbilities() {
            return "Small Mario! ";
        }
    }
    
    public static class HeightUp extends MarioDecorator {    // Concrete Decorator class height up
        public HeightUp(Character decoratedMario) {
            super(decoratedMario);
        }

        public String getAbilities() {
            return character.getAbilities() + "+ Height Up! ";
        }
    }
    
    public static class GunPower extends MarioDecorator {    // Concrete Decorator class gun power
        public GunPower(Character decoratedMario) {
            super(decoratedMario);
        }

        public String getAbilities() {
            return character.getAbilities() + "+ Gun Power! ";
        }
    }

    public static class StarPower extends MarioDecorator {   // Concrete Decorator class star power
        public StarPower(Character decoratedMario) {
            super(decoratedMario);
        }

        public String getAbilities() {
            return character.getAbilities() + "+ Star Power! ";
        }
    }


    public static void main(String[] args) {
        Character mario = new Mario();
        System.out.println("Basic Character : " + mario.getAbilities());

        mario = new HeightUp(mario);
        System.out.println("After Height Up : " + mario.getAbilities());

        mario = new GunPower(mario);
        System.out.println("After Gun Power : " + mario.getAbilities());
        
        mario = new StarPower(mario);
        System.out.println("After Star Power : " + mario.getAbilities());
    }
}
