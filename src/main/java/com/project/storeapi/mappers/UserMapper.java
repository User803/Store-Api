package com.project.storeapi.mappers;

import com.project.storeapi.dtos.RegisterUserRequest;
import com.project.storeapi.dtos.UpdateUserRequest;
import com.project.storeapi.dtos.UserDto;
import com.project.storeapi.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(RegisterUserRequest request);
    void updateEntity(UpdateUserRequest request, @MappingTarget User user);
}
