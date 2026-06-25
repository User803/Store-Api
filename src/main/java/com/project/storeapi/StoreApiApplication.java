package com.project.storeapi;

import com.project.storeapi.manager.NotificationManager;
import com.project.storeapi.manager.PaymentManager;
import com.project.storeapi.model.User;
import com.project.storeapi.repositories.InMemoryUserRepository;
import com.project.storeapi.services.INotificationService;
import com.project.storeapi.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApiApplication.class, args);
        UserService bean = context.getBean(UserService.class);

        User user = new User("Java",
                "java@email.com",
                "123");

        bean.registerUser(user);
        bean.getAllUsers()
                .forEach(System.out::println);

        User user1 = bean.getUserByEmail("alex@email.com");
        System.out.println(user1);
    }

}
