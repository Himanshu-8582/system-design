package Spotify;

import Spotify.models.Song;
import java.util.ArrayList;
import java.util.List;
import Spotify.managers.PlaylistManager;
import Spotify.enums.PlayStrategyType;
import Spotify.enums.DeviceType;

public class SpotifyApp {
    private static SpotifyApp instance = null;
    private List<Song> songLibrary;

    private SpotifyApp() {
        this.songLibrary = new ArrayList<>();
    }

    public static synchronized SpotifyApp getInstance() {
        if (instance == null) {
            instance = new SpotifyApp();
        }
        return instance;
    }

    public void createSongsInLibrary(String title, String artist, String path) {
        Song song = new Song(title, artist, path);
        songLibrary.add(song);
    }

    public Song findSongByTitle(String title) {
        for (Song song : songLibrary) {
            if (song.getTitle().equals(title)) {
                return song;
            }
        }
        return null;
    }

    public void createPlaylist(String playlistName) {
        PlaylistManager.getInstance().createPlaylist(playlistName);
    }

    public void addSongToPlaylist(String playlistName, String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song with title \"" + songTitle + "\" not found in library");
        }
        PlaylistManager.getInstance().addSongToPlaylist(playlistName, song);
    }

    public void connectAudioDevice(DeviceType deviceType) {
        SpotifyFacade.getInstance().connectDevice(deviceType);
    }

    public void selectPlayStrategy(PlayStrategyType strategyType) {
        SpotifyFacade.getInstance().setPlayStrategy(strategyType);
    }

    public void loadPlaylist(String playlistName) {
        SpotifyFacade.getInstance().loadPlaylist(playlistName);
    }


    public void playSingleSong(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song with title \"" + songTitle + "\" not found in library");
        }
        SpotifyFacade.getInstance().playSong(song);
    }

    public void pauseCurrentSong(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song with title \"" + songTitle + "\" not found in library");
        }
        SpotifyFacade.getInstance().pauseSong(song);
    }

    public void playAllTracksInPlaylist() {
        SpotifyFacade.getInstance().playAllTracks();
    }

    public void playPreviousTrackInPlaylist() {
        SpotifyFacade.getInstance().playPreviousTrack();
    }


    public void queueSongNext(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song with title \"" + songTitle + "\" not found in library");
        }
        SpotifyFacade.getInstance().enqueueNext(song);
    }
}
