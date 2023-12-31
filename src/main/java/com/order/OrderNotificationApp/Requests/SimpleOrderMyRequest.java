package com.order.OrderNotificationApp.Requests;

import java.util.List;
import java.util.Map;

public class SimpleOrderMyRequest extends MyRequest {
    private List<Map.Entry<String,Integer>> productDetails;
    private String location;
    public SimpleOrderMyRequest(){

    }

    public List<Map.Entry<String, Integer>> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<Map.Entry<String, Integer>> productDetails) {
        this.productDetails = productDetails;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
