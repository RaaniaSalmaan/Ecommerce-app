package com.example.demo.service;

import com.example.demo.exceptions.InvalidCategoryException;
import com.example.demo.model.Products;
import com.example.demo.model.Shops;
import com.example.demo.repository.ProductsRepository;
import com.example.demo.repository.ShopsRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Add a new shop
    public void addShop(Shops shop) {
        shopRepository.save(shop);
    }
    public void deleteshop(int id){
        shopRepository.deleteById(id);
    }
    // Get all shops
    public List<Shops> getAllShops() {
        return (List<Shops>) shopRepository.findAll();
    }
    // Get shop by name
    public Shops getShopByName(String name) {
        List<Shops> s= (List<Shops>) shopRepository.findAll();
        for (Shops shop : s){
            if(shop.getName().contains(name))
                return shop;
        }
        return null;
    }

    // Get shop by ID
    public Optional<Shops> getShopById(int id) {
        s = shopRepository.findById(id);
        if (s != null) {
            return s;
        }
        else {
            return null;
        }
    }

  /* public void addProduct(String shopName, Products p) throws Exception {
        if (p.getCategory() == null) {
            throw new InvalidCategoryException("Category can't be null");

        }
        if (p.getPrice() <= 200) {
            throw new RuntimeException("Price must be greater than 200");
        }
        Shops s = getShopByName(shopName);
       productsRepository.save(p);


    }

    public void deleteProduct(String shopName, int productId) {
        Shops s = getShopByName(shopName);
        if (s != null){
            productsRepository.deleteById(productId);
        }
    }

    public void updateProduct(String shopName, Products updatedProduct) {
        Shops s = getShopByName(shopName);
        for (Products p : s.getProductList()) {
            if (p.getId() == updatedProduct.getId()) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
            }
        }
    }

*/
}

