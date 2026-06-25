package com.project.storeapi.services;

import org.springframework.stereotype.Service;

@Service("sms")
public class SmsNotificationService implements INotificationService{

    @Override
    public void sendNotification(String message) {
        System.out.println("SMS Message : " + message);
    }
}
