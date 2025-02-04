package com.example.library.dto;

import lombok.Builder;

@Builder
public record RestErrorDto(
        int statusCode,
        String errorMessage
) {
}
