package com.example.springbootkeycloak.controller;

public class IntrospectionResponse {
    private boolean active;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
