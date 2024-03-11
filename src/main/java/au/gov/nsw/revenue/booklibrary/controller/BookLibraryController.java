package au.gov.nsw.revenue.booklibrary.controller;

import au.gov.nsw.revenue.booklibrary.openapi.api.BookLibraryApi;
import au.gov.nsw.revenue.booklibrary.openapi.model.Book;
import au.gov.nsw.revenue.booklibrary.openapi.model.DeleteBooks;
import au.gov.nsw.revenue.booklibrary.openapi.model.UpdateBook;
import au.gov.nsw.revenue.booklibrary.service.BookLibraryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Book Library API",description = "The API endpoints enables a client to perform CRUD operations on book library.")
@CrossOrigin("http://localhost:3000/")
public class BookLibraryController implements BookLibraryApi {

    private final BookLibraryService bookLibraryService;

    @Override
    public ResponseEntity<Book> createBook(Book book) {
        Book bookCreated = bookLibraryService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookCreated);
    }

    @Override
    public ResponseEntity<BigDecimal> deleteBook(BigDecimal bookId) {
        bookLibraryService.delete(bookId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<BigDecimal> deleteBooks(DeleteBooks deleteBooks) {
       bookLibraryService.deleteBooks(deleteBooks);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<List<Book>> retrieveAllBooks(String sortBy) {
        return ResponseEntity.status(HttpStatus.OK).body(bookLibraryService.findAllBooks(sortBy));
    }

    @Override
    public ResponseEntity<List<Book>> retrieveAllBooksByAuthor(String authorId) {
        return null;
    }

    @Override
    public ResponseEntity<BigDecimal> updateBook(BigDecimal bookId, UpdateBook updateBook) {
        return ResponseEntity.status(HttpStatus.OK).body(bookLibraryService.updateBook(bookId,updateBook));
    }
}
