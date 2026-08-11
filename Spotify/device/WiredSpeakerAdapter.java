package Spotify.device;

import Spotify.external.WiredSpeakerAPI;
import Spotify.models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice {
    WiredSpeakerAPI wiredSpeakerAPI;

    public WiredSpeakerAdapter(WiredSpeakerAPI wiredSpeakerAPI) {
        this.wiredSpeakerAPI = wiredSpeakerAPI;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        wiredSpeakerAPI.playSoundViaCable(payload);
    }
    
}
