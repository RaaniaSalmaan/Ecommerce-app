package com.example.demo.model;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Shops {
    @Id
    private int shopId;

    private String shopName;

    @OneToMany(mappedBy= "shop" ,cascade = CascadeType.ALL)
    private List<Products> products;

    public int getShopid() {
        return shopId;
    }

    public void setShopid(int shop_id) {
        this.shopId = shop_id;
    }

    public Shops() {
    }

   public Shops(String name, int shopid, List<Products> productList) {
       this.shopName = name;
        this.shopId=shopid;
        this.products = productList;
   }
    public String getName() {

        return shopName;
        }
        public List<Products> getProductList() {

        return products;
        }

    public void setName(String name) {

        this.shopName = name;
    }

    public void setProductList(List<Products> productList) {

        this.products = productList;
    }
}

