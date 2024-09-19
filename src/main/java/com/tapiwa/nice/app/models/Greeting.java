package com.tapiwa.nice.app.models;

import java.time.LocalDateTime;

public class Greeting {
    
    private String message;
    private LocalDateTime currentTime;


    public Greeting(String message, LocalDateTime currentTieme) {
        this.message = message;
        this.currentTime = currentTieme;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCurrentTieme() {
        return this.currentTime;
    }

    public void setCurrentTieme(LocalDateTime currentTieme) {
        this.currentTime = currentTieme;
    }

}

   
