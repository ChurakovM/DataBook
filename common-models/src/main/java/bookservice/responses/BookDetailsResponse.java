package bookservice.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDetailsResponse {

    private String id, authorName, bookName, visitorId;
}
