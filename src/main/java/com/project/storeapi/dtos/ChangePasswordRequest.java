package com.project.storeapi.dtos;

public record ChangePasswordRequest(String oldPassword, String newPassword) {
}
