package com.order.OrderNotificationApp.model;

import java.util.ArrayList;
import java.util.List;

public class CompoundOrder extends Order{
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public CompoundOrder(){
        orders = new ArrayList<>();
    }
    @Override
    public String listOrderDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Order ID: ").append(getOrderID()).append("\n");
        stringBuilder.append("Created At ").append(createdAt).append(" ").append("ShippedAt ").append(shippedAt).append('\n');

        for (Order o: orders ){
            stringBuilder.append(o.listOrderDetails()).append(" ");
        }
        return stringBuilder.toString();
    }

    public Boolean addOrder(Order o) {
        orders.add(o);
        return true;
    }

    @Override
    public Boolean shipOrder() {
        return null;
    }
}
