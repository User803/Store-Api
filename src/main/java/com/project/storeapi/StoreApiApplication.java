package com.project.storeapi;

import com.project.storeapi.entities.User;
import com.project.storeapi.repositories.UserRepository;
import net.datafaker.Faker;
import net.datafaker.providers.base.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

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

    @Bean
    CommandLineRunner runner(UserRepository userRepository) {
        return args -> {
            Faker faker = new Faker();
            Name name = faker.name();
            String firstName = name.firstName();
            String lastName = name.lastName();

            var user = new User(
                    firstName + " " + lastName,
                    firstName + "." + lastName + "@email.com",
                    faker.internet().password(8, 10)
            );

            userRepository.save(user);
        };
    }
}
