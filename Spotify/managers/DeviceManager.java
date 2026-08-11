package Spotify.managers;

import Spotify.device.IAudioOutputDevice;
import Spotify.enums.DeviceType;
import Spotify.factories.DeviceFactory;

public class DeviceManager {
    private static DeviceManager instance = null;
    private IAudioOutputDevice currentDevice;

    private DeviceManager() {
        currentDevice = null;
    }

    public static synchronized DeviceManager getInstance() {
        if (instance == null) {
            instance = new DeviceManager();
        }
        return instance;
    }

    public void connect(DeviceType deviceType) {
        if (currentDevice != null) {
        }
        currentDevice = DeviceFactory.createDevice(deviceType);

        switch (deviceType) {
            case BLUETOOTH:
                System.out.println("Bluetooth device connected");
                break;
            case WIRED:
                System.out.println("Wired device connected");
                break;
            default:
                System.out.println("Unknown device type");
                break;
        }
    }

    public IAudioOutputDevice getOutputDevice() {
        if (currentDevice == null) {
            throw new RuntimeException("No device connected");
        }
        return currentDevice;
    }

    public boolean hasOutputDevice() {
        return currentDevice != null;
    }
}
