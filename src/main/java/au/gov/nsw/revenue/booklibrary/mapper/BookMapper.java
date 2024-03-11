package au.gov.nsw.revenue.booklibrary.mapper;

import au.gov.nsw.revenue.booklibrary.entity.Author;
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
    @Mapping(
            target = "authors",
            source = "book.authors",
            qualifiedByName = "convertListOfAuthorEntitiesToStringList")
    Book toBookDto(au.gov.nsw.revenue.booklibrary.entity.Book book, String statusDescriptionByStatusId);

    @Named("getAuthors")
    default List<Author> getAuthors(List<String> authorDetails) {
        List<Author> authors = new ArrayList<>();
        for (String authorFullName : authorDetails) {
            authors.add(Author.builder().fullName(authorFullName).build());
        }
        return authors;
    }

    @Named("getStatus")
    default Book.StatusEnum getStatus(String statusDescriptionByStatusId) {
        return Book.StatusEnum.fromValue(statusDescriptionByStatusId);
    }

    @Named("convertListOfAuthorEntitiesToStringList")
    default List<String> convertListOfAuthorEntitiesToStringList(List<Author> authorDetails) {
        List<String> authors = new ArrayList<>();
        for (Author authorDetail : authorDetails) {
            authors.add(authorDetail.getFullName());
        }
        return authors;
    }
}
