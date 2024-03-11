package au.gov.nsw.revenue.booklibrary.controller;

import au.gov.nsw.revenue.booklibrary.openapi.model.Book;
import au.gov.nsw.revenue.booklibrary.openapi.model.DeleteBooks;
import au.gov.nsw.revenue.booklibrary.openapi.model.UpdateBook;
import au.gov.nsw.revenue.booklibrary.service.BookLibraryService;
import org.hibernate.sql.Update;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
@ExtendWith(SpringExtension.class)
@WebMvcTest(BookLibraryController.class)
public class BookLibraryControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookLibraryService bookLibraryService;

    @Test
    public void testRetrieveAllBooks() throws Exception {
        Book book1=new Book();
        book1.setStatus(Book.StatusEnum.AVAILABLE);
        book1.setBookId(BigDecimal.ONE);
        book1.setTitle("Test Title 1");
        book1.setBorrowedBy("Test Borrower");
        book1.setAuthors(List.of("Test Auth1","Test Auth2"));

        Book book2=new Book();
        book2.setStatus(Book.StatusEnum.AVAILABLE);
        book2.setBookId(BigDecimal.ONE);
        book2.setTitle("Test Title 2");
        book2.setBorrowedBy("Test Borrower");
        book2.setAuthors(List.of("Test Auth1","Test Auth2"));

        when(bookLibraryService.findAllBooks(anyString())).thenReturn(List.of(book1,book2));
        mvc.perform(get("/book-library/v1/books?sortBy=title")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title", is("Test Title 1")));
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book1=new Book();
        book1.setStatus(Book.StatusEnum.AVAILABLE);
        book1.setBookId(BigDecimal.ONE);
        book1.setTitle("Test Title 1");
        book1.setBorrowedBy("Test Borrower");
        book1.setAuthors(List.of("Test Auth1","Test Auth2"));

        when(bookLibraryService.createBook(book1)).thenReturn(book1);

        mvc.perform(post("/book-library/v1/book")
                        .content(asJsonString(book1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("title", is("Test Title 1")))
                .andExpect(jsonPath("borrowedBy", is("Test Borrower")));
    }

    @Test
    public void testUpdateBook() throws Exception {
        UpdateBook updateBook = new UpdateBook();
        updateBook.setStatus(UpdateBook.StatusEnum.AVAILABLE);

        when(bookLibraryService.updateBook(BigDecimal.ONE,updateBook)).thenReturn(BigDecimal.ONE);

        mvc.perform(put("/book-library/v1/updateBook?bookId=1")
                        .content(asJsonString(updateBook))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBooks() throws Exception {
        DeleteBooks deleteBooks = new DeleteBooks();
        deleteBooks.setIdsToDelete(List.of(BigDecimal.ONE,BigDecimal.TWO));
        mvc.perform(delete("/book-library/v1/deleteBooks")
                        .content(asJsonString(deleteBooks))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
