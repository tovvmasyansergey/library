package com.example.library.service.impl;

import com.example.library.dto.AuthorCreateRequestDto;
import com.example.library.dto.AuthorResponseDto;
import com.example.library.entity.Author;
import com.example.library.entity.User;
import com.example.library.entity.UserType;
import com.example.library.exception.EmailValidationException;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.UserRepository;
import com.example.library.security.CurrentUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {
    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    AuthorMapper authorMapper;
    private Author author;
    private CurrentUser currentUser;
    private CurrentUser currentUser1;
    AuthorCreateRequestDto authorCreateRequestDto;
    private User admin;
    private User user;

    @BeforeEach
    public void setUp() {
        user = new User(1, "a", "a", "a", "a", UserType.USER);
        admin = new User(2, "d", "d", "d", "d", UserType.ADMIN);
        currentUser = new CurrentUser(user);
        currentUser1 = new CurrentUser(admin);
        author = new Author(1, "a", "a", "a", user);
    }

    @Test
    void getAllByUser_UserIsAdmin_Test() {
        when(userRepository.findById(2)).thenReturn(Optional.of(admin));
        when(authorRepository.findAll()).thenReturn(List.of(new Author()));
        when(authorMapper.mapListToDto(List.of(new Author()))).thenReturn(List.of(new AuthorResponseDto(1, "d")));
        List<AuthorResponseDto> allByUser = authorServiceImpl.getAllByUser(currentUser1);
        Assertions.assertNotNull(allByUser);
        assertEquals(1, allByUser.size());
        assertEquals(1, allByUser.get(0).id());
    }

    @Test
    void getAllByUser_UserExist_Test() {
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        when(authorRepository.getAuthorsByUserId(1)).thenReturn(List.of(author));
        when(authorMapper.mapListToDto(List.of(author))).thenReturn(List.of(new AuthorResponseDto(1, "d")));
        List<AuthorResponseDto> allByUser = authorServiceImpl.getAllByUser(currentUser);
        Assertions.assertNotNull(allByUser);
        assertEquals(1, allByUser.size());
        assertEquals(1, allByUser.get(0).id());
    }

    @Test
    void getAllByUser_UserNotExist_Test() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        List<AuthorResponseDto> allByUserId = authorServiceImpl.getAllByUser(currentUser);

        assertEquals(allByUserId, Collections.emptyList());
    }


    @Test
    void create_Test() {
        when(authorMapper.mapToAuthor(authorCreateRequestDto)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.mapToDto(author)).thenReturn(new AuthorResponseDto(1, "a"));

        AuthorResponseDto authorResponseDto = authorServiceImpl.create(currentUser, authorCreateRequestDto);

        assertEquals(1, authorResponseDto.id());
        assertNotNull(authorResponseDto);
    }

    @Test
    void deleteById() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        authorServiceImpl.deleteById(1);
    }

    @Test
    void update_Test() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(authorRepository.findByEmail("aa")).thenReturn(Optional.empty());
        when(authorRepository.save(author)).thenReturn(new Author(author.getId(), "aa", "aa", "aa", author.getUser()));
        when(authorMapper.mapToDto(new Author(author.getId(), "aa", "aa", "aa", author.getUser()))).thenReturn(new AuthorResponseDto(1, "aa"));

        AuthorResponseDto update = authorServiceImpl.update(1, new AuthorCreateRequestDto("aa", "aa", "aa"));

        assertEquals(1, update.id());
        assertNotNull(update);

    }

    @Test
    void update_AuthorNotFound_Test() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () ->
                authorServiceImpl.update(1, new AuthorCreateRequestDto("a", "a", "a")));
        assertEquals("Author not fount with " + 1 + " id", exception.getMessage());
    }

    @Test
    void update_EmailExist_Test() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(authorRepository.findByEmail("aa")).thenReturn(Optional.of(new Author()));
        EmailValidationException emailValidationException = assertThrows(EmailValidationException.class, () ->
                authorServiceImpl.update(1, new AuthorCreateRequestDto("aa", "aa", "aa")));

        assertEquals("this email exist", emailValidationException.getMessage());
    }
}