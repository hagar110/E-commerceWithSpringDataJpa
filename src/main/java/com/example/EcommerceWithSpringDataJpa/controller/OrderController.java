package com.example.EcommerceWithSpringDataJpa.controller;
import com.example.EcommerceWithSpringDataJpa.entity.Customer;
import com.example.EcommerceWithSpringDataJpa.entity.Order;
import com.example.EcommerceWithSpringDataJpa.entity.OrderDetails;
import com.example.EcommerceWithSpringDataJpa.enums.OrderStatus;
import com.example.EcommerceWithSpringDataJpa.model.Response;
import com.example.EcommerceWithSpringDataJpa.repository.OrderRepository;
import com.example.EcommerceWithSpringDataJpa.service.address.AddressService;
import com.example.EcommerceWithSpringDataJpa.service.order.OrderService;
import com.example.EcommerceWithSpringDataJpa.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

//Omar: create order, get orders, view order details, cancel order, CheckOut, update status.

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final AddressService addressService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderService orderService, UserService userService, AddressService addressService,
                           OrderRepository orderRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.addressService = addressService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/details/{id}")
    public List<OrderDetails> getOrderDetails(@PathVariable("id") int orderId) {
        return orderService.getOrderDetails(orderId);
    }

    @GetMapping("/view")
    public ResponseEntity<List<Order>> getOrders(@RequestParam("id") int userId) {
       List<Order> orders= orderService.getOrders(userId);
//       if(orders.size()<=0){
//           throw new ResponseStatusException(HttpStatus.);
//       }
       return new ResponseEntity<>(orders,HttpStatus.OK);
    }

    @PostMapping("/placeOrder/{id}")
    public ResponseEntity<String> checkOut(@PathVariable("id") int id) {
        //id passed correctly
       if(orderService.checkOut(id))
           return new ResponseEntity<>("ok", HttpStatus.OK);
       return new ResponseEntity<>("Failed",HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cancel/{id}")
    public void cancelOrder(@PathVariable("id") int id) {
        orderService.deleteOrder(id);
    }
}