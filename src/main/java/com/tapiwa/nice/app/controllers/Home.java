package com.tapiwa.nice.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class Home {


    @GetMapping
    public  ResponseEntity<String> getHello(){

        return  ResponseEntity.ok("Hello From home-server");
    }
    
}
