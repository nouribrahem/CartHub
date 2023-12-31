package com.order.OrderNotificationApp.model;

import com.order.OrderNotificationApp.model.Enums.ChannelType;
import com.order.OrderNotificationApp.model.Enums.Language;

import java.util.List;

public class CompoundOrderPlacementNotification extends NotificationTemplate {
    private List<String> usernames;
    public CompoundOrderPlacementNotification(String username, int orderId,List<String> usernames) {
        super(username, orderId);
        this.content = "Dear xxx , " +
                "Your  compound order is successfully placed! "+
                "Your compound orderId # ooo and it's for: uuu ." +
                "Thanks for using our store :)";
        this.subject = "Compound order placement Confirmed!";
        this.channelType = ChannelType.EMAIL;
        this.language = Language.ENGLISH;
        this.usernames = usernames;
        makeContent();
    }
    @Override
    public void makeContent() {
        this.content = this.content.replace("xxx", username);
        this.content = this.content.replace("ooo", Integer.toString(orderId));
        StringBuilder users = new StringBuilder();
        for(String user:usernames){
            users.append(user);
            users.append(" , ");
        }
        this.content = this.content.replace("uuu", users);
    }
}
