package com.project.storeapi.dtos;

public record UpdateUserRequest(
        String name,
        String email) {
}
