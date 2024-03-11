package au.gov.nsw.revenue.booklibrary.service;

import au.gov.nsw.revenue.booklibrary.entity.Author;
import au.gov.nsw.revenue.booklibrary.entity.Book;
import au.gov.nsw.revenue.booklibrary.mapper.BookMapper;
import au.gov.nsw.revenue.booklibrary.openapi.model.UpdateBook;
import au.gov.nsw.revenue.booklibrary.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Slf4j
public class BookLibraryServiceTest {
    @InjectMocks
    private BookLibraryService bookLibraryService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private StatusService statusService;

    @Mock
    BookMapper bookMapper = mock(BookMapper.class);;

    public final String BORROWER_USER = "TEST USER";
    public final String AUTHOR_1 = "AUTH1";
    public final String AUTHOR_2 = "AUTH2";

    public final String TITLE= "TEST TITLE";

    public final String STATUS_1_DESCRIPTION = "AVAILABLE";


    @Test
    void testCreateBook() {
        au.gov.nsw.revenue.booklibrary.openapi.model.Book bookRequest = new au.gov.nsw.revenue.booklibrary.openapi.model.Book();
        bookRequest.setStatus(au.gov.nsw.revenue.booklibrary.openapi.model.Book.StatusEnum.fromValue(STATUS_1_DESCRIPTION));
        bookRequest.setBorrowedBy(BORROWER_USER);
        bookRequest.setAuthors(List.of(AUTHOR_1,AUTHOR_2));
        bookRequest.setTitle(TITLE);

        Book bookEntity = Book.builder().borrowedBy(BORROWER_USER)
                .bookId(1L)
                .title(TITLE)
                .statusId(1)
                .authors(List.of(
                 Author.builder().authorId(1L).fullName(AUTHOR_1).build()
                ,Author.builder().authorId(2L).fullName(AUTHOR_2).build()))
                .build();

        when(bookRepository.save(any(Book.class)))
                .thenReturn(bookEntity);
        bookRequest.setBookId(BigDecimal.ONE);
        when(statusService.getStatusIdByStatusDescription(STATUS_1_DESCRIPTION)).thenReturn(1);
        when(statusService.getStatusDescriptionByStatusId(1)).thenReturn(STATUS_1_DESCRIPTION);
        when(bookMapper.toBookEntity(bookRequest,1)).thenReturn(bookEntity);
        when(bookMapper.toBookDto(bookEntity, au.gov.nsw.revenue.booklibrary.openapi.model.Book.StatusEnum.AVAILABLE.getValue())).thenReturn(bookRequest);


        au.gov.nsw.revenue.booklibrary.openapi.model.Book book = bookLibraryService.createBook(bookRequest);
        assertNotNull(book.getBookId());
        assertEquals(BigDecimal.ONE,book.getBookId());
        assertEquals(BORROWER_USER,book.getBorrowedBy());
        assertEquals(TITLE,book.getTitle());
        assertEquals(AUTHOR_1,book.getAuthors().get(0));
        assertEquals(AUTHOR_2,book.getAuthors().get(1));
    }

    @Test
    public void testFindAllBooks(){
        Book bookEntity1 = Book.builder().borrowedBy(BORROWER_USER)
                .bookId(1L)
                .title(TITLE)
                .statusId(1)
                .authors(List.of(
                        Author.builder().authorId(1L).fullName(AUTHOR_1).build()
                        ,Author.builder().authorId(2L).fullName(AUTHOR_2).build()))
                .build();

        Book bookEntity2 = Book.builder().borrowedBy(BORROWER_USER)
                .bookId(2L)
                .title(TITLE)
                .statusId(1)
                .authors(List.of(
                        Author.builder().authorId(1L).fullName(AUTHOR_1).build()
                        ,Author.builder().authorId(2L).fullName(AUTHOR_2).build()))
                .build();

        when(bookRepository.findAll(Sort.by("title"))).thenReturn(List.of(bookEntity1,bookEntity2));
        au.gov.nsw.revenue.booklibrary.openapi.model.Book bookDto = new au.gov.nsw.revenue.booklibrary.openapi.model.Book();
        bookDto.setStatus(au.gov.nsw.revenue.booklibrary.openapi.model.Book.StatusEnum.AVAILABLE);
        when(bookMapper.toBookDto(any(Book.class),anyString())).thenReturn(bookDto);
        List<au.gov.nsw.revenue.booklibrary.openapi.model.Book> books = bookLibraryService.findAllBooks("title");
        assertNotNull(books);
        assertEquals(2,books.size());
    }

    @Test
    public void testUpdateBook(){
        UpdateBook updateBook = new UpdateBook();
        updateBook.setStatus(UpdateBook.StatusEnum.AVAILABLE);
        when(statusService.getStatusIdByStatusDescription(au.gov.nsw.revenue.booklibrary.openapi.model.Book.StatusEnum.AVAILABLE.name())).thenReturn(1);
        BigDecimal bookId = bookLibraryService.updateBook(BigDecimal.ONE,updateBook);
        assertEquals(BigDecimal.ONE,bookId);
    }
}
