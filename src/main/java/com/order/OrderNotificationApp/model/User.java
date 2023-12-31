package com.order.OrderNotificationApp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public class User {
    @NotNull
    @NotBlank(message = "User is mandatory")
    private String userName;
    @NotNull
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotNull
    @NotBlank(message = "PhoneNumber is mandatory")
    @Pattern(regexp = "\\d+", message = "Phone must be numeric")
    private String phoneNumber;
    @NotNull
    @NotBlank(message = "Email is mandatory")
    private String email;
    @NotNull
    private Account account;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email= '" + email + "\'" +
                ", account= '" + account.toString() + "'" +
                '}';
    }
}
