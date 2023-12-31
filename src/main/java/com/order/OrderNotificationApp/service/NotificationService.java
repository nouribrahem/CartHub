package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.*;
import com.order.OrderNotificationApp.repository.NotificationRepository;
import com.order.OrderNotificationApp.repository.OrderRepository;
import com.order.OrderNotificationApp.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final Queue<NotificationTemplate> notificationQueue;
    public NotificationService(OrderRepository orderRepository,UserRepository userRepository){
        this.orderRepository = orderRepository;
        notificationQueue = new LinkedList<>();
        notificationRepository = new NotificationRepository();
        this.userRepository = userRepository;
    }
    public NotificationTemplate notifySimpleOrderPlaced(String username, int orderId) {
        Order order = (Order) orderRepository.getByID(orderId);
        OrderPlacementNotification notification = new OrderPlacementNotification(
                (User)userRepository.getByID(username),orderId,((SimpleOrder)order).getProductList());

        notificationQueue.add(notification);
        return notification;
    }
    public NotificationTemplate notifySimpleOrderShipped(String username, int orderId) {
        Order order = (Order) orderRepository.getByID(orderId);
        ShippingNotification notification = new ShippingNotification((User)userRepository.getByID(username),orderId);
        notificationQueue.add(notification);
        return notification;
    }

    public String listNotifications() {
        int size = notificationQueue.size();
        StringBuilder notifications = new StringBuilder();
        for(int i =0; i < size;i++){
            NotificationTemplate not = notificationQueue.poll();
            notifications.append(not.getContent());
            notifications.append("|||");
            notificationQueue.add(not);
        }
        return notifications.toString();
    }

    public NotificationTemplate notifyCompoundOrderPlaced(String username, int orderId) {
        Order order = (Order) orderRepository.getByID(orderId);
        List<String>usernames = new ArrayList<>();
        for(Order o:((CompoundOrder)order).getOrders()){
            String user = orderRepository.getUserByOrderID(((SimpleOrder)o).getOrderID());
            usernames.add(user);
            OrderPlacementNotification notification = new OrderPlacementNotification(
                    (User)userRepository.getByID(user),((SimpleOrder)o).getOrderID(),((SimpleOrder)o).getProductList());
            notificationQueue.add(notification);
        }
        return new CompoundOrderPlacementNotification((User)userRepository.getByID(username),orderId,usernames);
    }
    public NotificationTemplate notifyCompoundOrderShipped(String username, int orderId) {
        Order order = (Order) orderRepository.getByID(orderId);
        List<String>usernames = new ArrayList<>();
        for(Order o:((CompoundOrder)order).getOrders()){
            String user = orderRepository.getUserByOrderID(((SimpleOrder)o).getOrderID());
            usernames.add(user);
            ShippingNotification notification = new ShippingNotification(
                    (User)userRepository.getByID(user),((SimpleOrder)o).getOrderID());
            notificationQueue.add(notification);
        }
        return new CompoundShippingNotification((User)userRepository.getByID(username),orderId,usernames);
    }
    public void removeNotification(){
        if(!notificationQueue.isEmpty()){
            notificationRepository.add(notificationQueue.poll());
        }
    }
    public String getLiveStat(){
        return notificationRepository.getLiveStat();
    }

    public List<NotificationTemplate> getSent() {
        return (List<NotificationTemplate>) notificationRepository.getAll();
    }
}
