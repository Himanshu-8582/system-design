package Spotify.strategies;

import Spotify.models.Playlist;
import Spotify.models.Song;

public class SequentialPlayStrategy implements PlayStrategy {
    private Playlist playlist;
    private int currentIndex;

    public SequentialPlayStrategy() {
        this.playlist = null;
        this.currentIndex = -1;
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
        this.currentIndex = -1;
    }

    @Override
    public Song next() {
        if (playlist == null || playlist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }
        currentIndex += 1;
        return playlist.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasNext() {
        return ((currentIndex + 1) < playlist.getSize());
    }

    @Override
    public Song previous() {
        if (playlist == null || playlist.getSize() == 0) {
            throw new RuntimeException("Playlist is empty or not set");
        }
        currentIndex -= 1;
        return playlist.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasPrevious() {
        return (currentIndex - 1) >= 0;
    }
    
}
