package Spotify.strategies;

import Spotify.models.Playlist;
import Spotify.models.Song;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class CustomPlayStrategy implements PlayStrategy {
    private Playlist currentPlaylist;
    private int currentIndex;
    private Queue<Song> customQueue;
    private Stack<Song> previousStack;

    public CustomPlayStrategy() {
        this.currentPlaylist = null;
        this.currentIndex = -1;
        this.customQueue = new LinkedList<>();
        this.previousStack = new Stack<>();
    }

    private Song nextSequential() {
        if (currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }
        currentIndex += 1;
        return currentPlaylist.getSongs().get(currentIndex);
    }

    private Song previousSequential() {
        if (currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }
        currentIndex -= 1;
        return currentPlaylist.getSongs().get(currentIndex);
    }
    
    @Override
    public void setPlaylist(Playlist playlist) {
        this.currentPlaylist = playlist;
        this.currentIndex = -1;
        this.customQueue.clear();
        this.previousStack.clear();
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }
        
        if (!customQueue.isEmpty()) {
            Song s = customQueue.poll();
            previousStack.push(s);

            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                if (currentPlaylist.getSongs().get(i)==s) {
                    currentIndex = i;
                    break;
                }
            }
            return s;
        }
        
        return nextSequential();
    }

    @Override
    public boolean hasNext() {
        return ((currentIndex + 1) < currentPlaylist.getSize());
    }

    @Override
    public Song previous() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }

        if (!previousStack.isEmpty()) {
            Song s = previousStack.pop();

            for (int i = 0; i < currentPlaylist.getSongs().size(); i++) {
                if (currentPlaylist.getSongs().get(i)==s) {
                    currentIndex = i;
                    break;
                }
            }
            return s;
        }
        
        return previousSequential();
    }

    @Override
    public boolean hasPrevious() {
        // Implementation to check if there is a previous song in custom order
        return ((currentIndex - 1) >= 0);
    }

    public void addToNext(Song song) {
        if (song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }
        customQueue.add(song);
    }
    
}
