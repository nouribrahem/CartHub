package com.order.OrderNotificationApp.model;

import java.util.List;

public class CompoundOrderPlacementNotification extends NotificationTemplate {
    private List<String> usernames;
    public CompoundOrderPlacementNotification(User user, int orderId,List<String> usernames) {
        super(user, orderId);
        this.content = "Dear xxx , " +
                "Your  compound order is successfully placed! "+
                "Your compound orderId # ooo and it's for: uuu ." +
                "Thanks for using our store :)";
        this.subject = "Compound order placement Confirmed!";
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
