package com.example.glovo.testServices;
import com.example.glovo.dto.Order;
import com.example.glovo.dto.Product;
import com.example.glovo.repository.ProductRepository;
import com.example.glovo.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Test
    void testAddToOrder() {
        ProductService productService = new ProductService(productRepository);
        Order order = Order.builder().id(123L).build();
        Product productAdd = Product.builder().id(321L).name("burger").order(order).build();
        when(productRepository.save(any())).thenReturn(productAdd);
        productService.addToOrder(order, productAdd);
        assertEquals(order, productAdd.getOrder());
    }
    @Test
    void testAddToOrderOnNull() {
        ProductService productService = new ProductService(productRepository);
        Order order = Order.builder().id(123L).build();
        Product productAdd = Product.builder().id(321L).name("burger").order(order).build();
        when(productRepository.save(any())).thenReturn(null);
        productService.addToOrder(order, productAdd);
        assertNull(productAdd.getOrder());
    }

    @Test
    void testRemoveFromOrder() {
        ProductService productService = new ProductService(productRepository);
        Order order = Order.builder().id(123L).build();
        Product productRem = Product.builder().id(321L).name("burger").order(order).build();
        productService.removeFromOrder(order, productRem.getId());
        assertNull(order.getProducts());

    }
}
