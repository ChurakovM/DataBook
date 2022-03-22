package bookservice.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortBookInfoResponse {

    private String id, authorName, bookName;
}
