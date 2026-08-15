package Tinder.strategies;

import java.util.ArrayList;
import java.util.List;

import Tinder.models.Location;
import Tinder.models.User;

public class BasicLocationStrategy implements LocationStrategy {
     public List<User> findNearbyUsers(Location location, double maxDistance, List<User> allUsers) {
        List<User> nearbyUsers = new ArrayList<>();
        for (User user : allUsers) {
            double distance = location.distanceInKm(user.getProfile().getLocation());
            if (distance <= maxDistance) {
                nearbyUsers.add(user);
            }
        }
        return nearbyUsers;
    }
}
