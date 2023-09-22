package br.com.rei.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.rei.data.dto.v1.BookDto;
import br.com.rei.mapper.BookMapper;
import br.com.rei.mapper.BookMapperImpl;
import br.com.rei.models.Book;
import br.com.rei.unittests.mapper.mocks.MockBook;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { BookMapperImpl.class } )
public class BookMapperTest {
    
    MockBook inputObject;

    @BeforeEach
    public void setUp() {
        inputObject = new MockBook();
    }
    
    @Autowired
    private BookMapper bookMapper;
    
    @Test
    public void parseEntityToVOTest() {
    	BookDto output = bookMapper.bookToBookDto(inputObject.mockEntity());
        assertEquals(Long.valueOf(0L), output.getKey());
        assertEquals("Author0", output.getAuthor());
        assertEquals("Title0", output.getTitle());
        assertEquals(20.00 + 0, output.getPrice());
    }

    @Test
    public void parseEntityListToVOListTest() {
        List<BookDto> outputList = bookMapper.booksToBooksDto(inputObject.mockEntityList());
        BookDto outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getKey());
        assertEquals("Author0", outputZero.getAuthor());
        assertEquals("Title0", outputZero.getTitle());
        assertEquals(20.00 + 0, outputZero.getPrice());
        
        BookDto outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getKey());
        assertEquals("Author7", outputSeven.getAuthor());
        assertEquals("Title7", outputSeven.getTitle());
        assertEquals(20.00 + 7, outputSeven.getPrice());
        
        BookDto outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getKey());
        assertEquals("Author12", outputTwelve.getAuthor());
        assertEquals("Title12", outputTwelve.getTitle());
        assertEquals(20.00 + 12, outputTwelve.getPrice());
    }

    @Test
    public void parseVOToEntityTest() {
        Book output = bookMapper.bookDtoToBook(inputObject.mockVO());
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Author0", output.getAuthor());
        assertEquals("Title0", output.getTitle());
        assertEquals(20.00 + 0, output.getPrice());
    }

    @Test
    public void parserVOListToEntityListTest() {
        List<Book> outputList = bookMapper.booksDtoToBooks(inputObject.mockVOList());
        Book outputZero = outputList.get(0);
        
        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Author0", outputZero.getAuthor());
        assertEquals("Title0", outputZero.getTitle());
        assertEquals(20.00 + 0, outputZero.getPrice());
        
        Book outputSeven = outputList.get(7);
        
        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Author7", outputSeven.getAuthor());
        assertEquals("Title7", outputSeven.getTitle());
        assertEquals(20.00 + 7, outputSeven.getPrice());
        
        Book outputTwelve = outputList.get(12);
        
        assertEquals(Long.valueOf(12L), outputTwelve.getId());
        assertEquals("Author12", outputTwelve.getAuthor());
        assertEquals("Title12", outputTwelve.getTitle());
        assertEquals(20.00 + 12, outputTwelve.getPrice());
    }
}
