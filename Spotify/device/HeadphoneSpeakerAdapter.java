package Spotify.device;


import Spotify.external.HeadphoneSpeakerAPI;
import Spotify.models.Song;

public class HeadphoneSpeakerAdapter implements IAudioOutputDevice {
    HeadphoneSpeakerAPI headphoneSpeakerAPI;

    public HeadphoneSpeakerAdapter(HeadphoneSpeakerAPI headphoneSpeakerAPI) {
        this.headphoneSpeakerAPI = headphoneSpeakerAPI;
    }

    @Override
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        headphoneSpeakerAPI.playSoundViaHeadphone(payload);
    }
}
