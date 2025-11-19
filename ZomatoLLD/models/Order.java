package models;

import java.util.ArrayList;

import strategies.PaymentStrategy;

public abstract class Order {
    protected static int nextOrderId;
    protected int orderId;
    protected User user;
    protected Restaurant restaurant;
    protected ArrayList<MenuItem> items = new ArrayList<>();
    protected PaymentStrategy paymentStrategy;
    protected double total;
    protected String schedule;

    public Order() {
        user = null;
        restaurant = null;
        total = 0.0;
        schedule = "";
        orderId = ++nextOrderId;
    }

    public boolean processPayment() {
        if (paymentStrategy != null) {
            System.out.println("Paying......................");
            paymentStrategy.pay(total);
            System.out.println();
            System.out.println();
            return true;
        } else {
            System.out.println("Please choose a payment mode first");
            return false;
        }
    }

    public abstract String getType();    // Abstract method


    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }

    public void setUser(User u) {
        this.user = u;
    }

    public User getUser() {
        return user;
    }

    public void setRestaurant(Restaurant res) {
        this.restaurant = res;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
    
    public void setItems(ArrayList<MenuItem> items) {
        this.items = items;
        total = 0;
        for (MenuItem mi : items) {
            total += mi.getPrice();
        }
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public void setPaymentStrategy(PaymentStrategy p) {
        this.paymentStrategy = p;
    }
}
