package com.project.storeapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class StoreApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApiApplication.class, args);
//        UserService bean = context.getBean(UserService.class);
//
//        User user = new User("Java",
//                "java@email.com",
//                "123");
//
//        bean.registerUser(user);
//        bean.getAllUsers()
//                .forEach(System.out::println);
//
//        User user1 = bean.getUserByEmail("alex@email.com");
//        System.out.println(user1);
    }
}
