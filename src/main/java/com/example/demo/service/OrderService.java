package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
@Autowired
private ProductsRepository productsRepository;
@Autowired
private OrderRepository orderRepository;
@Autowired
private UserRepository userRepository;

    public void orderRequest(OrderRequest request) {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        User customer = userRepository.findByUsername(name);

        Order order = new Order();
        order.setCustomer(customer);

        List<OrderItem> orderItems = new ArrayList<>();

        for (int i = 0; i < request.getProductIds().size(); i++) {
            int productId = request.getProductIds().get(i);
            int qty = request.getQuantities().get(i);

            Products product = productsRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getQuantity() < qty) {
                throw new RuntimeException("Insufficient stock of: " + product.getName());
            }

            product.setQuantity(product.getQuantity() - qty);
            productsRepository.save(product);

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(qty);
            item.setOrder(order);
            orderItems.add(item);
        }
        order.setOrderItems(orderItems);
        orderRepository.save(order);


    }
    public List<OrderResponse> myOrders() {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        User customer = userRepository.findByUsername(name);
         List<Order> orders = orderRepository.findByCustomer(customer);
        List<OrderResponse> response = new ArrayList<>();
        for (Order order : orders) {
            List<String> product_info = new ArrayList<>();
            int totalPrice=0;
            for (OrderItem item : order.getOrderItems()) {
                String info = item.getProduct().getName() + " Price " + item.getProduct().getPrice() + ",Quantity " + item.getQuantity();
                product_info.add(info);
                totalPrice += item.getProduct().getPrice() * item.getQuantity();
            }
            response.add(new OrderResponse(product_info, totalPrice));
        }

        return response;

        }
}
