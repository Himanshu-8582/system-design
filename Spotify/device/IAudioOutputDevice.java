package Spotify.device;

import Spotify.models.Song;

public interface IAudioOutputDevice {
    public void playAudio(Song song);
}
