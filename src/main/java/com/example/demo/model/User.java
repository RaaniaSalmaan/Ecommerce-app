package com.example.demo.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @JsonIgnore
    private String phoneNumber;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String username;


    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    private List<Shops> shops;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Roles roles;


    public User(int id, String name, String phoneNumber, String password, String username, Roles roles,List<Shops> shops) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
        this.password = password;
        this.username = username;
        this.shops= shops;
    }

    public User() {
    }

    public List<Shops> getShops() {
        return shops;
    }

    public void setShops(List<Shops> shops) {
        this.shops = shops;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

}
