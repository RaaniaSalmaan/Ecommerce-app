package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.model.OrderRequest;
import com.example.demo.model.OrderResponse;
import com.example.demo.service.OrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderController {
    @Autowired
    OrderService service;
    @Autowired
    UserService userService;
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PostMapping("/orders")
    public ResponseEntity<String> orderRequest (@RequestBody OrderRequest order) throws Exception{
        userService.sessionCheck();
        try {
            service.orderRequest(order);
            return ResponseEntity.ok("Order placed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Error: " + e.getMessage());
        }
    }
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @GetMapping("/orders/my")
    public List<OrderResponse> myOrders (){
        userService.sessionCheck();
        return service.myOrders();
    }

}
