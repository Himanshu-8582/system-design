package models;

import java.util.ArrayList;

public class Cart {
    private Restaurant r;
    private ArrayList<MenuItem> items=new ArrayList<>();

    public Cart() {
        r = null;
    }

    public void addItem(MenuItem item) {
        if (r == null) {
            System.out.println("Cart: Set a restaurant before adding items.");
            return;
        }
        items.add(item);
    }

    public double getTotalCost() {
        double sum = 0;
        for (MenuItem i : items) {
            sum += i.getPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return (r == null || items.isEmpty());
    }
    
    public void clear() {
        items.clear();
        r = null;
    }

    // Getters and Setters

    public void setRestaurant(Restaurant r) {
        if (this.r != r) {
            items.clear(); // reset the cart for new restaurant
        }
        this.r = r;
    }

    public Restaurant getRestaurant() {
        return r;
    }
    
    public ArrayList<MenuItem> getItems() {
        return items;
    }
    
}
