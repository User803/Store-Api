package com.project.storeapi;

import com.project.storeapi.manager.NotificationManager;
import com.project.storeapi.manager.PaymentManager;
import com.project.storeapi.services.INotificationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApiApplication.class, args);
        PaymentManager bean = context.getBean(PaymentManager.class);
        bean.processPayment(100);
    }

}
