package com.order.OrderNotificationApp.model;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Order {
    private int orderID =0;
    private Double shippingFee = 50.0;
    private boolean isShipped = false;
    private Account account;
    public LocalDateTime createdAt;
    public LocalDateTime  shippedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(LocalDateTime shippedAt) {
        this.shippedAt = shippedAt;
    }

    public  int getOrderID() {
        return orderID;
    }

    public void setOrderID(int lastID) {
        orderID = lastID+1;
    }

    public Double getShippingFee() {
        return shippingFee;
    }

    public void setShippingFee(Double shippingFee) {
        this.shippingFee = shippingFee;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public abstract String listOrderDetails();
    public abstract Boolean shipOrder();
}
