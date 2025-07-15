package com.example.demo.service;

import com.example.demo.exceptions.InvalidCategoryException;
import com.example.demo.model.Products;
import com.example.demo.model.Shops;
import com.example.demo.model.User;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.ShopsRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    Optional<Shops> s;
    @Autowired
    private ShopsRepository shopRepository;
    @Autowired
    private ProductsRepository productsRepository;
     @Autowired
     private UserRepository userRepository;

    public void addShop(Shops shop) {
        shopRepository.save(shop);
    }

    public void deleteshop(int id){
        shopRepository.deleteById(id);
    }

    public List<Shops> getAllShops() {
        return (List<Shops>) shopRepository.findAll();
    }

    public Shops getShopByName(String name) {
        List<Shops> s= (List<Shops>) shopRepository.findAll();
        for (Shops shop : s){
            if(shop.getName().contains(name))
                return shop;
        }
        return null;
    }

    public Optional<Shops> getShopById(int id) {
        s = shopRepository.findById(id);
        if (s != null) {
            return s;
        }
        else {
            return null;
        }
    }

    public Shops updateshop(int shopid, Shops shops) {
        s = shopRepository.findById(shopid);

        if (s.isPresent()) {
            Shops shop = s.get();
            shop.setName(shops.getName());
            return shopRepository.save(shop);
        } else {
            throw new RuntimeException("Shop not found");
        }
    }

    public List<Shops> myshop() {
        Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        String name = authenticate.getName();
        User seller = userRepository.findByUsername(name);
        return shopRepository.findBySeller(seller);
    }


}

