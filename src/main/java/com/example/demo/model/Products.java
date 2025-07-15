package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
public class Products {

        @Id
        @GeneratedValue(strategy= GenerationType.AUTO)
        private int id;
        private String name;
        private int price;
        @Enumerated(EnumType.STRING)
        private Category category;
        private  int quantity;
        private int totalPrice;
        @ManyToOne
        @JoinColumn(name = "shop_id")// FK to Shops table
        @JsonIgnoreProperties({"productList","seller"})
        private Shops shop;

    public Shops getShop() {
        return shop;
    }

    public void setShop(Shops shop) {
        this.shop = shop;
    }

    public int getId() {

        return id;
    }

    public int getTotalPrice() {

        return this.price * this.quantity;
    }

    public void setId(int id) {

        this.id = id;
    }
    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {

          this.price = price;
        this.totalPrice= (this.price) * (this.quantity);
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Category getCategory() {

        return category;
    }
    public void setCategory(Category category) {

        this.category = category;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
        this.totalPrice= (this.price)* (this.quantity);
    }

    public Products() {
    }


    public Products(int id, String name, int price, Category category, int quantity) {
            this.price = price;
            this.name = name;
            this.id = id;
            this.quantity=quantity;
        this.category = category;
        this.totalPrice= price * quantity;
        }

}
