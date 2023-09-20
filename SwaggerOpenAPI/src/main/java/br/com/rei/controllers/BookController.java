package br.com.rei.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rei.data.dto.v1.BookDto;
import br.com.rei.services.BookServices;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {
	
	@Autowired
	BookServices bookService;
	
	@GetMapping
	public List<BookDto> findAll() {
		return bookService.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public BookDto findById(@PathVariable("id") Long id) {
		return bookService.findById(id);
	}
	
	@PostMapping
	public BookDto create(@RequestBody BookDto bookDto) {
		return bookService.create(bookDto);
	}
	
	@PutMapping
	public BookDto update(@RequestBody BookDto bookDto) {
		return bookService.update(bookDto);
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		bookService.delete(id);
		
		return ResponseEntity.noContent().build();
	}
}
