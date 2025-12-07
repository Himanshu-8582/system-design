public class VirtualProxy {

    public interface Image {                               // Subject Interface
        void display();
    }

    public static class RealImage implements Image {       // Real Subject
        private String filename;

        public RealImage(String filename) {
            this.filename = filename;
            System.out.println("[RealImage] Loading image from disk: " + filename);
        }

        @Override
        public void display() {
            System.out.println("[RealImage] Displaying image: " + filename);
        }
    }
    
    public static class ImageProxy implements Image {       // Proxy
        private String filename;
        private RealImage realImage;

        public ImageProxy(String filename) {
            this.filename = filename;
            realImage = null;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }
    public static void main(String[] args) {
        Image image1 = new ImageProxy("test_image.jpg");
        image1.display(); // Loads and displays the image
        System.out.println();
        image1.display(); // Displays the image without loading again
    }
}
