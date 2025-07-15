package com.example.demo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String roleName;

   @OneToMany(mappedBy = "roles")
   private Set<User> users = new HashSet<>();

   public Roles() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Roles(String roleName, int id) {
        this.roleName = roleName;
        this.id = id;
    }
}

