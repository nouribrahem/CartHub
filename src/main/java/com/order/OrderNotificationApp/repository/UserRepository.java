package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.User;

import java.util.List;

public class UserRepository {
    private List<User> users;

    public User add(User newUser){
        return newUser;
    }
    public boolean update(String userName, Double deductedPrice){
        return true;
    }
}
