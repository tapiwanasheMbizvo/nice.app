package com.tapiwa.nice.app.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tapiwa.nice.app.models.SystemMetrics;

@RestController
@RequestMapping("/server/info")
public class SystemInfo {
    

    @GetMapping
    public  ResponseEntity<SystemMetrics> getSystemMetrics(){

        return  ResponseEntity.ok(new SystemMetrics());
    }
}
