package com.example.library.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.example.library.dto.AuthorCreateRequestDto;
import com.example.library.dto.AuthorResponseDto;
import com.example.library.entity.Author;
import com.example.library.entity.UserType;
import com.example.library.exception.EmailValidationException;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.UserRepository;
import com.example.library.security.CurrentUser;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final UserRepository userRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorResponseDto> getAllByUser(CurrentUser currentUser) {
        if (userExist(currentUser) && currentUser.getUser().getType() == UserType.ADMIN) {
            return authorMapper.mapListToDto(authorRepository.findAll());
        }
        if (userExist(currentUser)) {
            return authorMapper.mapListToDto(authorRepository.getAuthorsByUserId(currentUser.getUser().getId()));
        }
        return Collections.emptyList();
    }

    @Override
    public AuthorResponseDto create(CurrentUser currentUser, AuthorCreateRequestDto authorCreateRequestDto) {
        Author author = authorMapper.mapToAuthor(authorCreateRequestDto);
        author.setUser(currentUser.getUser());
        authorRepository.save(author);
        return authorMapper.mapToDto(author);
    }

    @Override
    public void deleteById(int id) {
        findById(id);
        authorRepository.deleteById(id);
    }


    @Override
    public AuthorResponseDto update(int id, AuthorCreateRequestDto authorCreateRequestDto) {
        Author author = findById(id);
        Author updatedAuthor = updateDateOfAuthor(authorCreateRequestDto, author);
        return authorMapper.mapToDto(updatedAuthor);

    }

    private Author findById(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not fount with " + id + " id"));
    }

    private boolean userExist(CurrentUser currentUser) {
        return userRepository.findById(currentUser.getUser().getId()).isPresent();
    }

    private Author updateDateOfAuthor(AuthorCreateRequestDto authorCreateRequestDto, Author author) {
        if (!StringUtil.isNullOrEmpty(authorCreateRequestDto.email())) {
            if (authorRepository.findByEmail(authorCreateRequestDto.email()).isPresent()) {
                throw new EmailValidationException("this email exist");
            } else {
                author.setEmail(authorCreateRequestDto.email());
            }
        }
        if (!StringUtil.isNullOrEmpty(authorCreateRequestDto.name())) {
            author.setName(authorCreateRequestDto.name());
        }
        if (!StringUtil.isNullOrEmpty(authorCreateRequestDto.surname())) {
            author.setSurname(authorCreateRequestDto.surname());
        }
        return authorRepository.save(author);
    }
}
