package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.User;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository implements BaseRepository {
    private final List<User> users;
    UserRepository(){
        users = new ArrayList<>();
    }

    public Boolean checkUserCredentials(User loggedUser) {
        for(User user : users){
            if(loggedUser.getUserName().equals(user.getUserName()) && loggedUser.getPassword().equals(user.getPassword()) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean add(Object o) {
        for(User user : users){
            if(((User) o).getUserName().equals(user.getUserName())){
                return false;
            }
        }
        users.add(((User) o));
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for(User user : users){
            if(((User) o).getUserName().equals(user.getUserName())){
                users.remove(((User) o));
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getByID(Object o) {
        for(User user : users){
            if(o.equals(user.getUserName())){
                return (User)user;
            }
        }
        return null;
    }

    @Override
    public Object getAll() {
        return users;
    }

}