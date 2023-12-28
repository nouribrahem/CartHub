package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class UserRepository {
    private static List<User> users;
    UserRepository(){
        users = new ArrayList<>();
    }
    public boolean add(User newUser){
        for(User user : users){
            if(newUser.getUserName().equals(user.getUserName())){
                return false;
            }
        }
       users.add(newUser);
       return true;
    }
    public boolean update(String userName, Double deductedPrice){
        return true;
    }

    public Boolean checkUserCredentials(User loggedUser) {
        for(User user : users){
            if(loggedUser.getUserName().equals(user.getUserName()) &&loggedUser.getPassword().equals(user.getPassword()) ){
                return true;
            }
        }
        return false;
    }
}
