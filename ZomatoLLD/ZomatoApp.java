import java.util.ArrayList;

import factories.NowOrderFactory;
import factories.OrderFactory;
import factories.ScheduledOrderFactory;
import managers.OrderManager;

// Customer interact with this file

// It Breaks Single responsible principle

// It also breaks principle of least knowledge


import managers.RestaurantManager;
import models.Cart;
import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import services.NotificationService;
import strategies.PaymentStrategy;

public class ZomatoApp {
    public ZomatoApp() {
        initializeRestaurants();
    }

    public void initializeRestaurants() {
        Restaurant restaurant1 = new Restaurant("Bikaner", "Delhi");
        restaurant1.addMenuItem(new MenuItem("p1", "Chole Bhature", 120));
        restaurant1.addMenuItem(new MenuItem("p2", "Samosa", 15));

        Restaurant restaurant2 = new Restaurant("Haldiram", "Kolkata");
        restaurant2.addMenuItem(new MenuItem("p1", "Raj kachori", 80));
        restaurant2.addMenuItem(new MenuItem("p2", "Pav bhaji", 100));
        restaurant2.addMenuItem(new MenuItem("p3", "Dhokla", 30));

        Restaurant restaurant3 = new Restaurant("Saravana Bhavan", "Chennai");
        restaurant3.addMenuItem(new MenuItem("p1", "Masala Dhosa", 90));
        restaurant3.addMenuItem(new MenuItem("p2", "Idli Vada", 60));

        // Setting restaurant managers
        RestaurantManager restaurantManager = RestaurantManager.getInstance();
        restaurantManager.addRestaurant(restaurant1);
        restaurantManager.addRestaurant(restaurant2);
        restaurantManager.addRestaurant(restaurant3);
    }

    public ArrayList<Restaurant> searchRestaurants(String location) {
        return RestaurantManager.getInstance().searchByLocation(location);
    }

    public void selectRestaurant(User user, Restaurant restaurant) {
        if (user.getCart() == null) {
            user.setCart(new Cart());
        }
        Cart cart = user.getCart();
        cart.setRestaurant(restaurant);
    }

    public void addToCart(User user, String itemCode) {
        Restaurant restaurant = user.getCart().getRestaurant();
        if (restaurant == null) {
            System.out.println("Please select a restaurant first");
            return;
        }

        for (MenuItem item : restaurant.getMenu()) {
            if (item.getCode().equals(itemCode)) {
                user.getCart().addItem(item);
                break;
            }
        }
    }

    public Order checkOutNow(User user, String orderType, PaymentStrategy paymentStrategy) {
        return checkOut(user, orderType, paymentStrategy, new NowOrderFactory());
    }
    
    public Order checkOutSchedule(User user, String orderType, PaymentStrategy paymentStrategy, String scheduleTime) {
        return checkOut(user, orderType, paymentStrategy, new ScheduledOrderFactory(scheduleTime));
    }


    public Order checkOut(User user, String orderType, PaymentStrategy paymentStrategy, OrderFactory orderFactory) {
        if (user.getCart().isEmpty()) {
            return null;
        }

        Cart userCart = user.getCart();
        Restaurant orderRestaurant = userCart.getRestaurant();
        ArrayList<MenuItem> itemsOrdered = userCart.getItems();

        Order order = orderFactory.createOrder(user, userCart, orderRestaurant, itemsOrdered, paymentStrategy,
                orderType);
        OrderManager.getInstance().addOrderList(order);
        return order;
    }
    
    public void payForOrder(User user, Order order) {
        boolean isPaymentSuccess = order.processPayment();
        if (isPaymentSuccess) {
            NotificationService notification = new NotificationService();
            notification.notify(order);
            user.getCart().clear();
        }
    }

    public void printUserCart(User user) {
        System.out.println("--------------------------------------------");
        System.out.println("Items in cart: ");
        for (MenuItem item : user.getCart().getItems()) {
            System.out.println(" -> "+item.getCode() + " : " + item.getName() + " : Rs." + item.getPrice());
        }
        System.out.println("Grand Total: "+user.getCart().getTotalCost());
        System.out.println("--------------------------------------------");
    }
}


