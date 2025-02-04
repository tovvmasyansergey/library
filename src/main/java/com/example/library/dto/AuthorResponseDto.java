package com.example.library.dto;

import lombok.Builder;

@Builder
public record AuthorResponseDto(
        int id,
        String email
) {
}
