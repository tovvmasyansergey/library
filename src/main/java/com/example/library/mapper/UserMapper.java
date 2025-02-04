package com.example.library.mapper;


import com.example.library.dto.UserRegisterRequestDto;
import com.example.library.dto.UserRegisterResponseDto;
import com.example.library.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "type", expression = "java(com.example.library.entity.UserType.USER)")
    User mapToUser(UserRegisterRequestDto dto);

    UserRegisterResponseDto mapToDto(User user);
}
