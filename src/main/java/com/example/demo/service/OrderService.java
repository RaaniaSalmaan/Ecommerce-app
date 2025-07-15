package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.model.OrderRequest;
import com.example.demo.model.Products;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
@Autowired
private ProductsRepository productsRepository;
@Autowired
private OrderRepository orderRepository;
@Autowired
private UserRepository userRepository;
    public Order orderRequest(OrderRequest orderRequest) {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        User customer = userRepository.findByUsername(name);

        Products product = productsRepository.findById(orderRequest.getProductId()).orElseThrow(() -> new RuntimeException("Product is not available"));
        if (product.getQuantity() < orderRequest.getQuantity()) {
                throw new RuntimeException("Product is out of stock");
        } else {
            product.setQuantity(product.getQuantity() - orderRequest.getQuantity());
            productsRepository.save(product);
            Order order = new Order();
            order.setProducts(List.of(product));
            order.setCustomer(customer);
            orderRepository.save(order);
            return order;
            }


    }
    public List<Order> myOrders() {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        User customer = userRepository.findByUsername(name);
        return orderRepository.findByCustomer(customer);
    }
}
