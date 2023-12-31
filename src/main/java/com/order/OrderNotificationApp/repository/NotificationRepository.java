package com.order.OrderNotificationApp.repository;

import com.order.OrderNotificationApp.model.Enums.ChannelType;
import com.order.OrderNotificationApp.model.NotificationTemplate;
import com.order.OrderNotificationApp.model.User;

import java.util.*;

public class NotificationRepository implements BaseRepository{
    private final List<NotificationTemplate> sentNotifications = new ArrayList<>();
    @Override
    public boolean add(Object o) {
        sentNotifications.add((NotificationTemplate) o);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!sentNotifications.isEmpty()){
            sentNotifications.remove((NotificationTemplate) o);
            return true;
        }
        return false;
    }

    @Override
    public Object getByID(Object o) {
        for(NotificationTemplate not : sentNotifications){
            if(not.getOrderId()==(int)o){
                return not;
            }
        }
        return null;
    }

    @Override
    public Object getAll() {
        return sentNotifications;
    }
    public String getLiveStat(){
        if(!sentNotifications.isEmpty()){
            StringBuilder stat = new StringBuilder();
            Map<User,Integer> usersCount = new HashMap<>();
            Map<NotificationTemplate,Integer> tempsCount = new HashMap<>();
            for(NotificationTemplate not:sentNotifications){
                int userCount;
                int tempCount;
                userCount = usersCount.getOrDefault(not.getUser(), 0);
                tempCount = tempsCount.getOrDefault(not, 0);
                usersCount.put(not.getUser(), userCount+1);
                tempsCount.put(not, tempCount+1);
            }
            User maxUser = Collections.max(usersCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            stat.append("Most sent notifications for ");
            if(maxUser.getAccount().getChannelType().equals(ChannelType.EMAIL)){
                stat.append("email ").append(maxUser.getEmail());
            }
            else{
                stat.append("phone ").append(maxUser.getPhoneNumber());
            }
            stat.append(" , ");
            NotificationTemplate maxTemp = Collections.max(tempsCount.entrySet(), Map.Entry.comparingByValue()).getKey();
            stat.append("Most sent template ");
            stat.append(maxTemp.getClass().getSimpleName());
            return stat.toString();
        }
        return "no notifications sent!";
    }
}
