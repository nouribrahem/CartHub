package com.order.OrderNotificationApp.model;

import com.order.OrderNotificationApp.model.Enums.ChannelType;
import com.order.OrderNotificationApp.model.Enums.Language;

public class ShippingNotification extends NotificationTemplate{
    public ShippingNotification(String username, int orderId) {
        super(username, orderId);
        this.content = "Dear xxx , " +
                "Your orderId # ooo is successfully shipped! "+
                "Thanks for using our store :)";
        this.subject = "Order shipment Confirmed!";
        this.channelType = ChannelType.EMAIL;
        this.language = Language.ENGLISH;
        makeContent();
    }
    @Override
    public void makeContent() {
        this.content = this.content.replace("xxx", username);
        this.content = this.content.replace("ooo", Integer.toString(orderId));
    }
}
