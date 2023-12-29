package com.order.OrderNotificationApp.model;

import java.util.List;

public class SimpleOrder extends Order{
    private List<Product> productList;
    private String location;
    private Double price;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String listOrderDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Product p: productList){
        }
        return null;
    }

    @Override
    public Boolean placeOrder() {
        return null;
    }

    @Override
    public Boolean shipOrder() {
        return null;
    }
}
