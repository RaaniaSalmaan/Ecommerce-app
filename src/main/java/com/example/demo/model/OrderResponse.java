package com.example.demo.model;

import java.util.List;

public class OrderResponse {
    private List<String> products;
    private int totalPrice;

    public OrderResponse(List<String> products, int totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public List<String> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}


