package com.example.glovo.services;
import com.example.glovo.dto.Order;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {
    private final Map<Integer, Order> orders = new HashMap<>();
    private int orderIdCounter = 1;
    public Order get(int orderId) {
        return orders.get(orderId);
    }
    public int create(Order order) {
        int orderId = orderIdCounter++;
        order.setId(orderId);
        orders.put(orderId, order);
        return orderId;
    }
    public void update(int orderId, Order updatedOrder) {
        if (orders.containsKey(orderId)) {
            orders.put(orderId, updatedOrder);
        }
    }
    public void delete(int orderId) {
        orders.remove(orderId);
    }
}
