package com.order.OrderNotificationApp.model;


import com.order.OrderNotificationApp.model.Enums.ChannelType;
import com.order.OrderNotificationApp.model.Enums.Language;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class Account {
    @NotNull
    @NotBlank(message = "Balance is mandatory")
    @Pattern(regexp = "\\d+", message = "Balance must be numeric")
    private Double balance;
    @NotNull
    @NotBlank(message = "Account Number is mandatory")
    @Pattern(regexp = "\\d+", message = "AccountNumber must be numeric")
    private String accountNumber;
    @NotNull
    @NotBlank(message = "Language is mandatory")
    @Enumerated(EnumType.STRING)
    private Language accountLanguage;
    @NotNull
    @NotBlank(message = "Channel type is mandatory")
    @Enumerated(EnumType.STRING)
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

    @Override
    public String toString() {
        return "Account{" +
                "balance=" + balance +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountLanguage=" + accountLanguage +
                ", channelType=" + channelType +
                '}';
    }

}
