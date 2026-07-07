package com.project.storeapi.controllers;

import com.project.storeapi.entities.User;
import com.project.storeapi.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAllWithAddressesProjection());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }
}
