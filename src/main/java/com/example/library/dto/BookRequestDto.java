package com.example.library.dto;

import lombok.Builder;

@Builder
public record BookRequestDto(
        String title,
        String description,
        int authorId
) {
}
