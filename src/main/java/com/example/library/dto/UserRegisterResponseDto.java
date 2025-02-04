package com.example.library.dto;

import com.example.library.entity.UserType;
import lombok.Builder;


@Builder
public record UserRegisterResponseDto(
        int id,
        String name,
        String surname,
        String email,
        UserType type
) {
}
