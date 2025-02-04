package com.example.library.service;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.dto.BookSearchRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface BookService {
    List<BookResponseDto> search(BookSearchRequestDto bookSearchRequestDto,int size,int page);
    BookResponseDto create(BookRequestDto bookRequestDto);
    void delete(int id);
    BookResponseDto update(int id,BookRequestDto bookRequestDto);

    BookResponseDto addImageToBook(int id, MultipartFile multipartFile) throws IOException;
}

