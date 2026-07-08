package com.project.storeapi.dtos;

import java.math.BigDecimal;

public record ProductDto(
        Long id,
        String name,
        BigDecimal price,
        Byte categoryId) {
}
