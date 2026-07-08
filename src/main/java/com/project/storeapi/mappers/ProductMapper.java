package com.project.storeapi.mappers;

import com.project.storeapi.dtos.ProductDto;
import com.project.storeapi.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "categories.id")
    ProductDto toProductDto(Product product);
}
