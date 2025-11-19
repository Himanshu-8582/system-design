package factories;

import java.util.ArrayList;

import models.Cart;
import models.DeliveryOrder;
import models.MenuItem;
import models.Order;
import models.PickUpOrder;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;
import utils.TimeUtil;

public class NowOrderFactory extends OrderFactory {

    @Override
    public Order createOrder(User user,Cart cart,Restaurant restaurant,ArrayList<MenuItem> menuItems,PaymentStrategy paymentStrategy,String orderType){
        Order order = null;
        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
            PickUpOrder pickUpOrder = new PickUpOrder();
            pickUpOrder.setRestaurantAddress(restaurant.getLocation());
            order = pickUpOrder;
        }
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setSchedule(TimeUtil.getCurrentTime());
        order.setTotal(cart.getTotalCost());
        return order;
    }
}
