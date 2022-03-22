package com.example.bookservice.persistence;

import com.example.bookservice.models.BookModel;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class SpecificationUtils {

    public static Specification<BookModel> matchesId(String id) {
        return (bookModel, cq, cb) -> cb.equal(bookModel.get("id"), id);
    }

    public static Specification<BookModel> containsVisitorId(String visitorId) {
        return (bookModel, cq, cb) -> cb.like(bookModel.get("visitorId"), visitorId);
    }

    public static Specification<BookModel> containsAuthorName(String authorName) {
        return (bookModel, cq, cb) -> cb.like(bookModel.get("authorName"), "%" + authorName + "%");
    }

    public static Specification<BookModel> containsBookName(String bookName) {
        return (bookModel, cq, cb) -> cb.like(bookModel.get("bookName"), "%" + bookName + "%");
    }
}
