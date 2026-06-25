package com.project.storeapi.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service("email")
@Primary
public class EmailNotificationService implements INotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("Email Message : " + message);
    }
}
