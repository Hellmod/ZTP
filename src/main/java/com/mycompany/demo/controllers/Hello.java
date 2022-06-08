package com.mycompany.demo.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/auth")
    public String sayAuth() {
        return "hello";
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/login")
    public String sayLogin() {
        return "hello";
    }
}
