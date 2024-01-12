package com.example.glovo.testServices;
import com.example.glovo.dto.Order;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.services.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Test
    public void testGet (){
        OrderService orderService = new OrderService(orderRepository);
        Order already = Order.builder().id(123L).build();
        when(orderRepository.findById((any()))).thenReturn(Optional.ofNullable(already));
        Order order = orderService.get(123L);
        assertEquals(order,already);
    }
    @Test
    public void testGetOnNull (){
        OrderService orderService = new OrderService(orderRepository);
        when(orderRepository.findById((any()))).thenReturn(Optional.empty());
        Order order = orderService.get(123L);
        assertNull(order);
    }
    @Test
    public void testCreate (){
        OrderService orderService = new OrderService(orderRepository);
        Order whichCreate = Order.builder().id(123L).build();
        Order saved = Order.builder().id(123L).build();
        when(orderRepository.save(whichCreate)).thenReturn(saved);

        Long idCreated = orderService.create(whichCreate);
        assertEquals( saved.getId(), idCreated);
    }
    @Test
    void testCreateOnNull() {
        OrderService orderService = new OrderService(orderRepository);
        when(orderRepository.save(any())).thenReturn(null);
        Long idCreated = orderService.create(null);
        assertNull(idCreated);
    }

    @Test
    void testUpdate() {
        OrderService orderService = new OrderService(orderRepository);
        Order already = Order.builder().id(123L).build();
        Order updated = new Order();
        when(orderRepository.existsById(already.getId())).thenReturn(true);
        orderService.update(already.getId(), updated);
        assertEquals(already.getId(), updated.getId());
    }
    @Test
    void testUpdateNotExist() {
        OrderService orderService = new OrderService(orderRepository);
        Order already = Order.builder().id(123L).build();
        Order updated = new Order();
        when(orderRepository.existsById(already.getId())).thenReturn(false);
        orderService.update(already.getId(), updated);
        assertEquals(false, orderRepository.save(updated));
    }
    @Test
    void testDeleteOrder() {
        OrderService orderService = new OrderService(orderRepository);
        Order already = Order.builder().id(123L).build();
        when(orderRepository.existsById(already.getId())).thenReturn(true);
        orderService.delete(already.getId());
        assertFalse(orderRepository.existsById(already.getId()));
    }









}
