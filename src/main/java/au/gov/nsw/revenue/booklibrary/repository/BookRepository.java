package au.gov.nsw.revenue.booklibrary.repository;

import au.gov.nsw.revenue.booklibrary.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query("update Book book set book.statusId = ?1 where book.bookId = ?2")
    void updateBookStatus(Integer statusId, Long bookId);
}
