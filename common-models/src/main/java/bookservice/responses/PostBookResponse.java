package bookservice.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBookResponse {

    private String id, authorName, bookName;
}
