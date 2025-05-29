package org.openapitools.api;

import java.math.BigDecimal;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.openapitools.model.Book;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.constraints.*;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2025-03-19T20:26:27.436269835Z[Etc/UTC]", comments = "Generator version: 7.11.0-SNAPSHOT")
@Controller
@RequestMapping("${openapi.book.base-path:}")
public class BooksApiController implements BooksApi {
    
    private final NativeWebRequest request;
    ArrayList<Book> books = new ArrayList<>();

    @Autowired
    public BooksApiController(NativeWebRequest request) {
        this.request = request;

        Book book = new Book();
        BigDecimal id = new BigDecimal(books.size()+1);
        book.setId(id);
        book.setAuthor("Luisa may alcott");
        book.setTitle("Little women");
        book.setDescription("4 sisters");
        books.add(book);

        book = new Book();
        id = new BigDecimal(books.size()+1);
        book.setId(id);
        book.setAuthor("J.K Rowling");
        book.setTitle("Harry Potter and Goblet of fire");
        book.setDescription("Harry in 4th year");
        books.add(book);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Book> getBookById(BigDecimal bookId)
    {  
        for(Book book: books)
        {
            if(book.getId().equals(bookId))
                return ResponseEntity.ok(book);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<Book> updateBookById(BigDecimal bookId, Book updatedBook) {
        for(Book book : books)
        {
            if(book.getId().equals(bookId))
            {
                book.setAuthor(updatedBook.getAuthor());
                book.setDescription(updatedBook.getDescription());
                book.setTitle(updatedBook.getTitle());                
                return ResponseEntity.ok(book);
            }            
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    public ResponseEntity<List<Book>> listBooks() {        
        return ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<Book> createBook(Book book)
    {
        if(book.getId() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        book.setId(new BigDecimal(books.size()+1));
        books.add(book);
        return ResponseEntity.ok(book); 
    }
}
