package com.example.demo.controller;
import com.example.demo.model.Shops;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ShopController {
    @Autowired // field injection , linking this class with ShopService
    ShopService service;

    @GetMapping("/shops")
    public Object getshops(@RequestParam(required = false) String shopname,@RequestParam(required = false) Integer shopid){
        if (shopid == null && shopname== null) {
            return service.getAllShops();
        }
        else if(shopid==null && shopname != null){
            return service.getShopByName(shopname);
        }
        else {
            return service.getShopById(shopid);
        }
    }

    @PostMapping("/addshop")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity addShop(@RequestBody Shops shop) {
        try {
            service.addShop(shop);
            return ResponseEntity.ok("✅ Shop added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ ERROR: " + e.getMessage());
        }
    }
    @DeleteMapping("/shop/{shopid}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void deleteshop(@PathVariable int shopid){
        service.deleteshop(shopid);
    }
    @PutMapping("/shops/{shopid}")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity updateshop(@PathVariable int  shopid, @RequestBody Shops shops){
        try{
        service.updateshop(shopid,shops);

            return ResponseEntity.ok("✅ Shop updated successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ ERROR: " + e.getMessage());
        }
    }
    @GetMapping("/shops/my")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public List<Shops> myshops(){
        return service.myshop();
    }
}