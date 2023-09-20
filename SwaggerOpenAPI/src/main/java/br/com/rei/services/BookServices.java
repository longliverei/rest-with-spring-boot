package br.com.rei.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rei.controllers.BookController;
import br.com.rei.controllers.PersonController;
import br.com.rei.data.dto.v1.BookDto;
import br.com.rei.exceptions.RequiredObjectIsNullException;
import br.com.rei.exceptions.ResourceNotFoundException;
import br.com.rei.mapper.BookMapper;
import br.com.rei.models.Book;
import br.com.rei.repositories.BookRepository;

@Service
public class BookServices {
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	BookMapper bookMapper;
	
	public List<BookDto> findAll() {
		
		List<BookDto> dtoList = bookMapper.booksToBooksDto(bookRepository.findAll());
		
		dtoList
			.stream()
			.forEach(bookDto -> bookDto.add(linkTo(methodOn(BookController.class).findById(bookDto.getKey())).withSelfRel()));
		
		return dtoList;
	}
	
	public BookDto findById(Long id) {
		
		Book entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		BookDto bookDto = bookMapper.bookToBookDto(entity);
		bookDto.add(linkTo(methodOn(BookController.class).findById(bookDto.getKey())).withSelfRel());
		
		return bookDto;
		
	}
	
	public BookDto create(BookDto book) {
		
		if (book == null) throw new RequiredObjectIsNullException();
		
		Book entity = bookMapper.bookDtoToBook(book);
		
		BookDto bookDto = bookMapper.bookToBookDto(bookRepository.save(entity));
		bookDto.add(linkTo(methodOn(BookController.class).findById(bookDto.getKey())).withSelfRel());
		
		return bookDto;		
	}
	
	public BookDto update(BookDto book) {
		
		if (book == null) throw new RequiredObjectIsNullException();
		
		Book entity = bookRepository.findById(book.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setTitle(book.getTitle());
		entity.setPrice(book.getPrice());
		
		BookDto bookDto = bookMapper.bookToBookDto(bookRepository.save(entity));
		bookDto.add(linkTo(methodOn(BookController.class).findById(bookDto.getKey())).withSelfRel());
		
		return bookDto;
	}
	
	public void delete(Long id) {
		
		Book entity = bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
		
		bookRepository.delete(entity);
	}
}
