package com.project.storeapi;

import com.project.storeapi.entities.*;
import com.project.storeapi.repositories.CategoryRepository;
import com.project.storeapi.repositories.ProductRepository;
import com.project.storeapi.repositories.ProfileRepository;
import com.project.storeapi.repositories.UserRepository;
import com.project.storeapi.services.UserService;
import net.datafaker.Faker;
import net.datafaker.providers.base.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class StoreApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(StoreApiApplication.class, args);
        UserService bean = context.getBean(UserService.class);

        bean.findLoyalProfiles();
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

//    @Bean
//    CommandLineRunner runner(
//            UserRepository userRepository,
//            CategoryRepository categoryRepository,
//            ProductRepository productRepository,
//            ProfileRepository profileRepository) {
//
//        return args -> {
//            Faker faker = new Faker();
//            Name name = faker.name();
//            String firstName = name.firstName();
//            String lastName = name.lastName();
//
//            var user = new User(
//                    firstName + " " + lastName,
//                    firstName + "." + lastName + "@email.com",
//                    faker.credentials().password(8, 11)
//            );
//
//            var address = new Address(
//                    faker.address().streetAddress(),
//                    faker.address().cityName(),
//                    faker.address().state(),
//                    faker.address().zipCode()
//            );
//
//            Profile profile = new Profile(
//                    "Bio",
//                    faker.phoneNumber().phoneNumber(),
//                    faker.timeAndDate().birthday(18, 80),
//                    faker.number().numberBetween(1, 50)
//            );
//
//            var category = new Category("Category " + faker.number().numberBetween(1, 20));
//            var product = new Product(
//                    "Product " + faker.number().numberBetween(1, 10_000),
//                    BigDecimal.valueOf(faker.number().numberBetween(100, 10_000))
//            );
//
//            user.addAddress(address);
//            category.addProducts(product);
//
//            userRepository.save(user);
//            categoryRepository.save(category);
//            productRepository.save(product);
//            profileRepository.save(profile);
//        };
//    }

}
