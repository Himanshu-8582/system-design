import java.util.ArrayList;
import java.util.List;

public class CompositePattern {

    public interface FileSystemItem {
        public void ls(int indent);

        public void openAll(int indent);

        public int getSize();

        public FileSystemItem cd(String name);

        public String getName();

        public boolean isFolder();
    }

    public static class File implements FileSystemItem {                 // Leaf
        private String name;
        private int size;

        public File(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public void ls(int indent) {
            System.out.println(" ".repeat(indent) + name);
        }

        @Override
        public void openAll(int indent) {
            System.out.println(" ".repeat(indent) + name);
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public FileSystemItem cd(String name) {
            return null; // Files cannot contain other items
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isFolder() {
            return false;
        }
    }

    public static class Folder implements FileSystemItem {              // Composite
        private String name;
        private List<FileSystemItem> items;

        public Folder(String name) {
            this.name = name;
            this.items = new ArrayList<>();
        }

        public void addItem(FileSystemItem item) {
            items.add(item);
        }

        @Override
        public void ls(int indent) {
            for (FileSystemItem item : items) {
                if(item.isFolder()) {
                    System.out.println(" ".repeat(indent)+"+ " + item.getName());
                } else {
                    System.out.println(" ".repeat(indent) + item.getName());
                }
            }
        }

        @Override
        public void openAll(int indent) {
            System.out.println(" ".repeat(indent) + "+ " + name);
            for (FileSystemItem item : items) {
                item.openAll(indent + 4);
            }
        }

        @Override
        public int getSize() {
            int totalSize = 0;
            for (FileSystemItem item : items) {
                totalSize += item.getSize();
            }
            return totalSize;
        }

        @Override
        public FileSystemItem cd(String name) {
            for (FileSystemItem item : items) {
                if (item.getName().equals(name) && item.isFolder()) {
                    return item;
                }
            }
            return null; // Folder not found
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isFolder() {
            return true;
        }
    }
    public static void main(String[] args) {
        Folder root = new Folder("root");
        root.addItem(new File("file1.txt", 1));
        root.addItem(new File("file2.txt", 1));

        Folder docs = new Folder("docs");
        docs.addItem(new File("doc1.pdf", 2));
        docs.addItem(new File("doc2.pdf", 3));
        root.addItem(docs);

        Folder images = new Folder("images");
        images.addItem(new File("img1.png", 5));
        images.addItem(new File("img2.jpg", 4));
        root.addItem(images);


        // root.openAll(0);
        // root.ls(0);
        // docs.ls(0);


        FileSystemItem cwd = root.cd("docs");
        if(cwd != null) {
            cwd.ls(0);
        } else {
            System.out.println("Folder not found");
        }

        System.out.println("Total size of root folder: " + root.getSize() + " MB");
    }
}
