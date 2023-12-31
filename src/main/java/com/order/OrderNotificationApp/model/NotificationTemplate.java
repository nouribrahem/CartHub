package com.order.OrderNotificationApp.model;

import com.order.OrderNotificationApp.model.Enums.ChannelType;
import com.order.OrderNotificationApp.model.Enums.Language;

public abstract class NotificationTemplate {
    protected String username;
    protected int orderId;
    protected Language language;
    protected ChannelType channelType;
    protected String content;
    protected String subject;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
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

    NotificationTemplate(String username, int orderId){
        this.username = username;
        this.orderId = orderId;
    }

    public abstract void makeContent();
}
