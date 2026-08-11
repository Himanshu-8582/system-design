package Spotify.models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private String playlistName;
    private List<Song> songList;

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.songList = new ArrayList<>();
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public List<Song> getSongs() {
        return songList;
    }

    public int getSize() {
        return songList.size();
    }

    public void addSongsToPlaylist(Song song) {
        if(song == null) {
            throw new IllegalArgumentException("Song cannot be null");
        }
        songList.add(song);
    }
}