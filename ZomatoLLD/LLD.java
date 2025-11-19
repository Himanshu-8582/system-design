import java.util.ArrayList;

import models.Order;

// Our main file

import models.Restaurant;
import models.User;
import strategies.UpiPaymentStrategy;


public class LLD {

    public static void main(String[] args) {

        // Create ZomatoApp facade
        ZomatoApp zomato = new ZomatoApp();

        // Simulate a user coming in (Happy Flow)
        User user = new User(101, "Aditya", "Delhi");
        System.out.println("User: " + user.getName() + " is active.");
        System.out.println();
        System.out.println();
        
        // User Searches for restaurants by location
        ArrayList<Restaurant> restaurantList = zomato.searchRestaurants("Delhi");
        if (restaurantList.isEmpty()) {
            System.out.println("No restaurant is found");
            return;
        }

        System.out.println("Found restaurant:-");
        for (Restaurant restaurant : restaurantList) {
            System.out.println(" -> "+restaurant.getName());
        }
        System.out.println("-----------------");
        System.out.println();
        System.out.println();

        // User Selects a restaurant
        zomato.selectRestaurant(user, restaurantList.get(0));

        System.out.println("Selected restaurant: " + restaurantList.get(0).getName());
        System.out.println();
        System.out.println();
        
        // User adds items to the cart
        zomato.addToCart(user, "p1");
        zomato.addToCart(user, "p2");

        zomato.printUserCart(user);
        System.out.println();
        System.out.println();

        // User Checkout the cart.
        Order order = zomato.checkOutNow(user, "Delivery", new UpiPaymentStrategy("1234567890"));

        // User pay for the cart. If payment is success, notification is sent.
        zomato.payForOrder(user, order);
    }
}
