package com.order.OrderNotificationApp.Requests;

import java.util.List;

public class CompoundOrderRequest extends Request{
    private List<SimpleOrderRequest> simpleOrderRequest;

    public List<SimpleOrderRequest> getSimpleOrderRequest() {
        return simpleOrderRequest;
    }
}
