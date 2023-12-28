package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.User;
import com.order.OrderNotificationApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepo;

    public Boolean add(User user){
        return userRepo.add(user);
    }
    public Boolean authenticate(User user){
        return userRepo.checkUserCredentials(user);
    }
}
