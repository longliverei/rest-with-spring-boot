package br.com.rei.unittests.mockito.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.rei.data.dto.v1.BookDto;
import br.com.rei.exceptions.RequiredObjectIsNullException;
import br.com.rei.mapper.BookMapper;
import br.com.rei.models.Book;
import br.com.rei.repositories.BookRepository;
import br.com.rei.services.BookServices;
import br.com.rei.unittests.mapper.mocks.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith({ MockitoExtension.class })
class BookServicesTest {
	
	MockBook input;
	
	@InjectMocks
	private BookServices service;
	
	@Mock
	private BookRepository repository;
	
	@Mock
	private BookMapper bookMapper;
	
	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testFindAll() {
		List<Book> entities = input.mockEntityList();
		List<BookDto> dtoList = input.mockVOList();
		
		when(repository.findAll())
			.thenReturn(entities);
		when(bookMapper.booksToBooksDto(entities))
			.thenReturn(dtoList);
		
		var book = service.findAll();
		assertNotNull(book);
		
		var bookOne = book.get(1);
		bookOne.setKey(1L);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		assertTrue(bookOne.toString().contains("links: [</api/book/v1/1>;rel=\"self\"]"));
		
		var bookSeven = book.get(7);
		bookSeven.setKey(7L);
		
		assertNotNull(bookSeven);
		assertNotNull(bookSeven.getKey());
		assertNotNull(bookSeven.getLinks());
		assertTrue(bookSeven.toString().contains("links: [</api/book/v1/7>;rel=\"self\"]"));
	}

	@Test
	void testFindById() {
		
		Book entity = input.mockEntity();
		BookDto dto = input.mockVO();
		entity.setId(0L);
		
		when(repository.findById(entity.getId()))
			.thenReturn(Optional.of(entity));
		when(bookMapper.bookToBookDto(entity))
			.thenReturn(dto);
		
		BookDto result = service.findById(entity.getId());
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/0>;rel=\"self\"]"));
		assertThat(result)
			.isSameAs(dto);
	}

	@Test
	void testCreate() {
		
		Book entity = input.mockEntity();
		Book persisted = entity;
		persisted.setId(0L);
		
		BookDto dto = input.mockVO();
		
		when(bookMapper.bookDtoToBook(dto))
			.thenReturn(entity);
		when(repository.save(entity))
			.thenReturn(persisted);
		when(bookMapper.bookToBookDto(persisted))
			.thenReturn(dto);
		
		BookDto result = service.create(dto);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/0>;rel=\"self\"]"));
	}
	
	@Test
	void testCreateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testUpdate() {
		
		Book entity = input.mockEntity();
		Book persisted = entity;
		persisted.setId(0L);
		
		BookDto dto = input.mockVO();
		
		when(repository.findById(dto.getKey()))
			.thenReturn(Optional.of(entity));
		when(repository.save(entity))
			.thenReturn(persisted);
		when(bookMapper.bookToBookDto(persisted))
			.thenReturn(dto);
		
		BookDto result = service.update(dto);
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		assertTrue(result.toString().contains("links: [</api/book/v1/0>;rel=\"self\"]"));
	}
	
	@Test
	void testUpdateWithNullPerson() {
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.update(null);
		});
		
		String expectedMessage = "It's not allowed to persist a null object!";
		String actualMessage = exception.getMessage();
		
		assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testDelete() {
		Book entity = input.mockEntity();
		entity.setId(0L);
		
		when(repository.findById(entity.getId()))
			.thenReturn(Optional.of(entity));
		
		service.delete(entity.getId());
	}

}
