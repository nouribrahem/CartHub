package com.order.OrderNotificationApp.Requests;

import java.util.List;
import java.util.Map;

public class SimpleOrderRequest {
    private List<Map.Entry<String,Integer>> productDetails;
    private String username;
    private String location;

    public List<Map.Entry<String, Integer>> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<Map.Entry<String, Integer>> productDetails) {
        this.productDetails = productDetails;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
