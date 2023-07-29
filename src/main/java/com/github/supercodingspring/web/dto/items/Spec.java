package com.github.supercodingspring.web.dto.items;

public class Spec {
    private String cpu;
    private String capacity;

    public Spec() {
    }

    public Spec(String cpu, String capacity) {
        this.cpu = cpu;
        this.capacity = capacity;
    }

    public String getCpu() {
        return cpu;
    }

    public String getCapacity() {
        return capacity;
    }
}
