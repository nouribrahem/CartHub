package com.order.OrderNotificationApp.service;

import com.order.OrderNotificationApp.model.NotificationTemplate;
import com.order.OrderNotificationApp.model.Order;
import com.order.OrderNotificationApp.model.OrderPlacementNotification;
import com.order.OrderNotificationApp.model.SimpleOrder;
import com.order.OrderNotificationApp.repository.OrderRepository;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
public class NotificationService {
    private final OrderRepository orderRepository;
    private final Queue<NotificationTemplate> notificationQueue;
    public NotificationService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
        notificationQueue = new LinkedList<>();
    }
    public NotificationTemplate notifySimpleOrderPlaced(String username, int orderId) {
        Order order = (Order) orderRepository.getByID(orderId);
        OrderPlacementNotification notification = new OrderPlacementNotification(
                username,orderId,((SimpleOrder)order).getProductList());

        notificationQueue.add(notification);
        return notification;
    }

    public Response listNotifications() {
        return null;
    }
}
