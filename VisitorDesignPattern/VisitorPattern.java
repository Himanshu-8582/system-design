public class VisitorPattern {


    // IVisitor
    public interface FileSystemVisitor {
        public void visit(TextFile file);
        
        public void visit(ImageFile file);
        
        public void visit(VideoFile file);
    }

    public static class ScanVirusVisitor implements FileSystemVisitor {
        @Override
        public void visit(TextFile file) {
            System.out.println("Scanning text file : " + file.getName());
        }

        @Override
        public void visit(ImageFile file) {
            System.out.println("Scanning Image file : " + file.getName());
        }

        @Override
        public void visit(VideoFile file) {
            System.out.println("Scanning Video file : " + file.getName());
        }
    }

    public static class CompressVisitor implements FileSystemVisitor {
        @Override
        public void visit(TextFile file) {
            System.out.println("Compressing text file : " + file.getName());
        }

        @Override
        public void visit(ImageFile file) {
            System.out.println("Compressing Image file : " + file.getName());
        }

        @Override
        public void visit(VideoFile file) {
            System.out.println("Compressing Video file : " + file.getName());
        }
    }

    public static class SizeCalculatorVisitor implements FileSystemVisitor {
        @Override
        public void visit(TextFile file) {
            System.out.println("Calculating size of text file : " + file.getName());
        }

        @Override
        public void visit(ImageFile file) {
            System.out.println("Calculating size of Image file : " + file.getName());
        }

        @Override
        public void visit(VideoFile file) {
            System.out.println("Calculating size of Video file : " + file.getName());
        }
    }



    // Document Element
    public static abstract class FileSystemItem {
        private String name;

        FileSystemItem(String itemName) {
            name = itemName;
        }

        public String getName() {
            return name;
        }

        public void accept(FileSystemVisitor v) { };
    }

    public static class TextFile extends FileSystemItem {
        private String content;

        public TextFile(String fileName, String fileContent) {
            super(fileName);
            content = fileContent;
        }

        public String getContent() {
            return content;
        }

        @Override
        public void accept(FileSystemVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class ImageFile extends FileSystemItem {

        public ImageFile(String fileName) {
            super(fileName);
        }

        @Override
        public void accept(FileSystemVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static class VideoFile extends FileSystemItem {

        public VideoFile(String fileName) {
            super(fileName);
        }

        @Override
        public void accept(FileSystemVisitor visitor) {
            visitor.visit(this);
        }
    }

    public static void main(String[] args) {
        FileSystemItem img1 = new ImageFile("sample.jpg");
        img1.accept(new CompressVisitor());
        img1.accept(new ScanVirusVisitor());
        img1.accept(new SizeCalculatorVisitor());
        
        FileSystemItem video1 = new VideoFile("sample.mp4");
        video1.accept(new CompressVisitor());
    }
}
