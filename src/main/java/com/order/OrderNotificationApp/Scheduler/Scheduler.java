package com.order.OrderNotificationApp.Scheduler;

import com.order.OrderNotificationApp.service.NotificationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class Scheduler {

    private NotificationService notificationService;
    public Scheduler(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @Scheduled(cron = "0/50 * * ? * *")
    public void removeNotification(){
        notificationService.removeNotification();
    }
}
