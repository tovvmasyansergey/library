package com.example.library.repository;

import com.example.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
    List<Author> getAuthorsByUserId(int id);
    Optional<Author> findByEmail(String email);
}
