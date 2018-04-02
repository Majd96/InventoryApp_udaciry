package com.majd.inventoryapp;

/**
 * Created by majd on 3/23/18.
 */

public class Product {
    private String name;
    private int quantity;
    private String price;
    private String suppiler;
    private String suppilerEmail;
    private String suppilerPhoneNo;


    public Product(String name, int quantity, String price, String suppiler, String suppilerEmail, String suppilerPhoneNo) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.suppiler = suppiler;
        this.suppilerEmail = suppilerEmail;
        this.suppilerPhoneNo = suppilerPhoneNo;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPrice() {
        return price;
    }

    public String getSuppiler() {
        return suppiler;
    }

    public String getSuppilerEmail() {
        return suppilerEmail;
    }

    public String getSuppilerPhoneNo() {
        return suppilerPhoneNo;
    }
}
