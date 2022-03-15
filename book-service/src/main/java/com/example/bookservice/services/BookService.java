package com.example.bookservice.services;

import com.example.bookservice.exceptions.BookNotFoundException;
import com.example.bookservice.mappers.BookMapper;
import com.example.bookservice.models.BookModel;
import com.example.bookservice.persistence.BookRepository;
import com.example.bookservice.requests.PostBookRequest;
import com.example.bookservice.responses.GetBookResponse;
import com.example.bookservice.responses.GetBooksResponse;
import com.example.bookservice.responses.PostBookResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public GetBookResponse getBook(String bookId) {
        BookModel bookModel = findVisitorInRepository(bookId);
        return bookMapper.bookModelToGetBookResponse(bookModel);
    }

    public GetBooksResponse getBooks() {
        List<BookModel> listOfBooks = bookRepository.findAll();
        GetBooksResponse getBooksResponse = new GetBooksResponse();
        getBooksResponse.setListOfBooks(listOfBooks);
        return getBooksResponse;
    }

    private BookModel findVisitorInRepository(String bookId) {
        return bookRepository.findOne(where(matchesId(bookId)))
                .orElseThrow(() -> new BookNotFoundException(bookId));
    }
}
