package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.model.NotificationTemplate;
import com.order.OrderNotificationApp.service.NotificationService;
import org.apache.coyote.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notifications")
public class NotificationController {
    private final NotificationService notificationService;
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @GetMapping("{username}/{orderId}")
    public NotificationTemplate notifySimpleOrderPlaced(@PathVariable String username, @PathVariable int orderId){
        return notificationService.notifySimpleOrderPlaced(username,orderId);
    }

    @GetMapping("list")
    public Response listNotificationsInQueue(){
        return notificationService.listNotifications();
    }

}
