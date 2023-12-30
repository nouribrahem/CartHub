package com.order.OrderNotificationApp.model;

public abstract class Order {
    private String orderID;
    private Double shippingFee;
    private boolean isShipped = false;
    private Account account;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
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
    public abstract Boolean placeOrder();
    public abstract Boolean shipOrder();
}
