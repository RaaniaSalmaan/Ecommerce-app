package com.example.demo.controller;

import com.example.demo.model.LoginUser;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService service;

    @PostMapping("/auth/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        try {
            service.register(user);

            return ResponseEntity.ok(" User Registered successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(" Error: " + e.getMessage());
        }
    }
   @PostMapping("/auth/login")
    public void login(){
        service.login();
   }
   @GetMapping("/users/me")
    public LoginUser getDetails (){
      service.sessionCheck();
        return service.getDetails();
   }
}
