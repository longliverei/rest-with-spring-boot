package br.com.rei.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rei.models.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
