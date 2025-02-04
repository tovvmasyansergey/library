package com.example.library.mapper;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public abstract class BookMapper {
    @Mapping(target = "authorResponseDto", source = "author")
    public abstract BookResponseDto mapToDto(Book book);

    @Mapping(target = "author.id", source = "authorId")
    public abstract Book mapToBook(BookRequestDto bookRequestDto);
    public abstract List<BookResponseDto> mapListToDto(List<Book> books);


}
