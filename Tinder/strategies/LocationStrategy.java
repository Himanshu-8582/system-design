package Tinder.strategies;

import java.util.List;

import Tinder.models.Location;
import Tinder.models.User;

public interface LocationStrategy {
    public List<User> findNearbyUsers(Location location, double maxDistance, List<User> allUsers);
}
