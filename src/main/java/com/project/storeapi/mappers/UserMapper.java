package com.project.storeapi.mappers;

import com.project.storeapi.dtos.UserDto;
import com.project.storeapi.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
