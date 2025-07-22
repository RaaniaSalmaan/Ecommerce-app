package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LoginUser {
      @Id
      @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

     private String username;

     private LocalDateTime loginTime;

     private LocalDateTime expirationTime;

    public LoginUser( int id,String username, LocalDateTime loginTime, LocalDateTime expirationTime) {
         this.id = id;
        this.username = username;
        this.loginTime = loginTime;
        this.expirationTime = expirationTime;
    }

    public LoginUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(LocalDateTime expirationTime) {
        this.expirationTime = expirationTime;
    }
}
