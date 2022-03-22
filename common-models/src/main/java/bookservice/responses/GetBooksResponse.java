package bookservice.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetBooksResponse {

    private List<ShortBookInfoResponse> listOfBooks;
}
