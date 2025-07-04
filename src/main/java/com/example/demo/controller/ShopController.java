package com.example.demo.controller;
import com.example.demo.exceptions.priceFormatException;
import com.example.demo.model.Products;
import com.example.demo.exceptions.InvalidCategoryException;
import com.example.demo.model.Shops;
import com.example.demo.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity addShop(@RequestBody Shops shop) {
        try {
            service.addShop(shop);
            return ResponseEntity.ok("✅ Shop added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ ERROR: " + e.getMessage());
        }
    }
    @DeleteMapping("/deleteshop")
    public void deleteshop(@RequestParam int shopid){
        service.deleteshop(shopid);
    }
 /*  @PostMapping("/{shopName}/add")
    public ResponseEntity<String> addProduct(@PathVariable String shopName, @RequestBody Products product) throws Exception {

        try {
            service.addProduct(shopName, product);

            return ResponseEntity.ok("✅ Product added successfully");
        }
        catch (InvalidCategoryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error: " + e.getMessage());
        }
        catch (RuntimeException ex){
             return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("❌ Error: " + ex.getMessage());
        }
    }
    @PutMapping("/{shopName}/update")
    public void updateProduct(@PathVariable String shopName, @RequestBody Products product) {

         service.updateProduct(shopName, product);
    }
    @DeleteMapping("/{shopName}/delete")
    public void deleteProduct(@PathVariable String shopName, @RequestParam int id) {
        service.deleteProduct(shopName, id);
    }

  */
}