package au.gov.nsw.revenue.booklibrary.service;


import au.gov.nsw.revenue.booklibrary.mapper.BookMapper;
import au.gov.nsw.revenue.booklibrary.openapi.model.Book;
import au.gov.nsw.revenue.booklibrary.openapi.model.DeleteBooks;
import au.gov.nsw.revenue.booklibrary.openapi.model.UpdateBook;
import au.gov.nsw.revenue.booklibrary.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookLibraryService {

    private final BookRepository bookRepository;
    private final StatusService statusService;
    private final BookMapper bookMapper;

    public Book createBook(Book bookRequest){
        Integer statusId = statusService.getStatusIdByStatusDescription(bookRequest.getStatus().name().toUpperCase());
        au.gov.nsw.revenue.booklibrary.entity.Book book = bookMapper.toBookEntity(bookRequest,statusId);
        return bookMapper.toBookDto(bookRepository.save(book),statusService.getStatusDescriptionByStatusId(statusId));
    }

    public List<Book> findAllBooks(String sortBy){
      Sort sortOrder = Sort.by(sortBy);
      List<au.gov.nsw.revenue.booklibrary.entity.Book> books = bookRepository.findAll(sortOrder);
      List<Book> bookDTOs = new ArrayList<>();
        books.forEach(book -> {
            bookDTOs.add(bookMapper.toBookDto(book, statusService.getStatusDescriptionByStatusId(book.getStatusId())));
        });
      return bookDTOs;
    }

    public BigDecimal updateBook(BigDecimal bookId, UpdateBook updateBook) {
        bookRepository.updateBookStatus(statusService.getStatusIdByStatusDescription(
                updateBook.getStatus().name().toUpperCase()),bookId.longValue());
        return bookId;
    }

    public void deleteBooks(DeleteBooks deleteBooks) {
        deleteBooks.getIdsToDelete().forEach(bookId -> {
            bookRepository.deleteById(bookId.longValue());
        });
    }
}
