package com.tapiwa.nice.app.models;

import java.time.LocalDateTime;

public class Greeting {
    
    private String message;
    private LocalDateTime currentTime;
    private SystemMetrics systemMetrics;


    public Greeting(String message, LocalDateTime currentTime, SystemMetrics systemMetrics) {
        this.message = message;
        this.currentTime = currentTime;
        this.systemMetrics = systemMetrics;
    }

    public SystemMetrics getSystemMetrics(){ return this.systemMetrics;}
    
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(LocalDateTime currentTime) {
        this.currentTime = currentTime;
    }

}

   
