package com.order.OrderNotificationApp.model;

public class Account {
    private Double balance;
    private String accountNumber;
    private Language accountLanguage;
    private ChannelType channelType;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Language getAccountLanguage() {
        return accountLanguage;
    }

    public void setAccountLanguage(Language accountLanguage) {
        this.accountLanguage = accountLanguage;
    }

    public ChannelType getChannelType() {
        return channelType;
    }

    public void setChannelType(ChannelType channelType) {
        this.channelType = channelType;
    }
}
