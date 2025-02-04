package com.example.library.mapper;

import com.example.library.dto.AuthorCreateRequestDto;
import com.example.library.dto.AuthorResponseDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDto mapToDto(Author author);

    Author mapToAuthor(AuthorCreateRequestDto authorCreateRequestDto);

    List<AuthorResponseDto> mapListToDto(List<Author> authors);

}
