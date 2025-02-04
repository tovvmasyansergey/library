package com.example.library.dto;

import lombok.Builder;

@Builder
public record BookSearchRequestDto(
        String title,
        String description,
        String authorName,

        String sortBy,
        String sortDirection
) {
}
