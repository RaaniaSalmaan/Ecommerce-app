package com.example.demo.controller;
import com.example.demo.exceptions.InvalidCategoryException;
import com.example.demo.model.Products;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired     // to link this class with ProductService class (field dependency injection)
    ProductService service;

    @GetMapping("/products")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public Object getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/products/id/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public Object getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/products/name/{name}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public Object getProductByName(@PathVariable String name) {
        return service.getProductbyname(name);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public ResponseEntity<String> addProduct(@RequestBody Products pro) throws Exception {
        try {
            service.addProduct(pro);

            return ResponseEntity.ok(" Product added successfully");
        } catch (InvalidCategoryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void deleteProduct(@RequestParam int id){
        service.deleteProduct(id);
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_SELLER')")
    public void updateProduct(@RequestBody Products pro){
        service.updateProduct(pro);
    }

    //gives us list of products having price < pricelimit
   @GetMapping("/productbyprice")
   @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public Object checkprice(@RequestParam int pricelimit){
       return  service.checkprice(pricelimit);
   }


}