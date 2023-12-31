package com.order.OrderNotificationApp.model;

import java.util.List;

public class CompoundShippingNotification extends NotificationTemplate{
    private List<String> usernames;
    public CompoundShippingNotification(User user, int orderId,List<String> usernames) {
        super(user, orderId);
        this.content = "Dear xxx , " +
                "Your compound order with Id # ooo is successfully shipped and it's for: uuu .! "+
                "Thanks for using our store :)";
        this.subject = "Order shipment Confirmed!";
        this.usernames = usernames;
        makeContent();
    }
    @Override
    public void makeContent() {
        this.content = this.content.replace("xxx", user.getUserName());
        this.content = this.content.replace("ooo", Integer.toString(orderId));
        StringBuilder users = new StringBuilder();
        for(String user:usernames){
            users.append(user);
            users.append(" , ");
        }
        this.content = this.content.replace("uuu", users);
    }
}
