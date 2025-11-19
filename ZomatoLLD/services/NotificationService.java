package services;

import java.util.ArrayList;

import models.MenuItem;
import models.Order;

public class NotificationService {
    public static void notify(Order order) {
        System.out.println("--------------------------------------------");
        System.out.println("Notification now: " + order.getType() + " order placed");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Customer : " + order.getUser().getName());
        System.out.println("Restaurant: " + order.getRestaurant().getName());
        System.out.println("--------------------------------------------");
        System.out.println("Order delivered");

        ArrayList<MenuItem> items = order.getItems();
        for (MenuItem i : items) {
            System.out.println(" -> " + i.getName() + " (" + i.getPrice() + ")");
        }
        System.out.println("Total: " + order.getTotal());
        System.out.println("Schedule for: " + order.getSchedule());
        System.out.println("Payment Done.");
        System.out.println("--------------------------------------------");
    }
}