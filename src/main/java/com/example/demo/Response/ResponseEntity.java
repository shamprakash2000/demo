package com.example.demo.Response;

import com.example.demo.Entity.User;

public class ResponseEntity {
    String message;
    Object object;

    public ResponseEntity() {
    }

    public ResponseEntity(String message, Object object) {
        this.message = message;
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
