package com.project.storeapi.mappers;

import com.project.storeapi.dtos.ProductDto;
import com.project.storeapi.dtos.UpdateProductRequest;
import com.project.storeapi.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryId", source = "categories.id")
    ProductDto toProductDto(Product product);
    Product toProduct(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    void updateProduct(UpdateProductRequest request, @MappingTarget Product product);
}
