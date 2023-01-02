package com.example.EcommerceWithSpringDataJpa.service.order;

import com.example.EcommerceWithSpringDataJpa.entity.Customer;
import com.example.EcommerceWithSpringDataJpa.entity.Order;
import com.example.EcommerceWithSpringDataJpa.entity.OrderDetails;
import com.example.EcommerceWithSpringDataJpa.enums.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> getOrders(int userId);
    Optional<Order> getOrderById(int orderId);
    List<OrderDetails> getOrderDetails(int orderId);
    OrderStatus checkOrderStatus(int orderId);
    // Response<Boolean> cancelOrder(int orderId);
    void updateStatus(int orderId, OrderStatus status);
    boolean checkOut(int userId);
    void deleteOrder(int id);
}
