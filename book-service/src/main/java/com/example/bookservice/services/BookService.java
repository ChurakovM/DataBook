package com.example.bookservice.services;

import bookservice.queries.BookQueryParameters;
import bookservice.requests.PostAssignBookRequest;
import bookservice.requests.PostBookRequest;
import bookservice.responses.BookDetailsResponse;
import com.example.bookservice.exceptions.BookNotFoundException;
import com.example.bookservice.mappers.BookMapper;
import com.example.bookservice.models.BookModel;
import com.example.bookservice.persistence.BookRepository;
import bookservice.responses.GetBooksResponse;
import bookservice.responses.PostBookResponse;
import bookservice.responses.PostAssignBookResponse;
import bookservice.responses.ShortBookInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.bookservice.persistence.SpecificationUtils.containsAuthorName;
import static com.example.bookservice.persistence.SpecificationUtils.containsBookName;
import static com.example.bookservice.persistence.SpecificationUtils.containsVisitorId;
import static com.example.bookservice.persistence.SpecificationUtils.matchesId;
import static org.springframework.data.jpa.domain.Specification.where;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public PostBookResponse createBook(PostBookRequest request) {
        BookModel bookModel = bookMapper.postBookRequestToBookModel(request);
        bookRepository.save(bookModel);
        return bookMapper.bookModelToPostBookResponse(bookModel);
    }

    public BookDetailsResponse getBook(String bookId) {
        BookModel bookModel = findVisitorInRepository(bookId);
        return bookMapper.bookModelToGetBookResponse(bookModel);
    }

    public GetBooksResponse getBooks(BookQueryParameters queryParameters) {
        List<BookModel> listOfBooksModels = bookRepository
            .findAll(where(containsVisitorId(queryParameters.getVisitorId()))
                .and(containsAuthorName(queryParameters.getAuthorName()))
                .and(containsBookName(queryParameters.getBookName())));

        GetBooksResponse getBooksResponse = new GetBooksResponse();

        List<ShortBookInfoResponse> listOfBooks = listOfBooksModels
            .stream()
            .map(bookMapper::bookModelToShortBookInfoResponse)
            .collect(Collectors.toList());

        getBooksResponse.setListOfBooks(listOfBooks);
        return getBooksResponse;
    }

    public PostAssignBookResponse updateBook(PostAssignBookRequest request) {
        BookModel bookModel = findVisitorInRepository(request.getId());
        bookModel.setVisitorId(request.getVisitorId());
        bookRepository.save(bookModel);
        return bookMapper.bookModelToPostAssignBookResponse(bookModel);
    }

    private BookModel findVisitorInRepository(String bookId) {
        return bookRepository.findOne(where(matchesId(bookId)))
            .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
