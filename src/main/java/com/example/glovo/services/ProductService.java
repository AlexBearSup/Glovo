package com.example.glovo.services;

import com.example.glovo.dto.Order;
import com.example.glovo.dto.Product;
import com.example.glovo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public void addToOrder(Order order, Product product) {
        product.setOrder(order);
        productRepository.save(product);
    }
    public void removeFromOrder(Order order, Long productId) {
        productRepository.deleteById(productId);
    }
}
