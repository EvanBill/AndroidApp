package com.example.zhang.mvp.model.bean;

public class ProductBean {
    private String name;
    private int price;

    public ProductBean(String name, int price) {
        this.name = name;
        this.price = price;
    }
    public ProductBean() {
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
