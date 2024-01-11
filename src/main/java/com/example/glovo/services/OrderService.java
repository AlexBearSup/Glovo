package com.example.glovo.services;
import com.example.glovo.dto.Order;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    public Order get(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
    public Long create(Order order) {
        Order savedOrder = orderRepository.save(order);
        return savedOrder.getId();
    }
    public void update(Long orderId, Order updatedOrder) {
        if (orderRepository.existsById(orderId)) {
            updatedOrder.setId(orderId);
            orderRepository.save(updatedOrder);
        }
    }
    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
