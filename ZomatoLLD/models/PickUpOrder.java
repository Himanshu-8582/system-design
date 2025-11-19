package models;

public class PickUpOrder extends Order {
    private String restaurantAddress;

    public PickUpOrder() {
        restaurantAddress = "";
    }
    
    public String getType() {
        return "Pickup";
    }
    
    public void setRestaurantAddress(String address) {
        this.restaurantAddress = address;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }
}
