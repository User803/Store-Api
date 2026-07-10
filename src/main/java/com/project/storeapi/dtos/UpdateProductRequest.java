package com.project.storeapi.dtos;

public record UpdateProductRequest(
        String name,
        String price,
        Byte categoryId) {
}
