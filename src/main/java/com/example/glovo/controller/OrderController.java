package com.example.glovo.controller;
import com.example.glovo.dto.Order;
import com.example.glovo.dto.Product;
import com.example.glovo.services.OrderService;
import com.example.glovo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }
    @GetMapping("/{orderId}")
    public Order get(@PathVariable Long orderId) {
        return orderService.get(orderId);
    }
    @PostMapping
    public Long create(@RequestBody Order order) {
        return orderService.create(order);
    }
    @PutMapping("/{orderId}")
    public void update(@PathVariable Long orderId, @RequestBody Order updatedOrder) {
        orderService.update(orderId, updatedOrder);
    }
    @PatchMapping("/{orderId}/addProduct")
    public void addProduct(@PathVariable Long orderId, @RequestBody Product product) {
        productService.addToOrder(orderService.get(orderId), product);
    }
    @DeleteMapping("/{orderId}/removeProduct/{productId}")
    public void removeProduct(@PathVariable Order orderId, @PathVariable Long productId) {
        productService.removeFromOrder(orderService.get(orderId.getId()), productId);
    }
    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable Long orderId) {
        orderService.delete(orderId);
    }
}
