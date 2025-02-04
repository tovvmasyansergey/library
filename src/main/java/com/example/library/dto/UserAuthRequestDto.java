package com.example.library.dto;

import lombok.Builder;


@Builder
public record UserAuthRequestDto(
        String email,
        String password
) {
}
