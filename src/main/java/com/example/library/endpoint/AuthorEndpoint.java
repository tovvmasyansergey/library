package com.example.library.endpoint;

import com.example.library.dto.AuthorCreateRequestDto;
import com.example.library.dto.AuthorResponseDto;
import com.example.library.security.CurrentUser;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorEndpoint {

    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAll(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(authorService.getAllByUser(currentUser));
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@AuthenticationPrincipal CurrentUser currentUser, @RequestBody AuthorCreateRequestDto authorCreateRequestDto) {
        return ResponseEntity.ok(authorService.create(currentUser, authorCreateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        authorService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@PathVariable("id") int id,
                                                    @RequestBody AuthorCreateRequestDto authorCreateRequestDto
    ) {
        return ResponseEntity.ok(authorService.update(id,authorCreateRequestDto));
    }
}
