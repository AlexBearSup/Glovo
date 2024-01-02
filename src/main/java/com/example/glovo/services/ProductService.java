package com.example.glovo.services;

import com.example.glovo.dto.Order;
import com.example.glovo.dto.Product;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    public void addToOrder(Order order, Product product) {
        order.getProducts().add(product);
    }
    public void removeFromOrder(Order order, Integer productId) {
        order.getProducts().removeIf(product -> product.getId().equals(productId));
    }
}
