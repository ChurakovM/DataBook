package com.example.bookservice.mappers;

import bookservice.requests.PostBookRequest;
import bookservice.responses.BookDetailsResponse;
import com.example.bookservice.models.BookModel;
import bookservice.responses.PostBookResponse;
import bookservice.responses.PostAssignBookResponse;
import bookservice.responses.ShortBookInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "visitorId", ignore = true)
    BookModel postBookRequestToBookModel(PostBookRequest request);

    PostBookResponse bookModelToPostBookResponse(BookModel bookModel);

    BookDetailsResponse bookModelToGetBookResponse(BookModel bookModel);

    PostAssignBookResponse bookModelToPostAssignBookResponse(BookModel bookModel);

    ShortBookInfoResponse bookModelToShortBookInfoResponse(BookModel bookModel);
}
