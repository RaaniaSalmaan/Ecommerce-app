package com.example.demo.repository;

import com.example.demo.model.Products;
import org.springframework.data.repository.CrudRepository;


public interface ProductsRepository extends CrudRepository<Products, Integer> {

}