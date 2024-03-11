package au.gov.nsw.revenue.booklibrary.config;

import au.gov.nsw.revenue.booklibrary.entity.Author;
import au.gov.nsw.revenue.booklibrary.entity.Status;
import au.gov.nsw.revenue.booklibrary.repository.BookRepository;
import au.gov.nsw.revenue.booklibrary.repository.StatusRepository;
import au.gov.nsw.revenue.booklibrary.util.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ApplicationEventHandler {
  private final StatusRepository statusRepository;
  private final BookRepository bookRepository;

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationEvent(ApplicationReadyEvent event) {
    statusRepository
            .save(Status.builder().id(1).description(Constants.AVAILABLE).build());
    statusRepository
            .save(Status.builder().id(2).description(Constants.BORROWED).build());

    Author author1 = Author.builder().authorId(1L).fullName("J K Rowling").build();
    Author author2 = Author.builder().authorId(2L).fullName("J S Rowling").build();
    bookRepository.save(au.gov.nsw.revenue.booklibrary.entity.Book.builder().bookId(1L).statusId(1)
            .borrowedBy("John Smith").title("HARRY POTTER").authors(List.of(author1,author2)).build());

    Author author3 = Author.builder().authorId(3L).fullName("Stephen King").build();
    bookRepository.save(au.gov.nsw.revenue.booklibrary.entity.Book.builder().bookId(2L).statusId(2)
            .borrowedBy("Alex Moses").title("IT").authors(List.of(author3)).build());
  }
}
