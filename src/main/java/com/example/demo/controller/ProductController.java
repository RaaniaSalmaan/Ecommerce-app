package com.example.demo.controller;
import com.example.demo.exceptions.InvalidCategoryException;
import com.example.demo.model.Products;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    @Autowired     // to link this class with ProductService class (field dependency injection)
    ProductService service;

    @GetMapping("/products")
    public Object getAllProducts() {
        return service.getAllProducts();
    }
    @GetMapping("/products/id/{id}")
    public Object getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @GetMapping("/products/name/{name}")
    public Object getProductByName(@PathVariable String name) {
        return service.getProductbyname(name);
    }
    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> addProduct(@RequestBody Products pro) throws Exception {
        try {
            service.addProduct(pro);

            return ResponseEntity.ok(" Product added successfully");
        } catch (InvalidCategoryException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@RequestParam int id){
        service.deleteProduct(id);
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateProduct(@RequestBody Products pro){
        service.updateProduct(pro);
    }

    //gives us list of products having price < pricelimit
   @GetMapping("/productbyprice")
   @PreAuthorize("hasRole('ROLE_USER')")
    public Object checkprice(@RequestParam int pricelimit){
       return  service.checkprice(pricelimit);
   }


}
