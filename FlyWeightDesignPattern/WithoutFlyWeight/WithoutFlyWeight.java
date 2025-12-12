import java.util.ArrayList;
import java.util.List;


public class WithoutFlyWeight {


    public static class Asteroid {
        // intrinsic properties (same for many asteroids)
        int length, width;
        int weight;
        String color, texture, material;

        // Extrinsic properties
        int posX, posY;
        int velX, velY;

        public Asteroid(int l, int w, int wt, String col, String texture, String mat, int posX, int posY, int velX,
                int velY) {
            this.length = l;
            this.width = w;
            this.weight = wt;
            this.color = col;
            this.texture = texture;
            this.material = mat;
            this.posX = posX;
            this.posY = posY;
            this.velX = velX;
            this.velY = velY;
        }

        public void render() {
            System.out.println("Rendering: " + color + ", " + texture + ", " + material +
                    " asteroid at (" + posX + ", " + posY + ") Size: " + length + " X " + width +
                    " Velocity: (" + velX + ", " + velY + ") ");
        }

        // Calculate approx memory usage per Object
        public static long getMemoryUsage(Asteroid a) {
            return Agent.getObjectSize(a);
        }
    }

    public static class SpaceGame {
        List<Asteroid> asteroids = new ArrayList<>();

        // creates Asteroids
        public void spawnAsteroids(int count) {
            List<String> colors = new ArrayList<>(List.of("Red", "Blue", "Green"));
            List<String> texture = new ArrayList<>(List.of("Rocky", "Metallic", "Icy"));
            List<String> material = new ArrayList<>(List.of("Iron", "Stone", "Ice"));
            int sizes[] = { 25, 35, 45 };

            for (int i = 0; i < count; i++) {
                int type = i % 3;
                asteroids.add(new Asteroid(sizes[type], sizes[type], sizes[type] * 10, colors.get(type),
                        texture.get(type), material
                                .get(type),
                        100 + i * 50, 200 + i * 30, 1, 2));
            }
            System.out.println("Created " + asteroids.size() + " asteroid Objects.");
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
            for (Asteroid a : asteroids) {
                total += Asteroid.getMemoryUsage(a);
            }
            return total;
        }
    }
    

    public static void main(String[] args) {
        int asteroid_cnt = 1000000;
        System.out.println("-----Testing without FlyWeight Pattern-----");
        SpaceGame game = new SpaceGame();
        game.spawnAsteroids(asteroid_cnt);
        game.renderAll();
        long total_memory = game.calculateMemoryUsage();
        System.out.println();
        System.out.println("------Memory Usage------ ");
        System.out.println("Total Asteroids: "+asteroid_cnt);
        System.out.println("Memory per asteroid: "+Asteroid.getMemoryUsage(game.asteroids.get(0))+" bytes");
        System.out.println("Total Memory used: "+total_memory);
        System.out.println("Memory in MB: " + (total_memory / (1024.0 * 1024.0)) + " MB");
    }
}
