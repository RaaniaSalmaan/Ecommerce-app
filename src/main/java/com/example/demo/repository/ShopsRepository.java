package com.example.demo.repository;

import com.example.demo.model.Shops;
import com.example.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShopsRepository extends CrudRepository<Shops, Integer> {
    List<Shops> findBySeller(User seller);
}
