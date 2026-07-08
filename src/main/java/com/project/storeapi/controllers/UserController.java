package com.project.storeapi.controllers;

import com.project.storeapi.dtos.UserDto;
import com.project.storeapi.entities.User;
import com.project.storeapi.mappers.UserMapper;
import com.project.storeapi.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserController(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort) {

        // If no sort is passed, it defaults to name
        if (!Set.of("name", "email").contains(sort)) {
            sort = "name";
        }

        return ResponseEntity.ok(
                userRepository.findAllWithAddressesProjection()
                        .stream()
                        .map(userMapper::toDto)
                        .toList()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
