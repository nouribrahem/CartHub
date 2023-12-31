package com.order.OrderNotificationApp.model;


import com.order.OrderNotificationApp.model.Enums.ChannelType;
import com.order.OrderNotificationApp.model.Enums.Language;
import java.util.List;

public class OrderPlacementNotification extends NotificationTemplate {
   private List<Product> orderProducts;

    public OrderPlacementNotification(String username, int orderId, List<Product> products) {
        super(username, orderId);
        this.content = "Dear xxx , " +
                "Your order is successfully placed! "+
                "Your orderId # ooo and its products: ppp ." +
                "Thanks for using our store :)";
        this.subject = "Order placement Confirmed!";
        this.channelType = ChannelType.EMAIL;
        this.language = Language.ENGLISH;
        orderProducts = products;
        makeContent();
    }

    public List<Product> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<Product> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public void makeContent() {
        this.content = this.content.replace("xxx", username);
        this.content = this.content.replace("ooo", Integer.toString(orderId));
        StringBuilder productNames = new StringBuilder();
        for(Product p: orderProducts){
            productNames.append(p.getName()).append(",");
        }
        this.content = this.content.replace("ppp", productNames.toString());
    }
}
