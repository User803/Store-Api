package com.project.storeapi.manager;

import com.project.storeapi.services.INotificationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class NotificationManager {
    private final INotificationService notificationService;

    public NotificationManager(@Qualifier("sms") INotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void sendNotification(String message){
        notificationService.sendNotification(message);
    }
}
