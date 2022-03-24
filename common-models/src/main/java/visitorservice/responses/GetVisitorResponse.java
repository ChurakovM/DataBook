package visitorservice.responses;

import bookservice.responses.GetBooksResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorResponse {

    private String id, firstName, lastName, email, phoneNumber;
    private GetBooksResponse booksOfVisitor;
}
