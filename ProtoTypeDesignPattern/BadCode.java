public class BadCode {

    public static class NPC {
        String name;
        int health;
        int attack;
        int defence;

        public NPC(String n, int h, int a, int def) {       // let its a heavy constructor it take a lots of time for creating object
            this.name = n;
            this.defence = def;
            this.health = h;
            this.attack = a;

            System.out.println(
                    "Creating NPC " + name + " [HP: " + health + ", ATK: " + attack + ", DEF: " + defence + "]");
        }
        
        public void describe() {
            System.out.println("  NPC: " + name + " | HP= " + health + " ATK= " + attack + " DEF= " + defence);
        }
    }

    public static void main(String[] args) {
        
        NPC alien1 = new NPC("Alien", 30, 5, 2);
        alien1.describe();

        System.out.println();
        System.out.println();

        NPC alien2 = new NPC("Powerful Alien", 50, 5, 5);
        alien2.describe();


        // Note:-> If we want to make 100 aliens then we need to repeat this 100 times.

    }
}
