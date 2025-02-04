package com.example.library.service;

import com.example.library.dto.AuthorCreateRequestDto;
import com.example.library.dto.AuthorResponseDto;
import com.example.library.entity.Author;
import com.example.library.security.CurrentUser;

import java.util.List;


public interface AuthorService {
    List<AuthorResponseDto> getAllByUser(CurrentUser currentUser);

    AuthorResponseDto create(CurrentUser currentUser, AuthorCreateRequestDto authorCreateRequestDto);

    void deleteById(int id);

    AuthorResponseDto update(int id,AuthorCreateRequestDto authorCreateRequestDto);

}


