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
    public Order get(@PathVariable Integer orderId) {
        return orderService.get(orderId);
    }
    @PostMapping
    public Integer create(@RequestBody Order order) {
        return orderService.create(order);
    }
    @PutMapping("/{orderId}")
    public void update(@PathVariable Integer orderId, @RequestBody Order updatedOrder) {
        orderService.update(orderId, updatedOrder);
    }
    @PatchMapping("/{orderId}/addProduct")
    public void addProduct(@PathVariable Integer orderId, @RequestBody Product product) {
        productService.addToOrder(orderService.get(orderId), product);
    }
    @DeleteMapping("/{orderId}/removeProduct/{productId}")
    public void removeProduct(@PathVariable Integer orderId, @PathVariable int productId) {
        productService.removeFromOrder(orderService.get(orderId), productId);
    }
    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable Integer orderId) {
        orderService.delete(orderId);
    }
}
