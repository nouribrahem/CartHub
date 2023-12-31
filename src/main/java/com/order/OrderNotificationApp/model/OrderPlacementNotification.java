package com.order.OrderNotificationApp.model;


import java.util.List;

public class OrderPlacementNotification extends NotificationTemplate {
   private List<Product> orderProducts;

    public OrderPlacementNotification(User user, int orderId, List<Product> products) {
        super(user, orderId);
        this.content = "Dear xxx , " +
                "Your order is successfully placed! "+
                "Your orderId # ooo and its products: ppp ." +
                "Thanks for using our store :)";
        this.subject = "Order placement Confirmed!";
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
        this.content = this.content.replace("xxx", user.getUserName());
        this.content = this.content.replace("ooo", Integer.toString(orderId));
        StringBuilder productNames = new StringBuilder();
        for(Product p: orderProducts){
            productNames.append(p.getName()).append(",");
        }
        this.content = this.content.replace("ppp", productNames.toString());
    }
}
