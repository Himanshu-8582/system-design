package Spotify.strategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import Spotify.models.Playlist;
import Spotify.models.Song;


public class RandomPlayStrategy implements PlayStrategy {

    private Playlist currentPlaylist;
    private List<Song> remainingSongs;
    private Stack<Song> history;
    private Random random;

    public RandomPlayStrategy() {
        this.currentPlaylist = null;
        this.random = new Random();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        this.currentPlaylist = playlist;
        if (currentPlaylist == null || currentPlaylist.getSize() == 0)
            return;
        remainingSongs = new ArrayList<>(currentPlaylist.getSongs());
        history = new Stack<>();
    }

    @Override
    public Song next() {
        // Implementation for getting the next song in random order
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }
        if (remainingSongs.isEmpty()) {
            throw new RuntimeException("No more songs to play");
        }
        int idx = random.nextInt(remainingSongs.size());
        Song selectedSong = remainingSongs.get(idx);

        int lastIndex = remainingSongs.size() - 1;
        remainingSongs.set(idx, remainingSongs.get(lastIndex));
        remainingSongs.remove(lastIndex);
        history.push(selectedSong);
        return selectedSong;
    }

    @Override
    public boolean hasNext() {
        // Implementation to check if there is a next song in random order
        return currentPlaylist != null && !remainingSongs.isEmpty();
    }

    @Override
    public Song previous() {
        // Implementation for getting the previous song in random order
        if (history.isEmpty()) {
            throw new RuntimeException("No previous song available");
        }
        Song previousSong = history.pop();
        return previousSong;
    }

    @Override
    public boolean hasPrevious() {
        // Implementation to check if there is a previous song in random order
        return history.size() > 0;
    }
    
}
