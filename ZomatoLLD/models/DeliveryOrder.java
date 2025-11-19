package models;

public class DeliveryOrder extends Order {
    private String userAddress;

    public DeliveryOrder() {
        userAddress = "";
    }

    public String getType() {
        return "Delivery";
    }

    public String getUserAdderess() {
        return userAddress;
    }
    
    public void setUserAddress(String address) {
        this.userAddress = address;
    }
}
