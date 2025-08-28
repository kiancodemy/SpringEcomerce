package com.Ecommerce.main.service.order;

import com.Ecommerce.main.model.Order;

import java.util.List;

public interface OrderInterface {
    Order placeorder(Long userid);
    Order getOrderById(Long id);



    List<Order> getUserOrders(Long userid);
}
