package com.project.storeapi.mappers;

import com.project.storeapi.dtos.CartDto;
import com.project.storeapi.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartDto toDto(Cart cart);
}
