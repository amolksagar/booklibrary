package au.gov.nsw.revenue.booklibrary.mapper;

import au.gov.nsw.revenue.booklibrary.entity.Author;
import au.gov.nsw.revenue.booklibrary.entity.Status;
import au.gov.nsw.revenue.booklibrary.openapi.model.AuthorDetails;
import au.gov.nsw.revenue.booklibrary.openapi.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    @Mapping(
            target = "authors",
            source = "book.authors",
            qualifiedByName = "getAuthors")
    @Mapping(
            target = "statusId",
            source = "statusIdByStatusDescription")
    au.gov.nsw.revenue.booklibrary.entity.Book toBookEntity(Book book, Integer statusIdByStatusDescription);

    @Mapping(
            target = "status",
            source = "statusDescriptionByStatusId",
            qualifiedByName = "getStatus")
    Book toBookDto(au.gov.nsw.revenue.booklibrary.entity.Book book, String statusDescriptionByStatusId);

    @Named("getAuthors")
    default List<Author> getAuthors(List<AuthorDetails> authorDetails) {
        List<Author> authors = new ArrayList<>();
        for (AuthorDetails authorDetail : authorDetails) {
            authors.add(Author.builder().firstName(authorDetail.getFirstName())
                    .middleName(authorDetail.getMiddleName())
                    .lastName(authorDetail.getLastName()).build());
        }
        return authors;
    }

    @Named("getStatus")
    default Book.StatusEnum getAuthors(String statusDescriptionByStatusId) {
        return Book.StatusEnum.fromValue(statusDescriptionByStatusId);
    }
}
