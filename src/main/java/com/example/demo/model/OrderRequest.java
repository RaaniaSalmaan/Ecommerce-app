package com.example.demo.model;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class OrderRequest {
    private List<Integer> quantity;
    private List<Integer> productId;

    public OrderRequest() {
    }

    public OrderRequest(List<Integer> quantity, List<Integer> productId) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public List<Integer> getProductIds() {
        return productId;
    }

    public void setProductIds(List<Integer> productId) {
        this.productId = productId;
    }

    public List<Integer> getQuantities() {
        return quantity;
    }

    public void setQuantities(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
