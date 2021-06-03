package com.developing.test_feign.Dto;

public class RequestDto {

    private int id;

    private String customer;

    private int quantity;

    private double price;

    public RequestDto() {
    }

    public RequestDto(int id, String customer, int quantity, double price) {
        this.id = id;
        this.customer = customer;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
