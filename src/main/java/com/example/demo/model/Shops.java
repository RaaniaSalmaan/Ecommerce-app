package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Shops {
    @Id
    private int shopId;
    private String shopName;
    @ManyToOne(optional = false)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @OneToMany(mappedBy= "shop" ,cascade = CascadeType.ALL)
    private List<Products> products;

    public Shops(String name, int shopid, List<Products> productList,User seller) {
       this.shopName = name;
        this.shopId=shopid;
        this.products = productList;
        this.seller = seller;
   }
    public Shops() {
    }
    public int getShopid() {
        return shopId;
    }
    public void setShopid(int shop_id) {
        this.shopId = shop_id;
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

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}

