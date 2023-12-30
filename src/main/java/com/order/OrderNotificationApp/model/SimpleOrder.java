package com.order.OrderNotificationApp.model;

import java.util.ArrayList;
import java.util.List;

public class SimpleOrder extends Order{
    private List<Product> productList;
    private String location;
    private Double price;
    public SimpleOrder(){
        productList = new ArrayList<>();
    }

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
        stringBuilder.append("Order Id: ").append(this.getOrderID()).append('\n');
        stringBuilder.append("Created At ").append(createdAt).append(" ").append("ShippedAt ").append(shippedAt).append('\n');
        for(Product p: productList){
            stringBuilder.append(p.toString()).append('\n');
        }
        return stringBuilder.toString();
    }

    @Override
    public Boolean shipOrder() {
        return null;
    }
}
