package Spotify;

import Spotify.core.AudioEngine;
import Spotify.strategies.PlayStrategy;
import Spotify.models.Playlist;
import Spotify.managers.DeviceManager;
import Spotify.managers.StrategyManager;
import Spotify.enums.DeviceType;
import Spotify.enums.PlayStrategyType;
import Spotify.managers.PlaylistManager;
import Spotify.models.Song;
import Spotify.device.IAudioOutputDevice;

public class SpotifyFacade {
    private static SpotifyFacade instance = null;
    private AudioEngine audioEngine;
    private Playlist loadedPlaylist;
    private PlayStrategy playStrategy;

    private SpotifyFacade() {
        this.audioEngine = new AudioEngine();
        this.loadedPlaylist = null;
        this.playStrategy = null;
    }

    public static synchronized SpotifyFacade getInstance() {
        if (instance == null) {
            instance = new SpotifyFacade();
        }
        return instance;
    }

    public void connectDevice(DeviceType deviceType) {
        DeviceManager.getInstance().connect(deviceType);
    }
    
    public void setPlayStrategy(PlayStrategyType strategyType) {
        this.playStrategy = StrategyManager.getInstance().getStrategy(strategyType);
    }

    public void loadPlaylist(String playlistName) {
        this.loadedPlaylist = PlaylistManager.getInstance().getPlaylist(playlistName);
        if (playStrategy == null) {
            throw new RuntimeException("Play strategy not set");
        }
        playStrategy.setPlaylist(loadedPlaylist);
    }

    public void playSong(Song song) {
        if (!DeviceManager.getInstance().hasOutputDevice()) {
            throw new RuntimeException("No output device connected");
        }
        IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
        audioEngine.play(device, song);
    }

    public void pauseSong(Song song) {
        if (!audioEngine.getCurrentSongTitle().equals(song.getTitle())) {
            throw new RuntimeException("Cannot pause \"" + song.getTitle() + "\"; not currently playing song");
        }
        audioEngine.pause();
    }

    public void playAllTracks() {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        while (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.play(device, nextSong);
        }
        System.out.println("Finished playing all tracks in the playlist: " + loadedPlaylist.getPlaylistName());
    }

    public void playNextTrack() {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        if (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.play(device, nextSong);
        }else {
            System.out.println("No more tracks to play in the playlist: " + loadedPlaylist.getPlaylistName());
        }
        
    }

    public void playPreviousTrack() {
        if (loadedPlaylist == null) {
            throw new RuntimeException("No playlist loaded");
        }
        if (playStrategy.hasPrevious()) {
            Song previousSong = playStrategy.previous();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutputDevice();
            audioEngine.play(device, previousSong);
        } else {
            System.out.println("No previous tracks to play in the playlist: " + loadedPlaylist.getPlaylistName());
        }
    }
    
    public void enqueueNext(Song song) {
        playStrategy.addToNext(song);
    }
}
