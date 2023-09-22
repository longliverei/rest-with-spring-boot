package br.com.rei.unittests.mapper.mocks;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.rei.data.dto.v1.BookDto;
import br.com.rei.models.Book;

public class MockBook {


    public Book mockEntity() {
        return mockEntity(0);
    }
    
    public BookDto mockVO() {
        return mockVO(0);
    }
    
    public List<Book> mockEntityList() {
        List<Book> books = new ArrayList<Book>();
        for (int i = 0; i < 14; i++) {
            books.add(mockEntity(i));
        }
        return books;
    }

    public List<BookDto> mockVOList() {
        List<BookDto> booksDto = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            booksDto.add(mockVO(i));
        }
        return booksDto;
    }
    
    public Book mockEntity(Integer number) {
        Book book = new Book();
        book.setAuthor("Author" + number);
        book.setLaunchDate(new Date());
        book.setTitle("Title" + number);
        book.setId(number.longValue());
        book.setPrice(20.00 + number);
        return book;
    }

    public BookDto mockVO(Integer number) {
        BookDto bookDto = new BookDto();
        bookDto.setAuthor("Author" + number);
        bookDto.setLaunchDate(new Date());
        bookDto.setTitle("Title" + number);
        bookDto.setKey(number.longValue());
        bookDto.setPrice(20.00 + number);
        return bookDto;
    }

}
