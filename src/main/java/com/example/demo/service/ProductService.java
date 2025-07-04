package com.example.demo.service;

import com.example.demo.exceptions.InvalidCategoryException;
import com.example.demo.model.Category;
import com.example.demo.model.Products;
import com.example.demo.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Service
public class ProductService {

    @Autowired
    private ProductsRepository productsRepository;


    public Optional<Products> getProductById(int id) {
        return productsRepository.findById(id);
    }
    public List<Products> getAllProducts(){
        return (List<Products>) productsRepository.findAll();
        //return product;
    }
    public Products getProductbyname(String name){
        List<Products> products = (List<Products>) productsRepository.findAll();
        for (Products p : products) {
            if (p.getName().contains(name))
                return p;
        }
        return (Products) emptyList();
    }
    public void addProduct(Products pro) throws Exception {

        if (pro.getCategory() == null) {
            throw new InvalidCategoryException("Category can't be null");

        }
        productsRepository.save(pro);

    }

    public void deleteProduct(int id) {
       productsRepository.deleteById(id);
    }

    public void updateProduct(Products pro) {
        Optional<Products> existingProduct = productsRepository.findById(pro.getId());
        if (existingProduct.isPresent()) {
            Products p = existingProduct.get();
            p.setName(pro.getName());
            p.setPrice(pro.getPrice());
            p.setCategory(pro.getCategory());
            p.setQuantity(pro.getQuantity());
            productsRepository.save(p);
        }
    }

    public List<Products> checkprice(int pricelimit) {
        List<Products> pro = (List<Products>) productsRepository.findAll();
        List<Products> newlist = new ArrayList<>();
        for (Products p: pro){
            if (p.getPrice()< pricelimit)
                newlist.add(p);
        }
        return newlist;

    }
}