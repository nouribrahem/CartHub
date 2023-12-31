package com.order.OrderNotificationApp.model;

public class ShippingNotification extends NotificationTemplate{
    public ShippingNotification(User user, int orderId) {
        super(user, orderId);
        this.content = "Dear xxx , " +
                "Your orderId # ooo is successfully shipped! "+
                "Thanks for using our store :)";
        this.subject = "Order shipment Confirmed!";
        makeContent();
    }
    @Override
    public void makeContent() {
        this.content = this.content.replace("xxx", user.getUserName());
        this.content = this.content.replace("ooo", Integer.toString(orderId));
    }
}
