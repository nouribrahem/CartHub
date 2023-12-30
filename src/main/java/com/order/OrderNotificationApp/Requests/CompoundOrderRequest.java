package com.order.OrderNotificationApp.Requests;

import java.util.List;

public class CompoundOrderRequest {
    private String username;
    private List<SimpleOrderRequest> simpleOrderRequest;

    public String getUsername() {
        return username;
    }

    public List<SimpleOrderRequest> getSimpleOrderRequest() {
        return simpleOrderRequest;
    }
}
