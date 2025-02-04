package com.example.library.service.impl;

import ch.qos.logback.core.util.StringUtil;
import com.example.library.dto.BookRequestDto;
import com.example.library.dto.BookResponseDto;
import com.example.library.dto.BookSearchRequestDto;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.entity.QBook;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    @Value("${upload.image.path}")
    private String uploadPath;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BookResponseDto> search(BookSearchRequestDto bookSearchRequestDto, int size, int page) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "id");
//        if (bookSearchRequestDto.sortBy() != null) {
//            sort = Sort.by("asc".equalsIgnoreCase(bookSearchRequestDto.sortDirection()) ?
//                    Sort.Direction.ASC : Sort.Direction.DESC, bookSearchRequestDto.sortBy());
//        }
//        PageRequest pageRequest = PageRequest.of(page, size, sort);
//        Page<Book> all = bookRepository.findAll(pageRequest);
        List<Book> books = searchBooksByFilter(page, size, bookSearchRequestDto);
        return bookMapper.mapListToDto(books);
    }


    @Override
    public BookResponseDto create(BookRequestDto bookRequestDto) {
        if (bookRequestDto == null) {
            throw new EntityNotFoundException("Book cannot be null.");
        }
        Author author = existAuthor(bookRequestDto.authorId());
        Book book = bookMapper.mapToBook(bookRequestDto);
        Book saved = bookRepository.save(book);
        saved.setAuthor(author);
        return bookMapper.mapToDto(saved);
    }

    @Override
    public void delete(int id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Book with ID " + id + " does not exist.");
        }
    }

    @Override
    public BookResponseDto update(int id, BookRequestDto bookRequestDto) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not found book with " + id + " id"));
        if (!StringUtil.isNullOrEmpty(bookRequestDto.title())) {
            book.setTitle(bookRequestDto.title());
        }
        if (!StringUtil.isNullOrEmpty(bookRequestDto.description())) {
            book.setDescription(bookRequestDto.description());
        }
        Book saved = bookRepository.save(book);
        return bookMapper.mapToDto(saved);
    }

    @Override
    public BookResponseDto addImageToBook(int id, MultipartFile multipartFile) throws IOException {
        Optional<Book> byId = bookRepository.findById(id);
        if (!multipartFile.isEmpty() && byId.isPresent()) {
            String originalFilename = multipartFile.getOriginalFilename();
            String picName = System.currentTimeMillis() + "_" + originalFilename;
            File file = new File(uploadPath + picName);
            multipartFile.transferTo(file);
            Book book = byId.get();
            book.setPicName(picName);
            Book saved = bookRepository.save(book);
            return bookMapper.mapToDto(saved);
        } else {
            throw new EntityNotFoundException("Please input filename or wrong id");
        }
    }

    private Author existAuthor(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with ID " + id + " does not exist."));
    }
    private List<Book> searchBooksByFilter(int page,int size,BookSearchRequestDto bookSearchRequestDto){
        QBook qBook = QBook.book;
        var query = new JPAQuery<Book>(entityManager);
        JPAQuery<Book> from = query.from(qBook);
        if (bookSearchRequestDto.description() != null && !bookSearchRequestDto.description().isEmpty()) {
            from.where(qBook.description.contains(bookSearchRequestDto.description()));
        }
        if (bookSearchRequestDto.authorName() != null && !bookSearchRequestDto.authorName().isEmpty()) {
            from.where(qBook.author.name.contains(bookSearchRequestDto.authorName()));
        }
        if (page > 0) {
            from.offset((long) page * size);
        }
        from.limit(size);
        PathBuilder<Object> orderByExpression = new PathBuilder<Object>(Book.class, bookSearchRequestDto.sortBy());

        from.orderBy(new OrderSpecifier("asc".equalsIgnoreCase(bookSearchRequestDto.sortDirection()) ? Order.ASC
                : Order.DESC, orderByExpression));
        return from.fetch();
    }
}
