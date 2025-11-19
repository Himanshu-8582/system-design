package managers;

import java.util.ArrayList;

import models.Order;

public class OrderManager {
    private static ArrayList<Order> orderLists = new ArrayList<>();
    private static OrderManager instance = null;

    public static OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addOrderList(Order o) {
        orderLists.add(o);
    }
    
    public void listOrders() {
        System.out.println("---All Orders---");
        for (Order o : orderLists) {
            System.out.println(o.getType() + " order for " + o.getUser().getName());
            System.out.println("Total: "+ o.getTotal());
            System.out.println("All "+ o.getSchedule());
        }
    }
}
