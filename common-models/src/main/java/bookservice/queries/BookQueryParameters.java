package bookservice.queries;

import lombok.Getter;

@Getter
public class BookQueryParameters {

    private final String authorName, bookName, visitorId;

    public BookQueryParameters(String authorName, String bookName, String visitorId) {
        this.authorName = authorName;
        this.bookName = bookName;
        this.visitorId = visitorId;
    }
}
