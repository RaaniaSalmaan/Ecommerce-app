package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {
    @RequestMapping("/")
    public String hello(){
        return "hello world";
    }

    @GetMapping("/hello")
    public String greet( @RequestParam String name) {
        return "Hello, " + name;
    }

    @RequestMapping("/about")
    public String about(){
        return "the about page";
    }

    @RequestMapping("login")
    public String login(){

        return "the login page";
    }

}
