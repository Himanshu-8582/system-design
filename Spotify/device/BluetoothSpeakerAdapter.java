package Spotify.device;

import Spotify.external.BluetoothSpeakerAPI;
import Spotify.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice {
    BluetoothSpeakerAPI bluetoothSpeakerAPI;

    public BluetoothSpeakerAdapter(BluetoothSpeakerAPI bluetoothSpeakerAPI) {
        this.bluetoothSpeakerAPI = bluetoothSpeakerAPI;
    }
    
    public void playAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        bluetoothSpeakerAPI.playSoundViaBluetooth(payload);
    }
}
