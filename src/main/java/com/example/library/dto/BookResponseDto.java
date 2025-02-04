package com.example.library.dto;

import lombok.Builder;

@Builder
public record BookResponseDto(
        int id,
        String title,
        String description,
        String picName,
        AuthorResponseDto authorResponseDto


) {
}
