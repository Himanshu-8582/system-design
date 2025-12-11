import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class IteratorPattern {

    // Custom Iterable Interface
    public interface MyIterable<T> {
        public Iterator<T> getIterator();
    }

    // Iterator Interface
    public interface Iterator<T> {
        public boolean hasNext();

        public T next();
    }

    // ------------------ LINKED LIST ------------------------

    public static class LinkedList implements MyIterable<Integer> {
        int data;
        LinkedList next;

        LinkedList(int data) {
            this.data = data;
        }

        public void append(int value) {
            LinkedList curr = this;
            while (curr.next != null)
                curr = curr.next;
            curr.next = new LinkedList(value);
        }

        @Override
        public Iterator<Integer> getIterator() {
            return new LinkedListIterator(this);
        }
    }

    public static class LinkedListIterator implements Iterator<Integer> {
        private LinkedList curr;

        public LinkedListIterator(LinkedList head) {
            this.curr = head;
        }

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public Integer next() {
            int value = curr.data;
            curr = curr.next;
            return value;
        }
    }

    // ------------------ BINARY TREE ------------------------

    public static class BinaryTree implements MyIterable<Integer> {
        int data;
        BinaryTree left, right;

        public BinaryTree(int data) {
            this.data = data;
        }

        @Override
        public Iterator<Integer> getIterator() {
            return new BinaryTreeInorderIterator(this);
        }
    }

    public static class BinaryTreeInorderIterator implements Iterator<Integer> {
        Stack<BinaryTree> stack = new Stack<>();

        public BinaryTreeInorderIterator(BinaryTree root) {
            pushLeft(root);
        }

        private void pushLeft(BinaryTree node) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            BinaryTree node = stack.pop();
            int value = node.data;

            if (node.right != null)
                pushLeft(node.right);

            return value;
        }
    }

    // ------------------ PLAYLIST ------------------------

    public static class Song {
        String title, artist;

        public Song(String t, String a) {
            title = t;
            artist = a;
        }
    }

    public static class PlayList implements MyIterable<Song> {
        List<Song> songs = new ArrayList<>();

        public void addSong(Song s) {
            songs.add(s);
        }

        @Override
        public Iterator<Song> getIterator() {
            return new PlayListIterator(songs);
        }
    }

    public static class PlayListIterator implements Iterator<Song> {
        private List<Song> list;
        private int index = 0;

        public PlayListIterator(List<Song> list) {
            this.list = list;
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }

        @Override
        public Song next() {
            return list.get(index++);
        }
    }

    public static void main(String[] args) {

        // Linked List
        LinkedList list = new LinkedList(1);
        list.append(2);
        list.append(3);
        list.append(4);

        System.out.println("LinkedList:");
        Iterator<Integer> it1 = list.getIterator();
        while (it1.hasNext())
            System.out.print(it1.next() + " ");
        System.out.println("\n------------------");

        // Binary Tree
        BinaryTree root = new BinaryTree(1);
        root.left = new BinaryTree(2);
        root.right = new BinaryTree(3);

        System.out.println("Binary Tree Inorder:");
        Iterator<Integer> it2 = root.getIterator();
        while (it2.hasNext())
            System.out.print(it2.next() + " ");
        System.out.println("\n------------------");

        // Playlist
        PlayList pl = new PlayList();
        pl.addSong(new Song("Admire You", "Karan"));
        pl.addSong(new Song("Hello Hello", "Honey Paaji"));

        System.out.println("Playlist:");
        Iterator<Song> it3 = pl.getIterator();
        while (it3.hasNext()) {
            Song s = it3.next();
            System.out.println("-> "+s.title + " by " + s.artist);
        }
    }
}
