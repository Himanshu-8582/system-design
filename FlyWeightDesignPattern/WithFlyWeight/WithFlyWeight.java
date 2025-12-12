import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
public class WithFlyWeight {

    // FlyWeight - Stores INTRINSIC state only
    public static class AsteroidFlyWeight {
        // intrinsic properties only (Share among asteroids of same group)
        int length, width;
        int weight;
        String color, texture, material;

        public AsteroidFlyWeight(int l, int w, int wt, String col, String texture, String mat) {
            this.length = l;
            this.width = w;
            this.weight = wt;
            this.color = col;
            this.texture = texture;
            this.material = mat;
        }

        public void render(int posX, int posY, int velX, int velY) {
            System.out.println("Rendering: " + color + ", " + texture + ", " + material +
                    " asteroid at (" + posX + ", " + posY + ") Size: " + length + " X " + width +
                    " Velocity: (" + velX + ", " + velY + ") ");
        }

        public static long getMemoryUsage(AsteroidFlyWeight a) {
            return Agent.getObjectSize(a);
        }
    }
    
    // FlyWeight Factory 
    public static class AsteroidFactory {
        private static HashMap<String, AsteroidFlyWeight> flyWeights = new HashMap<>();

        public static AsteroidFlyWeight getAsteroid(int len, int wid, int wt, String col, String text, String mat) {
            // Create a unique key for this combination
            String key = Integer.toString(len) + " " + Integer.toString(wid) + " " + Integer.toString(wt)+ " " + col + " " + text + " " + mat;
            // If the key does not exist, create a new flyweight and store it
            if (!flyWeights.containsKey(key)) {
                flyWeights.putIfAbsent(key, new AsteroidFlyWeight(len, wid, wt, col, text, mat));
            }
            // Return the shared flyweight object
            return flyWeights.get(key);
        }

        public static int getFlyWeightCount() {
            return flyWeights.size();
        }

        public static Collection<AsteroidFlyWeight> getAllFlyWeights() {
            return flyWeights.values();
        }


        public long calculateMemoryUsage() {
            long total = 0;
            for (AsteroidFlyWeight a : flyWeights.values()) {
                total += AsteroidFlyWeight.getMemoryUsage(a);
            }

            return total;
        }

        public void cleanUp() {
            flyWeights.clear();
        }
    }


    // Context - Stores EXTRINSIC state only
    public static class AsteroidContext { // Client Interacts With this
        private AsteroidFlyWeight flyWeight;
        int posX, posY;
        int velX, velY;

        public AsteroidContext(AsteroidFlyWeight fw, int x, int y, int velX, int velY) {
            this.flyWeight = fw;
            this.posX = x;
            this.posY = y;
            this.velX = velX;
            this.velY = velY;
        }

        public void render() {
            flyWeight.render(posX, posY, velX, velY);
        }

        public void update() {
            posX += velX;
            posY += velY;
        }

        public long getMemoryUsage() {
            return Agent.getObjectSize(this);
        }
    }
    


    // Space Game
    public static class SpaceGameWithFlyWeight {
        List<AsteroidContext> asteroids=new ArrayList<>();

        public void spawnAsteroids(int count) {
            List<String> colors = new ArrayList<>(List.of("Red", "Blue", "Green"));
            List<String> texture = new ArrayList<>(List.of("Rocky", "Metallic", "Icy"));
            List<String> material = new ArrayList<>(List.of("Iron", "Stone", "Ice"));
            int sizes[] = { 25, 35, 45 };

            for (int i = 0; i < count; i++) {
                int type = i % 3;
                AsteroidFlyWeight flyWeight=AsteroidFactory.getAsteroid(sizes[type], sizes[type], sizes[type]*10, colors.get(type), texture.get(type), material.get(type));
                asteroids.add(new AsteroidContext(flyWeight, 100+i*50, 200+i*30, 1, 2));
            }
            System.out.println("Created " + asteroids.size() + " asteroid Contexts.");
            System.out.println("Total FlyWeight Objects: "+AsteroidFactory.getFlyWeightCount());
        }
        

        public void renderAll() {
            System.out.println("Rendering first five asteroids:----");
            for (int i = 0; i < Math.min(5, asteroids.size()); i++) {
                asteroids.get(i).render();
            }
        }
        
        public int getAsteroidCount() {
            return asteroids.size();
        }

        public long calculateMemoryUsage() {
            long total = Agent.getObjectSize(asteroids); // shallow size of list
            for (AsteroidContext a : asteroids) {
                total += Agent.getObjectSize(a); // extrinsic state
            }
            // Add Flyweight memory
            for (AsteroidFlyWeight fw : AsteroidFactory.getAllFlyWeights()) {
                total += AsteroidFlyWeight.getMemoryUsage(fw);
            }
            return total;
        }

    }
    public static void main(String[] args) {
        int asteroid_cnt = 1000000;
        System.out.println("-----Testing with FlyWeight Pattern-----");
        SpaceGameWithFlyWeight game = new SpaceGameWithFlyWeight();
        game.spawnAsteroids(asteroid_cnt);
        game.renderAll();
        long total_memory = game.calculateMemoryUsage();
        System.out.println();
        System.out.println("------Memory Usage------ ");
        System.out.println("Total Asteroids: " + asteroid_cnt);
        System.out.println("Memory per asteroid: " + game.asteroids.get(0).getMemoryUsage() + " bytes");
        System.out.println("Total Memory used: " + total_memory+ "bytes");
        System.out.println("Memory in MB: " + (total_memory / (1024.0 * 1024.0)) + " MB");
    }
}
