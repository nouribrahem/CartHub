package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.User;
import com.order.OrderNotificationApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepo;

    public AuthenticationService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Boolean add(User user){
        return userRepo.add(user);
    }
    public Boolean authenticate(User user){
        return userRepo.checkUserCredentials(user);
    }
    public List<User> getAll(){
        return (List<User>) userRepo.getAll();
    }
}
