package com.example.library.endpoint;

import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.dto.BookSearchRequestDto;
import com.example.library.service.impl.BookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookEndpoint {
    private final BookServiceImpl bookServiceImpl;

    @PostMapping("/search")
    public ResponseEntity<List<BookResponseDto>> search(
            @RequestParam(name = "size", defaultValue = "20") int size,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestBody BookSearchRequestDto bookSearchRequestDto) {
        return ResponseEntity.ok(bookServiceImpl.search(bookSearchRequestDto,size,page));
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> create(@RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.ok(bookServiceImpl.create(bookRequestDto));
    }

    @PostMapping("/{id}/image")
    public ResponseEntity<BookResponseDto> uploadImage(
            @PathVariable("id") int id,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        return ResponseEntity.ok(bookServiceImpl.addImageToBook(id, multipartFile));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") int id) {
        bookServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{ids}")
    public ResponseEntity<BookResponseDto> update(@PathVariable("ids") int ids, @RequestBody BookRequestDto bookRequestDto) {
        return ResponseEntity.ok(bookServiceImpl.update(ids, bookRequestDto));
    }
}