package com.example.library.repository;

import com.example.library.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> , QuerydslPredicateExecutor<Book> {
    List<Book> getBooksByAuthorId(int id);
}
