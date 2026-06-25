package com.project.storeapi.repositories;

import com.project.storeapi.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    List<User> findAllUsers();

    User findUserByEmail(String email);
}
