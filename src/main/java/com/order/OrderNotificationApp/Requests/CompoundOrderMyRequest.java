package com.order.OrderNotificationApp.Requests;

import java.util.List;

public class CompoundOrderMyRequest extends MyRequest {
    private List<SimpleOrderMyRequest> simpleOrderRequest;

    public void setSimpleOrderRequest(List<SimpleOrderMyRequest> simpleOrderRequest) {
        this.simpleOrderRequest = simpleOrderRequest;
    }

    public CompoundOrderMyRequest(){

    }

    public List<SimpleOrderMyRequest> getSimpleOrderRequest() {
        return simpleOrderRequest;
    }
}
