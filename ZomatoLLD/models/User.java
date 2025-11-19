package models;

public class User {
    private int userId;
    private String name;
    private String address;
    private Cart cart;

    public User(int userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart c) {
        this.cart = c;
    }
}
