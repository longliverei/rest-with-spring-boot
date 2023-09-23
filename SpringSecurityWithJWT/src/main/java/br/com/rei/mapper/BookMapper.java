package br.com.rei.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.rei.data.dto.v1.BookDto;
import br.com.rei.models.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

	@Mapping(target = "key", source = "id")
	BookDto bookToBookDto(Book book);
	
	@Mapping(target = "id", source = "key")
	Book bookDtoToBook(BookDto bookDto);
	
	@Mapping(target = "key", source = "id")
	List<BookDto> booksToBooksDto(List<Book> books);
	
	@Mapping(target = "id", source = "key")
	List<Book> booksDtoToBooks(List<BookDto> booksDto);
}
