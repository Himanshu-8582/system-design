package managers;

import java.util.ArrayList;

import models.Restaurant;

public class RestaurantManager {
    private ArrayList<Restaurant> restaurants=new ArrayList<>();
    private static RestaurantManager instance=null;

    public static RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    public void addRestaurant(Restaurant r) {
        restaurants.add(r);
    }

    public ArrayList<Restaurant> searchByLocation(String location) {
        ArrayList<Restaurant> result = new ArrayList<>();
        location=location.toLowerCase();
        for (Restaurant r : restaurants) {
            String rl = r.getLocation();
            rl=rl.toLowerCase();
            if (rl.equals(location))
                result.add(r);
        }
        return result;
    }
}
