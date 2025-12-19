public class ProtoTypePattern {


    // In this pattern, We create a new Object by copying other object.

    public static class NPC {
        private String name;
        private int health;
        private int attack;
        private int defence;

        public NPC(String n, int h, int a, int def) {
            this.name = n;
            this.defence = def;
            this.health = h;
            this.attack = a;

            System.out.println("Setting up template NPC " + name);
        }

        // Copy Constructor
        public NPC(NPC other) {
            name = other.name;
            health = other.health;
            attack = other.attack;
            defence = other.defence;

            System.out.println("Copying NPC: " + name);
        }

        public NPC clone() {
            return new NPC(this);
        }

        public void describe() {
            System.out.println("   NPC: " + name + " [HP= " + health + " ATK= " + attack + " DEF= " + defence + "]");
        }

        void setName(String n) {
            name = n;
        }

        void setHealth(int h) {
            health = h;
        }

        void setAttack(int a) {
            attack = a;
        }

        void setDefence(int d) {
            defence = d;
        }
    }

    public static void main(String[] args) {
        NPC alien1 = new NPC("Alien", 30, 5, 2);
        System.out.println();

        NPC alienCopy1 = alien1.clone();
        alienCopy1.describe();
        System.out.println();

        NPC alienCopy2 = alien1.clone();
        alienCopy2.setName("Powerful Alien");
        alienCopy2.setHealth(50);
        alienCopy2.describe();

    }
}
