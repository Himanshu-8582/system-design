package Spotify.factories;



import Spotify.device.BluetoothSpeakerAdapter;
import Spotify.device.HeadphoneSpeakerAdapter;
import Spotify.device.IAudioOutputDevice;
import Spotify.device.WiredSpeakerAdapter;
import Spotify.external.BluetoothSpeakerAPI;
import Spotify.external.HeadphoneSpeakerAPI;
import Spotify.external.WiredSpeakerAPI;
import Spotify.enums.DeviceType;


public class DeviceFactory {
    public static IAudioOutputDevice createDevice(DeviceType deviceType) {
        switch (deviceType) {
            case BLUETOOTH:
                return new BluetoothSpeakerAdapter(new BluetoothSpeakerAPI());
            case WIRED:
                return new WiredSpeakerAdapter(new WiredSpeakerAPI());
            default:
                return new HeadphoneSpeakerAdapter(new HeadphoneSpeakerAPI());
        }
    }
}