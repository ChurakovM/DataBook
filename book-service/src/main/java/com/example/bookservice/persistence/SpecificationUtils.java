package com.example.bookservice.persistence;

import com.example.bookservice.models.BookModel;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public final class SpecificationUtils {

    public static Specification<BookModel> matchesId(String id) {
        return (bookModel, cq, cb) -> cb.equal(bookModel.get("id"), id);
    }
}
