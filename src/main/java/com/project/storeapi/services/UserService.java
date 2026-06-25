package com.project.storeapi.services;

import com.project.storeapi.model.User;
import com.project.storeapi.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final INotificationService notificationService;

    public UserService(UserRepository userRepository, INotificationService notificationService) {
        this.userRepository = userRepository;
        this.notificationService = notificationService;
    }

    public void registerUser(User user) {
       if (userRepository.findUserByEmail(user.getEmail()) != null) {
           throw new RuntimeException("Email already exists");
       }

        userRepository.save(user);
        log.info("User {} created successfully", user.getName());
        notificationService.sendNotification("Sent email to: " + user.getEmail());
    }

    public List<User> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
