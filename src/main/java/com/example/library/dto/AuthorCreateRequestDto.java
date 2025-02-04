package com.example.library.dto;

import lombok.Builder;

@Builder
public record AuthorCreateRequestDto(
        String name,
        String surname,
        String email
) {
}
