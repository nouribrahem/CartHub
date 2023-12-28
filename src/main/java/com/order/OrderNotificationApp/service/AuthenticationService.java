package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.User;
import com.order.OrderNotificationApp.repository.UserRepository;

public class AuthenticationService {
    private UserRepository userRepo;

    public Boolean addUser(User user){
        return true;
    }
    public Boolean authentication(User user){
        return true;
    }
}
