package com.order.OrderNotificationApp.model;

public class ShippingNotification extends NotificationTemplate{

    ShippingNotification(String username, int orderId) {
        super(username, orderId);
    }

    @Override
    public void makeContent() {

    }
}
