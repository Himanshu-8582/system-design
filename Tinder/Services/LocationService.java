package Tinder.Services;

import java.util.List;

import Tinder.models.Location;
import Tinder.models.User;
import Tinder.strategies.BasicLocationStrategy;
import Tinder.strategies.LocationStrategy;

public class LocationService {
     private LocationStrategy strategy;

    // Singleton Pattern
    private static LocationService instance;

    private LocationService() {
        strategy = new BasicLocationStrategy();
    }

    public static LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public void setStrategy(LocationStrategy newStrategy) {
        strategy = newStrategy;
    }

    public List<User> findNearbyUsers(Location location, double maxDistance, List<User> allUsers) {
        return strategy.findNearbyUsers(location, maxDistance, allUsers);
    }
}
