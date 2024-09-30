package com.tapiwa.nice.app.controllers;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapiwa.nice.app.models.Greeting;

@RestController
@RequestMapping("/greeting")
public class GreetingController {
    

    @GetMapping
    public ResponseEntity<Greeting> getGreeting(){

        var gretting = new Greeting("Hello There Buddy, How are you ", LocalDateTime.now());

        return  ResponseEntity.ok(gretting);
    }
}
