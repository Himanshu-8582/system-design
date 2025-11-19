package factories;

import java.util.ArrayList;

import models.Cart;
import models.MenuItem;
import models.Order;
import models.Restaurant;
import models.User;
import strategies.PaymentStrategy;

public abstract class OrderFactory {
    public abstract Order createOrder(
        User user,
        Cart cart,
        Restaurant restaurant,                                               
        ArrayList<MenuItem> menuItems,
        PaymentStrategy paymentStrategy,
        String type
    );
}
