package com.project.storeapi.dtos;

public record RegisterUserRequest(
        String name,
        String email,
        String password) {
}
