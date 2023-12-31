package com.order.OrderNotificationApp.model;

public abstract class NotificationTemplate {
    protected User user;
    protected int orderId;
    protected String content;
    protected String subject;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    NotificationTemplate(User user, int orderId){
        this.user = user;
        this.orderId = orderId;
    }

    public abstract void makeContent();
}
