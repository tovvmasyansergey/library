package com.example.library.dto;

import lombok.Builder;


@Builder
public record UserAuthResponseDto(
        String token
) {
}
