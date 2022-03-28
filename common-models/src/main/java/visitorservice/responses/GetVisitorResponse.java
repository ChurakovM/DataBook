package visitorservice.responses;

import bookservice.responses.GetBooksResponse;
import bookservice.responses.ShortBookInfoResponse;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetVisitorResponse {

    private String id, firstName, lastName, email, phoneNumber;
    private List<ShortBookInfoResponse> listOfBooks;
}
