package com.example.library.service.impl;

import com.example.library.dto.AuthorResponseDto;
import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.User;
import com.example.library.entity.UserType;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {
    @InjectMocks
    BookServiceImpl bookServiceImpl;
    @Mock
    BookRepository bookRepository;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    BookMapper bookMapper;
    private Book book;
    private Author author;

    private BookRequestDto bookRequestDto;

    @BeforeEach
    void setUp() {
        author = new Author(1, "a", "a", "a", new User(1, "ss", "ss", "ss", "ss", UserType.USER));
        book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        bookRequestDto = new BookRequestDto("aa", "aa", 1);

    }

    @Test
    void create_Success_Test() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(bookMapper.mapToBook(bookRequestDto)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.mapToDto(book)).thenReturn(new BookResponseDto(1, "aa", "aa", "aa", new AuthorResponseDto(1, "a")));

        BookResponseDto bookResponseDto = bookServiceImpl.create(bookRequestDto);

        assertNotNull(bookResponseDto);
        assertEquals("aa", bookResponseDto.title());
    }

    @Test
    void create_BookAuthorNotFound_Test() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> bookServiceImpl.create(bookRequestDto));
        assertEquals("Author with ID " + bookRequestDto.authorId() + " does not exist.", exception.getMessage());
    }

    @Test
    void delete_Success_Test() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        bookServiceImpl.delete(1);
    }

    @Test
    void delete_NotFoundBook_Test() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> bookServiceImpl.delete(1));
        assertEquals("Book with ID " + 1 + " does not exist.", exception.getMessage());
    }

    @Test
    void update_Success_Test() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        Book book1 = new Book(1, "aa", "cc", author, "as");
        when(bookRepository.save(book)).thenReturn(book1);
        when(bookMapper.mapToDto(book1)).thenReturn(new BookResponseDto(1, "aa", "cc", "as", new AuthorResponseDto(1, "a")));


        BookRequestDto updateDto = new BookRequestDto("aa", "cc", 1);
        BookResponseDto response = bookServiceImpl.update(1, updateDto);

        assertNotNull(response);
        assertEquals("aa", response.title());
        assertEquals("cc", response.description());

    }

    @Test
    void update_BookNotFound_Test() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());

        BookRequestDto updateDto = new BookRequestDto("Updated Book", "Updated Description", 1);
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> bookServiceImpl.update(1, updateDto));
        assertEquals("Not found book with " + 1 + " id", exception.getMessage());
    }


}